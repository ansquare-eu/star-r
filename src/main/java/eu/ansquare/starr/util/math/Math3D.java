package eu.ansquare.starr.util.math;

import eu.ansquare.starr.StarR;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.quiltmc.loader.impl.lib.sat4j.core.Vec;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class Math3D {
	public static Vec3d midpoint(Vec3d start, Vec3d end, double modifier){
		double x = (end.getX() - start.getX()) * modifier;
		double y = (end.getY() - start.getY()) * modifier;
		double z = (end.getZ() - start.getZ()) * modifier;
		Vec3d midpoint = new Vec3d(start.x + x, start.y + y , start.z + z);
		return midpoint;
	}
	public static void logPos(Vec3d pos){
		StarR.LOGGER.info(pos.toString());
	}
	public static boolean predicateBoxIterator(Box box, Predicate<BlockPos> predicate){
		int maxX = (int) box.maxX;
		int maxY = (int) box.maxY;
		int maxZ = (int) box.maxZ;
		int minX = (int) box.minX;
		int minY = (int) box.minY;
		int minZ = (int) box.minZ;
		for (int x = minX; x < maxX; x++){
			for (int y = minY; y < maxY; y++){
				for (int z = minZ; z < maxZ; z++){
					if(predicate.test(new BlockPos(x, y ,z))){
						return true;
					}
				}
			}
		}
		return false;
	}
}
