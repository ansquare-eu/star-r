package eu.ansquare.starr.blocks;

import eu.ansquare.starr.util.math.Math3D;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class WorldAnchorBlock extends Block {
	public final boolean isSpatial;
	public WorldAnchorBlock(Settings settings, boolean spatial) {
		super(settings);
		this.isSpatial = spatial;
	}
	public static boolean isInVicinity(boolean spatial, BlockPos loc, World world){
		Box box = new Box(loc).expand(8);
		return Math3D.predicateBoxIterator(box, (blockPos -> {
			if(world.getBlockState(blockPos).getBlock() instanceof WorldAnchorBlock block){
				return block.isSpatial == spatial;
			}
			return false;
		}));
	}
}
