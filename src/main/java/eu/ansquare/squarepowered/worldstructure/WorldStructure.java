package eu.ansquare.squarepowered.worldstructure;

import io.github.apace100.calio.data.SerializableData;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.*;

public class WorldStructure {
	private World world;
	private BlockPos centre;
	private List<BlockState> materials;
	private Map<BlockPos, Integer> BLOCKS = new HashMap<>();
	public void add(BlockPos e, int i){
		this.BLOCKS.put(e, i);
	}
	public void construct(World world, BlockPos centre, List<BlockState> blocks){
		this.world = world;
		this.centre = centre;
		this.materials = blocks;

		for (BlockPos pos : this.BLOCKS.keySet()) {
			BlockPos finalPos = centre.add(pos);
			if(this.world.getBlockState(finalPos).isAir()){
				this.world.setBlockState(finalPos, blocks.get(BLOCKS.get(pos)));
			}
		}
	}
	public void delete(){
		for (BlockPos pos : this.BLOCKS.keySet()) {
			if(materials.contains(world.getBlockState(centre.add(pos))))this.world.setBlockState(centre.add(pos), Blocks.AIR.getDefaultState());
		}
	}

	public static WorldStructure hollowCube(SerializableData.Instance data) {
		WorldStructure worldStructure = new WorldStructure();
		int dim = Math.floorDiv(data.getInt("diameter"), 2);

		for (int i = -dim; i <= dim; i++) {
			for (int j = -dim; j <= dim; j++) {
				for (int k = -dim; k <= dim; k++) {
					if (i == -dim || i == dim || j == -dim || j == dim || k == -dim || k == dim) {
						BlockPos pos = new BlockPos(i, j, k);

							worldStructure.add(pos, 0);
					}
				}
			}
		}
		return worldStructure;
	}
}
