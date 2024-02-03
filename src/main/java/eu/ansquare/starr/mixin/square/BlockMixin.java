package eu.ansquare.starr.mixin.square;

import eu.ansquare.sbd.BlockDataApi;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

@Mixin(Block.class)
public class BlockMixin {
	@Inject(method = "dropStack(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/item/ItemStack;)V", at = @At("HEAD"), cancellable = true)
	private static void onGetDroppedStacks(World world, BlockPos pos, ItemStack stack, CallbackInfo ci){
		if(BlockDataApi.getBoolean(pos, world, "no_drop")){
			BlockDataApi.setBoolean(pos, world, "no_drop", false);
			ci.cancel();
		}
	}
	@Inject(method = "dropStack(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/Direction;Lnet/minecraft/item/ItemStack;)V", at = @At("HEAD"), cancellable = true)
	private static void onGetDroppedStacksTwo(World world, BlockPos pos, Direction direction, ItemStack stack, CallbackInfo ci){
		if(BlockDataApi.getBoolean(pos, world, "no_drop")){
			BlockDataApi.setBoolean(pos, world, "no_drop", false);
			ci.cancel();
		}
	}
	@Inject(method = "afterBreak",at = @At("TAIL"), cancellable = true)
	public void onAfterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack stack, CallbackInfo ci){
		//TODO move to SBD
		BlockDataApi.setBoolean(pos, world, "no_drop", false);
	}
}
