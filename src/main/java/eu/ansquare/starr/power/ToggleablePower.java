package eu.ansquare.starr.power;

import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public abstract class ToggleablePower extends Power{
	public Set<UUID> map = new HashSet<>() {
	};
	@Override
	public void onActivate(ServerPlayerEntity player) {
		if(map.contains(player.getUuid())){
			map.remove(player.getUuid());
			deactivationAction(player);
		} else {
			map.add(player.getUuid());
			activationAction(player);
		}
	}
	public abstract void activationAction(ServerPlayerEntity player);
	public abstract void deactivationAction(ServerPlayerEntity player);
	public abstract void activeTick(LivingEntity entity);
	public boolean isActiveFor(UUID uuid){
		return map.contains(uuid);
	}
}
