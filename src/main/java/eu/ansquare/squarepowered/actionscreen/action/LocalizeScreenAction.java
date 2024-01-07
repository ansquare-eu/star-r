package eu.ansquare.squarepowered.actionscreen.action;

import eu.ansquare.squarepowered.Squarepowered;
import eu.ansquare.squarepowered.cca.SquareEntityComponents;
import eu.ansquare.squarepowered.util.AnchorChecker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

import java.util.UUID;

public class LocalizeScreenAction implements ScreenAction{
	public UUID id;
	public LocalizeScreenAction(PacketByteBuf buf){
		id = buf.readUuid();
	}
	@Override
	public void action(ServerPlayerEntity entity) {
		ServerPlayerEntity player = entity.getServer().getPlayerManager().getPlayer(id);
		entity.teleport(player.getServerWorld(), player.getX(), player.getY(), player.getZ(), player.getYaw(), player.getPitch());
		Squarepowered.log("log", 0);
	}
}
