package eu.ansquare.starr.network;

import eu.ansquare.starr.StarR;
import eu.ansquare.starr.network.c2s.*;
import eu.ansquare.starr.network.s2c.RenderLaserPacket;
import eu.ansquare.starr.network.s2c.TogglePlayerStatePacket;
import eu.ansquare.starr.network.s2c.UnrenderLaserPacket;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;
import org.quiltmc.qsl.networking.api.client.ClientPlayNetworking;

public class ModPackets {
	public static final Identifier POWER_PACKET_ID = new Identifier(StarR.MODID, "power");
	public static final Identifier AS_PACKET_ID = new Identifier(StarR.MODID, "addsubstract");
	public static final Identifier TPLOC_PACKET_ID = new Identifier(StarR.MODID, "tploc");
	public static final Identifier TPSAVED_PACKET_ID = new Identifier(StarR.MODID, "tpsaved");
	public static final Identifier SAVELOC_PACKET_ID = new Identifier(StarR.MODID, "saveloc");

	public static final Identifier RENDER_LASER_PACKET_ID = new Identifier(StarR.MODID, "laser");
	public static final Identifier UNRENDER_LASER_PACKET_ID = new Identifier(StarR.MODID, "unlaser");
	public static final Identifier TOGGLE_PLAYER_STATE_PACKET_ID = new Identifier(StarR.MODID, "togglestate");



	public static void initC2S(){
		ServerPlayNetworking.registerGlobalReceiver(AS_PACKET_ID, ASPacket::receive);
		ServerPlayNetworking.registerGlobalReceiver(POWER_PACKET_ID, PowerPacket::receive);
		ServerPlayNetworking.registerGlobalReceiver(TPSAVED_PACKET_ID, TeleportToSavedPacket::receive);
		ServerPlayNetworking.registerGlobalReceiver(TPLOC_PACKET_ID, TeleportToLocPacket::receive);
		ServerPlayNetworking.registerGlobalReceiver(SAVELOC_PACKET_ID, SaveLocPacket::receive);


	}
	public static void initS2C(){
		ClientPlayNetworking.registerGlobalReceiver(RENDER_LASER_PACKET_ID, RenderLaserPacket::receive);
		ClientPlayNetworking.registerGlobalReceiver(UNRENDER_LASER_PACKET_ID, UnrenderLaserPacket::receive);
		ClientPlayNetworking.registerGlobalReceiver(TOGGLE_PLAYER_STATE_PACKET_ID, TogglePlayerStatePacket::receive);
	}
}
