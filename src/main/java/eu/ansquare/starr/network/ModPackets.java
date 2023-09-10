package eu.ansquare.starr.network;

import eu.ansquare.starr.StarR;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;
import org.quiltmc.qsl.networking.api.client.ClientPlayNetworking;

public class ModPackets {
	public static final Identifier POWER_PACKET_ID = new Identifier(StarR.MODID, "power");
	public static final Identifier AS_PACKET_ID = new Identifier(StarR.MODID, "addsubstract");
	public static final Identifier RENDER_LASER_PACKET_ID = new Identifier(StarR.MODID, "laser");

	public static void initC2S(){
		ServerPlayNetworking.registerGlobalReceiver(AS_PACKET_ID, ASPacket::receive);
		ServerPlayNetworking.registerGlobalReceiver(POWER_PACKET_ID, PowerPacket::receive);
	}
	public static void initS2C(){
		ClientPlayNetworking.registerGlobalReceiver(RENDER_LASER_PACKET_ID, RenderLaserPacket::receive);
	}
}
