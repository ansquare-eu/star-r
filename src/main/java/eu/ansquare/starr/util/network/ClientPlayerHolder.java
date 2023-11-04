package eu.ansquare.starr.util.network;

import java.util.*;

import static eu.ansquare.starr.util.network.ClientPlayerState.AUTOMSG;

public class ClientPlayerHolder {
	public Map<UUID, ClientLaser> LASER_MAP = new HashMap<>();
	public Set<UUID> AUTOMSG_MAP = new HashSet<>();
	public boolean toggle(ClientPlayerState state, UUID uuid){
		if(state == AUTOMSG){
			if(AUTOMSG_MAP.contains(uuid)){
				AUTOMSG_MAP.remove(uuid);
				return false;
			}
			AUTOMSG_MAP.add(uuid);
			return true;
		}
		return true;
	}
}
