package eu.ansquare.squarepowered.util.editation;

import eu.ansquare.squarepowered.actionscreen.client.ActionScreen;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

@FunctionalInterface
public interface ExtraFieldWorldEditation extends Editation{
	void run(BlockPos one, BlockPos two, Identifier block,Identifier extra, ServerPlayerEntity player);
	default void getRequriedWidgets(ActionScreen screen, int height, String editationKey){
		screen.addTextField(60, height, 80, 16, editationKey + ".extra", 64);
	}
	@Override
	default Type getType(){
		return Type.REPLACEMENT;
	}
}
