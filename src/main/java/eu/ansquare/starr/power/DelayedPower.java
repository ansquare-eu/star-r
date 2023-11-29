package eu.ansquare.starr.power;

import net.minecraft.server.network.ServerPlayerEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class DelayedPower extends Power{
	public int delay;
	public DelayedPower(int delay){
		this.delay = delay;
	}
	private Map<UUID, Integer> delays = new HashMap<>();
	public void onActivate(ServerPlayerEntity player){
		if(delays.get(player.getUuid()) == null || delays.get(player.getUuid()) <= 0){
				activateAllowed(player);
				delays.put(player.getUuid(), delay);

		}
	}
	public abstract void activateAllowed(ServerPlayerEntity player);
	public void decreaseDelays(){
		delays.keySet().forEach(uuid -> {
			Integer integer = delays.get(uuid) - 1;
			if(integer >= 0){
				delays.put(uuid, integer);
			}else {
				delays.remove(integer);
			}
		});
	}
}
