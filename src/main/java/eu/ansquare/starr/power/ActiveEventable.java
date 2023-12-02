package eu.ansquare.starr.power;

import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;

public interface ActiveEventable {
	void activeAction(ServerPlayerEntity player, Entity entity);
}
