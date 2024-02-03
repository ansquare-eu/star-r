package eu.ansquare.squarepowered.actionscreen.action;

import eu.ansquare.squarepowered.Squarepowered;
import eu.ansquare.squarepowered.util.LocalizationHandler;
import eu.ansquare.squarepowered.util.WorldSecurity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.UUID;

public class LocalizeScreenAction implements ScreenAction{
	public UUID id;
	public LocalizeScreenAction(PacketByteBuf buf){
		id = buf.readUuid();
	}
	@Override
	public void action(ServerPlayerEntity entity) {
		ServerPlayerEntity player = entity.getServer().getPlayerManager().getPlayer(id);
		WorldSecurity.checkSpatial(true, player.getBlockPos(), entity,player.getServerWorld());
		LocalizationHandler.begin(entity, player);
		Squarepowered.log("log", 0);
	}
}
