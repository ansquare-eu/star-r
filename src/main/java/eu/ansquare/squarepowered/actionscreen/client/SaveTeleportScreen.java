package eu.ansquare.squarepowered.actionscreen.client;

import eu.ansquare.squarepowered.SquareNetworking;
import eu.ansquare.squarepowered.Squarepowered;
import eu.ansquare.squarepowered.actionscreen.SaveTeleportActionScreenHandler;
import eu.ansquare.squarepowered.cca.SavedLocationComponent;
import eu.ansquare.squarepowered.cca.SquareEntityComponents;
import eu.ansquare.squarepowered.util.PrettyPosUtil;
import eu.ansquare.squarepowered.util.SquareMiscUtils;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.quiltmc.qsl.networking.api.PacketByteBufs;
import org.quiltmc.qsl.networking.api.client.ClientPlayNetworking;

public class SaveTeleportScreen extends ActionScreen<SaveTeleportActionScreenHandler> {
	PlayerEntity player;
	public SaveTeleportScreen(SaveTeleportActionScreenHandler handler, PlayerInventory inventory, Text title) {
		super(handler, inventory, title, Squarepowered.id("textures/screen/teleportscreen.png"), 176, 166);
		screenKey = "teleport_save";
		player = handler.player;
	}
	protected void drawForeground(GuiGraphics graphics, int mouseX, int mouseY) {
		super.drawForeground(graphics, mouseX, mouseY);
		SquareEntityComponents.SAVED_LOCATION_COMPONENT.sync(player);
		if(SquareEntityComponents.SAVED_LOCATION_COMPONENT.isProvidedBy(player)){
			SavedLocationComponent component = SquareEntityComponents.SAVED_LOCATION_COMPONENT.get(player);
			for (int i = 0; i < component.amount(); i++) {
				BlockPos pos = component.get(i);
				graphics.drawText(textRenderer, PrettyPosUtil.colonSeparatedBlockPos(pos), 5, 25 + i *20, 1, false);
			}
		} else {
	}}
	public void init(){
		super.init();
		addButton("save",  this.backgroundWidth - 55, 20, 45, 20, 0, 1 );
		addButton("save",  this.backgroundWidth - 55, 40, 45, 20, 1, 1 );
		addButton("save",  this.backgroundWidth - 55, 60, 45, 20, 2, 1 );
		addButton("save",  this.backgroundWidth - 55, 80, 45, 20, 3, 1 );
		addButton("save",  this.backgroundWidth - 55, 100, 45, 20, 4, 1 );
		addButton("save",  this.backgroundWidth - 55, 120, 45, 20, 5, 1 );
		addButton("tp",  this.backgroundWidth - 105, 20, 45, 20, 0, 0 );
		addButton("tp",  this.backgroundWidth - 105, 40, 45, 20, 1, 0 );
		addButton("tp",  this.backgroundWidth - 105, 60, 45, 20, 2, 0 );
		addButton("tp",  this.backgroundWidth - 105, 80, 45, 20, 3, 0 );
		addButton("tp",  this.backgroundWidth - 105, 100, 45, 20, 4, 0 );
		addButton("tp",  this.backgroundWidth - 105, 120, 45, 20, 5, 0 );


	}
	@Override
	public void sendPacket(int... actions) {
		PacketByteBuf buf = PacketByteBufs.create();
		buf.writeInt(1);
		if(actions.length >= 2){
			boolean save = SquareMiscUtils.boolFromInt(actions[1]);
			buf.writeInt(actions[0]);
			buf.writeBoolean(save);
			ClientPlayNetworking.send(SquareNetworking.ACTION_SCREEN_PACKET, buf);
		}
	}
}
