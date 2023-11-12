package eu.ansquare.starr.blocks;

import eu.ansquare.starr.items.ItemRandomizer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.state.StateManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Random;

public class RandomKuforBlock extends HorizontalFacingBlock implements ItemRandomizer {
	public ItemConvertible[] bannedItems;
	public RandomKuforBlock(Settings settings, ItemConvertible[] items) {
		super(settings);
		setDefaultState(getDefaultState().with(FACING, Direction.WEST));
		this.bannedItems = items;

	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		Vec3d offset = state.getModelOffset(world, pos);
		return (switch (state.get(FACING)) {
			default -> createCuboidShape(1, 0, 6, 15, 9, 10);
			case EAST, WEST -> createCuboidShape(6, 0, 1, 10, 9, 15);
		}).offset(offset.x, offset.y, offset.z);
	}
	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		if (!world.isClient) {
			Item item = generate();
			player.getInventory().insertStack(new ItemStack(item));
		}

		return ActionResult.SUCCESS;
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext context) {
		return this.getDefaultState().with(FACING, context.getPlayerFacing().getOpposite());
	}

	@Override
	public ItemConvertible[] get() {
		return bannedItems;
	}
}
