package eu.ansquare.starr.power.creation;

import eu.ansquare.starr.power.Power;
import net.minecraft.entity.EntityType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;

import java.util.function.Consumer;

public class SummonEntityPower extends Power {
	public Consumer<ServerPlayerEntity> summoner;
	public SummonEntityPower(Consumer<ServerPlayerEntity> summoner){
		this.summoner = summoner;
	}
	@Override
	public void onActivate(ServerPlayerEntity player) {
		summoner.accept(player);
	}

}
