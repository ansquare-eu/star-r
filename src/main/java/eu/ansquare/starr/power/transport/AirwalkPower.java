package eu.ansquare.starr.power.transport;

import eu.ansquare.starr.power.ToggleablePower;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

import java.util.HashMap;
import java.util.Map;

public class AirwalkPower extends ToggleablePower {
	public Block block;
	public int delay;
	public Map<BlockPos, Integer> map = new HashMap<>();

	public AirwalkPower(Block block, int delay){
		this.block = block;
		this.delay = delay;
	}

	@Override
	public void activationAction(ServerPlayerEntity player) {

	}

	@Override
	public void deactivationAction(ServerPlayerEntity player) {
		map.keySet().forEach(blockPos -> player.getServerWorld().setBlockState(blockPos, Blocks.AIR.getDefaultState()));
	}

	@Override
	public void activeTick(LivingEntity entity) {
		map.keySet().forEach(blockPos -> {
			Integer i = map.get(blockPos) - 1;
			map.put(blockPos, i);
			if(i.intValue() <= 0){
				entity.getWorld().setBlockState(blockPos, Blocks.AIR.getDefaultState());
			}
		});
		if(entity.getWorld().getBlockState(entity.getBlockPos().down()).isAir()){
			entity.getWorld().setBlockState(entity.getBlockPos().down(), block.getDefaultState());
			map.put(entity.getBlockPos().down(), delay);
		}
	}
}
