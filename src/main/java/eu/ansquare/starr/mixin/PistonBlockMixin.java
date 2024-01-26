package eu.ansquare.starr.mixin;

import eu.ansquare.sbd.BlockDataApi;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.block.piston.PistonBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PistonBlock.class)
public class PistonBlockMixin {
	@Inject(method = "isMovable", at = @At("HEAD"), cancellable = true)
	private static void isMovable(BlockState state, World world, BlockPos pos, Direction direction, boolean canBreak, Direction pistonFacing, CallbackInfoReturnable<Boolean> cir) {
		//TODO move to SBD
		if(BlockDataApi.getBoolean(pos, world, "no_drop")){
			cir.setReturnValue(false);
		}
	}
}
