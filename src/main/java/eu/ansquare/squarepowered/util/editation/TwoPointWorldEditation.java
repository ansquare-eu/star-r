package eu.ansquare.squarepowered.util.editation;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

@FunctionalInterface
public interface TwoPointWorldEditation extends Editation{
	void run(BlockPos one, BlockPos two, Identifier block);

	@Override
	default Type getType(){
		return Type.TWO_POINT;
	}
}
