package eu.ansquare.starr.power;

import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.*;

public abstract class ToggleablePower extends Power{
	public Set<UUID> map = new HashSet<>() {
	};
	@Override
	public void onActivate(ServerPlayerEntity player) {
		if(map.contains(player.getUuid())){
			map.remove(player.getUuid());
		} else {
			map.add(player.getUuid());
		}
	}

	public abstract void activeTick(LivingEntity entity);
	public boolean isActiveFor(UUID uuid){
		return map.contains(uuid);
	}
}
