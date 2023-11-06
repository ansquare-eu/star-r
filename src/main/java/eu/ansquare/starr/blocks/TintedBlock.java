package eu.ansquare.starr.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import org.jetbrains.annotations.Nullable;

public class TintedBlock extends Block implements BlockColorProvider {
	public final int[] colors;
	public TintedBlock(Settings settings, int... colors) {
		super(settings);
		this.colors = colors;
	}

	@Override
	public int getColor(BlockState blockState, @Nullable BlockRenderView blockRenderView, @Nullable BlockPos blockPos, int i) {
		if(colors.length < i){
			return 0xFFFFFF;
		}
		return colors[i];
	}
}
