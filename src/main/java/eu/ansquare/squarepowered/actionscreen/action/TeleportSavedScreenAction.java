package eu.ansquare.squarepowered.actionscreen.action;

import eu.ansquare.squarepowered.action.OpenActionScreenAction;
import eu.ansquare.squarepowered.cca.SquareEntityComponents;
import eu.ansquare.squarepowered.util.AnchorChecker;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public class TeleportSavedScreenAction implements ScreenAction{
	public int savedId;
	public boolean save;
	public TeleportSavedScreenAction(PacketByteBuf buf){
		savedId = buf.readInt();
		save = buf.readBoolean();
	}
	@Override
	public void action(ServerPlayerEntity entity) {
		SquareEntityComponents.SAVED_LOCATION_COMPONENT.maybeGet(entity).ifPresent(savedLocationComponent -> {
			if(!save){
				BlockPos pos = savedLocationComponent.get(savedId);
				if(pos == null) return;
				if(AnchorChecker.checkSpatial(true, pos, entity)) OpenActionScreenAction.processTeleportation(entity, pos.getX(), pos.getY(), pos.getZ(), entity.getServerWorld());
			} else {
				 if(AnchorChecker.checkLocalSpatial(true, entity.getBlockPos(), entity)) savedLocationComponent.put(savedId, entity.getBlockX(), entity.getBlockY(), entity.getBlockZ());
				SquareEntityComponents.SAVED_LOCATION_COMPONENT.sync(entity);
			}
		});
	}
}
