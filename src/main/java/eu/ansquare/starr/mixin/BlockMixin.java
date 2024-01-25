package eu.ansquare.starr.mixin;

import eu.ansquare.sbd.BlockDataApi;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.LinkedList;
import java.util.List;

@Mixin(Block.class)
public class BlockMixin {
	@Inject(method = "getDroppedStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;)Ljava/util/List;", at = @At("TAIL"), cancellable = true)
	private static void onGetDroppedStacks(BlockState state, ServerWorld world, BlockPos pos, @Nullable BlockEntity blockEntity, CallbackInfoReturnable<List<ItemStack>> cir){
		if(BlockDataApi.getBoolean(pos, world, "no_drop")){
			BlockDataApi.setBoolean(pos, world, "no_drop", false);
			cir.setReturnValue(new LinkedList<>());
		}
	}
	@Inject(method = "getDroppedStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)Ljava/util/List;", at = @At("TAIL"), cancellable = true)
	private static void onGetDroppedStacksTwo(BlockState state, ServerWorld world, BlockPos pos, @Nullable BlockEntity blockEntity, @Nullable Entity entity, ItemStack stack, CallbackInfoReturnable<List<ItemStack>> cir){
		if(BlockDataApi.getBoolean(pos, world, "no_drop")){
			BlockDataApi.setBoolean(pos, world, "no_drop", false);
			cir.setReturnValue(new LinkedList<>());
		}
	}
}
