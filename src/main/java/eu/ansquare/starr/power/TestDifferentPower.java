package eu.ansquare.starr.power;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class TestDifferentPower extends Power{
	@Override
	public String getName() {
		return "testDifferent";
	}

	@Override
	public void onActivate(ServerPlayerEntity player) {
		player.sendMessage(Text.literal("lol"), false);

	}
}
