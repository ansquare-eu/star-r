package eu.ansquare.squarepowered.actionscreen.client;

import eu.ansquare.squarepowered.SquareNetworking;
import eu.ansquare.squarepowered.Squarepowered;
import eu.ansquare.squarepowered.actionscreen.handler.SaveTeleportActionScreenHandler;
import eu.ansquare.squarepowered.actionscreen.handler.WorldEditScreenHandler;
import eu.ansquare.squarepowered.cca.SavedLocationComponent;
import eu.ansquare.squarepowered.cca.SquareComponents;
import eu.ansquare.squarepowered.util.PrettyPosUtil;
import eu.ansquare.squarepowered.util.SquareMiscUtils;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.quiltmc.qsl.networking.api.PacketByteBufs;
import org.quiltmc.qsl.networking.api.client.ClientPlayNetworking;

public class WorldEditScreen extends ActionScreen<WorldEditScreenHandler> {
	PlayerEntity player;
	public WorldEditScreen(WorldEditScreenHandler handler, PlayerInventory inventory, Text title) {
		super(handler, inventory, title, Squarepowered.id("textures/screen/teleportscreen.png"), 176, 166);
		screenKey = "world_edit";
		player = handler.player;
	}
	protected void drawForeground(GuiGraphics graphics, int mouseX, int mouseY) {
		super.drawForeground(graphics, mouseX, mouseY);
	}
	public void init(){
		super.init();



	}
	@Override
	public void sendPacket(int... actions) {
		PacketByteBuf buf = PacketByteBufs.create();
		buf.writeInt(3);

	}
}
