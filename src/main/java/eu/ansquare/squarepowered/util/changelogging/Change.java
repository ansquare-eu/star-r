package eu.ansquare.squarepowered.util.changelogging;

import eu.ansquare.sbd.BlockDataApi;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public record Change(BlockPos pos, ServerWorld world, BlockState prevState, BlockState nextState, boolean noDrop, boolean prevNoDrop) {
	public void revert(){
		world().setBlockState(pos(), prevState());
		BlockDataApi.setBoolean(pos(), world(), "no_drop", prevNoDrop());
	}
	public void unrevert(){
		world().setBlockState(pos(), nextState());
		BlockDataApi.setBoolean(pos(), world(), "no_drop", noDrop());
	}
}
