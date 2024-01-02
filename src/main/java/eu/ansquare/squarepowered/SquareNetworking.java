package eu.ansquare.squarepowered;

public class SquareNetworking {
	public static void initC2S(){
		ServerPlayNetworking.registerGlobalReceiver(AS_PACKET_ID, ASPacket::receive);
	}
}
