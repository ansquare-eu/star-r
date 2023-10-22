package eu.ansquare.starr.power;

import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;

public class InvisPower extends Power{

	@Override
	public void onActivate(ServerPlayerEntity player) {
		player.setInvisible(!player.isInvisible());
	}
}
