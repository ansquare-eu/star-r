package eu.ansquare.squarepowered.actionscreen.action;

import net.minecraft.server.network.ServerPlayerEntity;

public interface ScreenAction {
	default Runnable run(ServerPlayerEntity entity){
		return ()->action(entity);
	}
	void action(ServerPlayerEntity entity);
}
