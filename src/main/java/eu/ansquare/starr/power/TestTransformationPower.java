package eu.ansquare.starr.power;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class TestTransformationPower extends Power{
	@Override
	public String getName() {
		return "testTransformation";
	}

	@Override
	public void onActivate(ServerPlayerEntity player) {
		player.sendMessage(Text.literal("TEstpoower"), false);
	}
}
