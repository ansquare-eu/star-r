package eu.ansquare.squarepowered.util.editation;

import eu.ansquare.squarepowered.actionscreen.client.ActionScreen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

@FunctionalInterface
public interface TwoPointWorldEditation extends Editation{
	void run(BlockPos one, BlockPos two, Identifier block, ServerPlayerEntity player);
	default void getRequriedWidgets(ActionScreen screen, int height, String editationKey){
	}
	@Override
	default Type getType(){
		return Type.TWO_POINT;
	}
}
