package eu.ansquare.squarepowered.util;

import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

import java.util.Collection;

public class SquareMiscUtils {
	public static boolean boolFromInt(int i){
		return i != 0;
	}
	public static void teleport(ServerPlayerEntity entity, ServerWorld world, Vec3d vec3d){
		entity.teleport(world, vec3d.x, vec3d.y, vec3d.z, entity.getYaw(), entity.getPitch());
	}
	public static void teleportTo(ServerPlayerEntity entity, Entity to){
		entity.teleport((ServerWorld) to.getWorld(), to.getX(), to.getY(), to.getZ(), to.getYaw(), to.getPitch());
	}
	public static void stringsToIds(Collection<String> from, Collection<Identifier> to){
		from.forEach(s -> to.add(new Identifier(s)));
	}
	public static void idsToStrings(Collection<Identifier> from, Collection<String> to){
		from.forEach(identifier -> to.add(identifier.toString()));
	}
}
