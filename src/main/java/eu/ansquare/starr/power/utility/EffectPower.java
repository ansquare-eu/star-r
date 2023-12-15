package eu.ansquare.starr.power.utility;

import eu.ansquare.starr.power.ToggleablePower;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.network.ServerPlayerEntity;

public class EffectPower extends ToggleablePower {
	final StatusEffect effect;
	int level;

	public EffectPower(StatusEffect effect, int level) {
		this.effect = effect;
		this.level = level;
	}

	@Override
	public void activationAction(ServerPlayerEntity player) {
		StatusEffectInstance instance = new StatusEffectInstance(effect, StatusEffectInstance.INFINITE_DURATION, level, false, false, false);
		player.addStatusEffect(instance);
	}

	@Override
	public void deactivationAction(ServerPlayerEntity player) {
		player.removeStatusEffect(effect);
	}

	@Override
	public void activeTick(LivingEntity entity) {

	}
}
