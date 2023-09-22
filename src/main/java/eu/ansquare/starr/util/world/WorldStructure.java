package eu.ansquare.starr.util.world;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.Set;

public class WorldStructure {
	private World world;
	private Set<BlockPos> BLOCKS = new HashSet<>();
	public WorldStructure(World world){
		this.world = world;
	}
	public void add(BlockPos e){
		this.BLOCKS.add(e);
	}
	public void construct(Block block){
		for (BlockPos pos : this.BLOCKS) {
			this.world.setBlockState(pos, block.getDefaultState());
		}
	}
	public void delete(){
		for (BlockPos pos : this.BLOCKS) {
			this.world.setBlockState(pos, Blocks.AIR.getDefaultState());
		}
	}

	public static WorldStructure hollowCube(BlockPos centre, int diameter, Block block, World world){
		WorldStructure worldStructure = new WorldStructure(world);
		int dim = Math.floorDiv(diameter, 2);
		int minX = centre.getX() - dim;
		int minY = centre.getY() - dim;
		int minZ = centre.getZ() - dim;
		int maxX = centre.getX() + dim;
		int maxY = centre.getY() + dim;
		int maxZ = centre.getZ() + dim;
		for(int i = minX; i <= maxX; i++){
			for(int j = minY; j <= maxY; j++){
				for(int k = minZ; k <= maxZ; k++) {
					if(i == minX || i == maxX || j == minY || j == maxY || k == minZ || k == maxZ){
						BlockPos pos = new BlockPos(i, j, k);
						if(world.isAir(pos)){
							worldStructure.add(pos);
						}
					}
				}
			}
		}
		worldStructure.construct(block);
		return worldStructure;
	}
}
