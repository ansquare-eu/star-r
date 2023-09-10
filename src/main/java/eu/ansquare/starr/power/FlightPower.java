package eu.ansquare.starr.power;

import net.fabricmc.fabric.api.entity.event.v1.EntityElytraEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;

public class FlightPower extends ToggleablePower{
	public FlightPower(){};

	@Override
	public String getName() {
		return "flight";
	}

	@Override
	public void activationAction(ServerPlayerEntity player) {
	}

	@Override
	public void deactivationAction(ServerPlayerEntity player) {

	}

	@Override
	public void activeTick(LivingEntity entity) {
	}
}
