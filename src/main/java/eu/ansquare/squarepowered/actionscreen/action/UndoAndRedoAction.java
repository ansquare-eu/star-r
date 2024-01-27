package eu.ansquare.squarepowered.actionscreen.action;

import eu.ansquare.squarepowered.Squarepowered;
import eu.ansquare.squarepowered.util.changelogging.Change;
import eu.ansquare.squarepowered.util.changelogging.ChangeLogger;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.UUID;

public class UndoAndRedoAction implements ScreenAction{
	public boolean redo;
	public UndoAndRedoAction(PacketByteBuf buf){
		redo = buf.readBoolean();
	}
	@Override
	public void action(ServerPlayerEntity entity) {
		ChangeLogger logger = ChangeLogger.getOrCreate(entity);
		if(redo) logger.redo(); else logger.undo();
	}
}
