package eu.ansquare.starr.power.creation;

import eu.ansquare.starr.power.Power;
import net.minecraft.entity.EntityType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;

public class SummonEntityPower extends Power {
	public EntitySummoner summoner;
	public SummonEntityPower(EntitySummoner summoner){
		this.summoner = summoner;
	}
	@Override
	public void onActivate(ServerPlayerEntity player) {
		summoner.run(player);
	}
	@FunctionalInterface
	public interface EntitySummoner{
		void run(ServerPlayerEntity player);
	}
}
