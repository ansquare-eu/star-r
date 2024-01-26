package eu.ansquare.squarepowered.actionscreen.action;

import eu.ansquare.squarepowered.SquareRegistries;
import eu.ansquare.squarepowered.Squarepowered;
import eu.ansquare.squarepowered.util.editation.Editation;
import eu.ansquare.squarepowered.util.editation.TwoPointWorldEditation;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.UUID;

public class WorldEditAction implements ScreenAction{
	public Editation editation;

	public Identifier block;
	public BlockPos one;
	public BlockPos two;

	public WorldEditAction(PacketByteBuf buf){
		block = buf.readIdentifier();
		one = buf.readBlockPos();
		editation = SquareRegistries.EDITATIONS.get(buf.readIdentifier());
		if(editation.getType() == Editation.Type.REPLACEMENT || editation.getType() == Editation.Type.TWO_POINT){
			two = buf.readBlockPos();
		}
	}
	@Override
	public void action(ServerPlayerEntity entity) {
		if(editation instanceof TwoPointWorldEditation twoPointWorldEditation){
			twoPointWorldEditation.run(one, two, block);
		}
	}
}
