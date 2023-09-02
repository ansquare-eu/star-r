package eu.ansquare.starr.network;

import eu.ansquare.starr.StarR;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;

public class ModPackets {
	public static final Identifier POWER_PACKET_ID = new Identifier(StarR.MODID, "power");
	public static void initC2S(){
		ServerPlayNetworking.registerGlobalReceiver(POWER_PACKET_ID, PowerPacket::receive);
	}
}
