package eu.ansquare.squarepowered.actionscreen.client;

import eu.ansquare.squarepowered.SquareNetworking;
import eu.ansquare.squarepowered.Squarepowered;
import eu.ansquare.squarepowered.actionscreen.handler.LocationTeleportActionScreenHandler;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import org.quiltmc.qsl.networking.api.PacketByteBufs;
import org.quiltmc.qsl.networking.api.client.ClientPlayNetworking;

public class LocationTeleportScreen extends ActionScreen<LocationTeleportActionScreenHandler> {
	TextFieldWidget zabox;
	TextFieldWidget xbox;
	TextFieldWidget ybox;
	ButtonWidget tpbutton;
	public LocationTeleportScreen(LocationTeleportActionScreenHandler handler, PlayerInventory inventory, Text title) {
		super(handler, inventory, title, Squarepowered.id("textures/screen/teleportscreen.png"), 176, 166);
		screenKey = "teleport_location";
	}
	public void init(){
		super.init();
		xbox = addTextField(-200, 94, 120, 20, "x", 32767);
		ybox = addTextField(-200, 118, 120, 20, "y", 32767);
		zabox = addTextField(-200, 142, 120, 20, "z", 32767);
		tpbutton = addButton("tp", -200, 70, 50, 20, 0);
	}
	@Override
	public void sendPacket(int... actions) {
		PacketByteBuf buf = PacketByteBufs.create();
		buf.writeInt(0);
		try {
		buf.writeInt(Integer.parseInt(xbox.getText()));
		buf.writeInt(Integer.parseInt(ybox.getText()));
		buf.writeInt(Integer.parseInt(zabox.getText()));
			ClientPlayNetworking.send(SquareNetworking.ACTION_SCREEN_PACKET, buf);
		} catch (NumberFormatException e){

		}
	}
}
