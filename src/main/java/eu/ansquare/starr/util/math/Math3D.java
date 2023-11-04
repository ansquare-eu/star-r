package eu.ansquare.starr.util.math;

import eu.ansquare.starr.StarR;
import net.minecraft.util.math.Vec3d;
import org.quiltmc.loader.impl.lib.sat4j.core.Vec;

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
}
