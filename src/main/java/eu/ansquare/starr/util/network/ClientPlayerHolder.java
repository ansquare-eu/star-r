package eu.ansquare.starr.util.network;

import java.util.*;

import static eu.ansquare.starr.util.network.ClientPlayerState.AUTOMSG;

public class ClientPlayerHolder {
	public Map<UUID, ClientLaser> LASER_MAP = new HashMap<>();
	public boolean isFalseCreative = false;
	public boolean add(ClientPlayerState state){
		if(state == ClientPlayerState.CREATIVE){
			isFalseCreative = !isFalseCreative;
		}
		return true;
	}
	public boolean readCreative(){
		return isFalseCreative;
	}
}
