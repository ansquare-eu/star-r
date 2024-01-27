package eu.ansquare.squarepowered.util.changelogging;

import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public record Change(BlockPos pos, ServerWorld world, BlockState prevState, BlockState nextState) {

}
