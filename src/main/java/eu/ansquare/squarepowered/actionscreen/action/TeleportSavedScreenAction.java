package eu.ansquare.squarepowered.actionscreen.action;

import eu.ansquare.squarepowered.action.OpenActionScreenAction;
import eu.ansquare.squarepowered.cca.SquareComponents;
import eu.ansquare.squarepowered.util.WorldSecurity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Pair;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TeleportSavedScreenAction implements ScreenAction{
	public int savedId;
	public boolean save;
	public TeleportSavedScreenAction(PacketByteBuf buf){
		savedId = buf.readInt();
		save = buf.readBoolean();
	}
	@Override
	public void action(ServerPlayerEntity entity) {
		SquareComponents.SAVED_LOCATION_COMPONENT.maybeGet(entity).ifPresent(savedLocationComponent -> {
			if(!save){
				Pair<BlockPos, RegistryKey<World>> pair = savedLocationComponent.get(savedId);
				if(pair == null) return;
				BlockPos pos = pair.getLeft();
				ServerWorld world = entity.getServer().getWorld(pair.getRight());
				if(WorldSecurity.checkSpatial(true, pair.getLeft(), entity, world)) OpenActionScreenAction.processTeleportation(entity, pos.getX(), pos.getY(), pos.getZ(), world);
			} else {
				 if(WorldSecurity.checkLocalSpatial(true, entity.getBlockPos(), entity, entity.getServerWorld())) savedLocationComponent.put(savedId, entity.getBlockX(), entity.getBlockY(), entity.getBlockZ(), entity.getServerWorld());
				SquareComponents.SAVED_LOCATION_COMPONENT.sync(entity);
			}
		});
	}
}
