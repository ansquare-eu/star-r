package eu.ansquare.starr.blocks;

import eu.ansquare.starr.util.math.Math3D;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class WorldAnchorBlock extends Block {
	public final boolean isSpatial;
	public WorldAnchorBlock(Settings settings, boolean spatial) {
		super(settings);
		this.isSpatial = spatial;
	}
	public static boolean isInVicinity(int range, boolean spatial, BlockPos loc, World world){
		Box box = new Box(loc).expand(range);
		return Math3D.boxSearch(box, (blockPos -> {
			if(world.getBlockState(blockPos).getBlock() instanceof WorldAnchorBlock block){
				return block.isSpatial == spatial;
			}
			return false;
		}));
	}
}
