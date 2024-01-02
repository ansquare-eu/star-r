package eu.ansquare.squarepowered;

import eu.ansquare.squarepowered.actionscreen.ActionScreenC2SPacket;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;

public class SquareNetworking {
	public static Identifier ACTION_SCREEN_PACKET = Squarepowered.id("action_screen_packet");
	public static void initC2S(){
		ServerPlayNetworking.registerGlobalReceiver(ACTION_SCREEN_PACKET, ActionScreenC2SPacket::receive);
	}
}
