package eu.ansquare.starr.power;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class EmptyPower extends Power{
	public static final String LACKS_POWER = "message.starr.lack_power";


	@Override
	public void onActivate(ServerPlayerEntity player) {
		player.sendMessage(Text.translatable(LACKS_POWER), true);
	}
}
