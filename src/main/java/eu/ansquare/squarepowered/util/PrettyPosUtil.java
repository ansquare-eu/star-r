package eu.ansquare.squarepowered.util;

import net.minecraft.util.math.BlockPos;

public class PrettyPosUtil {
	public static String colonSeparatedBlockPos(BlockPos pos){
		StringBuilder builder = new StringBuilder();
		builder.append(pos.getX());
		builder.append(":");
		builder.append(pos.getY());
		builder.append(":");
		builder.append(pos.getZ());
		return builder.toString();
	}
}
