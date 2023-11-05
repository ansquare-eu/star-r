package eu.ansquare.starr.power.protection;

import eu.ansquare.starr.power.Power;
import net.minecraft.server.network.ServerPlayerEntity;

public class InvisPower extends Power {

	@Override
	public void onActivate(ServerPlayerEntity player) {
		player.setInvisible(!player.isInvisible());
	}
}
