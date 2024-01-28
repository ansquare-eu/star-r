package eu.ansquare.squarepowered.actionscreen.client;

import eu.ansquare.squarepowered.SquareNetworking;
import eu.ansquare.squarepowered.SquareRegistries;
import eu.ansquare.squarepowered.Squarepowered;
import eu.ansquare.squarepowered.actionscreen.handler.SaveTeleportActionScreenHandler;
import eu.ansquare.squarepowered.actionscreen.handler.WorldEditScreenHandler;
import eu.ansquare.squarepowered.cca.SavedLocationComponent;
import eu.ansquare.squarepowered.cca.SquareComponents;
import eu.ansquare.squarepowered.util.PrettyPosUtil;
import eu.ansquare.squarepowered.util.SquareMiscUtils;
import eu.ansquare.squarepowered.util.editation.Editation;
import eu.ansquare.squarepowered.util.editation.ExtraFieldWorldEditation;
import eu.ansquare.squarepowered.util.editation.TwoPointWorldEditation;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.quiltmc.qsl.networking.api.PacketByteBufs;
import org.quiltmc.qsl.networking.api.client.ClientPlayNetworking;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class WorldEditScreen extends ActionScreen<WorldEditScreenHandler> {
	PlayerEntity player;
	public LinkedList<Identifier> numberedEditations = new LinkedList<>();

	public WorldEditScreen(WorldEditScreenHandler handler, PlayerInventory inventory, Text title) {
		super(handler, inventory, title, Squarepowered.id("textures/screen/teleportscreen.png"), 176, 166);
		screenKey = "world_edit";
		player = handler.player;
	}

	protected void drawForeground(GuiGraphics graphics, int mouseX, int mouseY) {
		super.drawForeground(graphics, mouseX, mouseY);
	}

	public void init() {
		super.init();
		addButton("undo", 60, 8, 40, 16, -1);
		addButton("redo", 110, 8, 40, 16, -2);
		addTextField(-200, 140, 120, 16, "block", 32767);
		addEditationsFromRegistry();
	}

	private void addEditationsFromRegistry() {
		Set<RegistryKey<Editation>> keys = SquareRegistries.EDITATIONS.getKeys();
		List<RegistryKey<Editation>> list = keys.stream().sorted(Comparator.comparing(RegistryKey::getValue)).toList();
		for (int i = 0; i < list.size(); i++) {
			RegistryKey<Editation> key = list.get(i);
			addButton(key.getValue().toTranslationKey(), 8, i * 18 + 26, 50, 16, i);
			Editation editation = SquareRegistries.EDITATIONS.get(key);
			numberedEditations.add(i, key.getValue());
			editation.getRequriedWidgets(this, i * 18 + 26, key.getValue().toTranslationKey());
		}
	}

	@Override
	public void sendPacket(int... actions) {
		PacketByteBuf buf = PacketByteBufs.create();
		if (actions[0] < 0) {
			buf.writeInt(4);
			buf.writeBoolean(actions[0] == -2);
		} else {
			buf.writeInt(3);
			Identifier editationId = numberedEditations.get(actions[0]);
			Editation editation = SquareRegistries.EDITATIONS.get(editationId);
			Identifier block = new Identifier(textFields.get("block").getText());
			buf.writeIdentifier(block);
			buf.writeIdentifier(editationId);
			if(editation instanceof ExtraFieldWorldEditation){
				Identifier extra = new Identifier(textFields.get(editationId.toTranslationKey() + ".extra").getText());
				buf.writeIdentifier(extra);
			}
		}
		ClientPlayNetworking.send(SquareNetworking.ACTION_SCREEN_PACKET, buf);

	}
}
