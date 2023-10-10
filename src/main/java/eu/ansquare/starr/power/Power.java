package eu.ansquare.starr.power;

import net.minecraft.server.network.ServerPlayerEntity;

public abstract class Power {
	public abstract String getName();
	public abstract void onActivate(ServerPlayerEntity player);


}
