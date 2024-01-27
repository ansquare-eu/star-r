package eu.ansquare.squarepowered.actionscreen.action;

import eu.ansquare.squarepowered.SquareRegistries;
import eu.ansquare.squarepowered.Squarepowered;
import eu.ansquare.squarepowered.cca.SavedLocationComponent;
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


	public WorldEditAction(PacketByteBuf buf){
		block = buf.readIdentifier();
		editation = SquareRegistries.EDITATIONS.get(buf.readIdentifier());
	}
	@Override
	public void action(ServerPlayerEntity entity) {
		if(editation instanceof TwoPointWorldEditation twoPointWorldEditation){
			twoPointWorldEditation.run(SavedLocationComponent.getSelOne(entity), SavedLocationComponent.getSelTwo(entity), block, entity);
		}
	}
}
