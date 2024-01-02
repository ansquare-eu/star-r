package eu.ansquare.squarepowered.actionscreen.action;

import eu.ansquare.squarepowered.util.AnchorChecker;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public class TeleportLocationScreenAction implements ScreenAction{
	public int x, y ,z;
	public BlockPos pos;
	public TeleportLocationScreenAction(PacketByteBuf buf){
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		pos = new BlockPos(x, y ,z);
	}
	@Override
	public void action(ServerPlayerEntity entity) {
		if(AnchorChecker.checkSpatial(true, pos, entity)) entity.teleport(x, y, z);
	}
}
