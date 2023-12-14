package eu.ansquare.starr.power;

import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.function.Consumer;

public class AnonymousToggleablePower extends ToggleablePower{
	private final Consumer<LivingEntity> action;

	public AnonymousToggleablePower(Consumer<LivingEntity> action) {
		this.action = action;
	}

	@Override
	public void activationAction(ServerPlayerEntity player) {

	}

	@Override
	public void deactivationAction(ServerPlayerEntity player) {

	}

	@Override
	public void activeTick(LivingEntity entity) {
		action.accept(entity);
	}
}
