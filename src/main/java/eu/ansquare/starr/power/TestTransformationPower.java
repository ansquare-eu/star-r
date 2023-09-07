package eu.ansquare.starr.power;

import eu.ansquare.starr.util.datasaving.IDataSaver;
import eu.ansquare.starr.util.datasaving.SuperdudeDataManager;
import net.fabricmc.fabric.api.entity.FakePlayer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class TestTransformationPower extends Power{
	@Override
	public String getName() {
		return "testTransformation";
	}

	@Override
	public void onActivate(ServerPlayerEntity player) {
		if(SuperdudeDataManager.get(((IDataSaver) player)).isFlying(player)){
			player.sendMessage(Text.literal("Yes"), false);
		}
	}
}
