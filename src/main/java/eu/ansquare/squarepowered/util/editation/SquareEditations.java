package eu.ansquare.squarepowered.util.editation;

import eu.ansquare.squarepowered.SquareRegistries;
import eu.ansquare.squarepowered.Squarepowered;
import eu.ansquare.squarepowered.cca.SavedLocationComponent;
import eu.ansquare.squarepowered.power.*;
import eu.ansquare.squarepowered.util.PrettyPosUtil;
import eu.ansquare.squarepowered.util.WorldSecurity;
import eu.ansquare.squarepowered.util.changelogging.ChangeLogger;
import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.apoli.registry.ApoliRegistries;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class SquareEditations {
	public static void init(){
		registerTwoPoint(((one, two, block, player) -> {
			ServerWorld world = player.getServerWorld();
			Block block1 = Registries.BLOCK.get(block);
			BlockState state = block1.getDefaultState();
			ChangeLogger logger = ChangeLogger.getOrCreate(player);
			logger.open();
			int minX = Math.min(one.getX(), two.getX());
			int minY = Math.min(one.getY(), two.getY());
			int minZ = Math.min(one.getZ(), two.getZ());
			int maxX = Math.max(one.getX(), two.getX());
			int maxY = Math.max(one.getY(), two.getY());
			int maxZ = Math.max(one.getZ(), two.getZ());
			for (int x = minX; x <= maxX; x++) {
				for (int y = minY; y <= maxY; y++) {
					for (int z = minZ; z <= maxZ; z++) {
						BlockPos b = new BlockPos(x, y, z);
						WorldSecurity.changeBlockState(state, b, world, player, true, true, true, true);
					}
				}
			}
			logger.close();
		}), Squarepowered.id("set"));
		registerTwoPoint(((one, two, block, player) -> {
			ServerWorld world = player.getServerWorld();
			Block block1 = Registries.BLOCK.get(block);
			BlockState state = block1.getDefaultState();
			ChangeLogger logger = ChangeLogger.getOrCreate(player);
			logger.open();
			int minX = Math.min(one.getX(), two.getX());
			int minY = Math.min(one.getY(), two.getY());
			int minZ = Math.min(one.getZ(), two.getZ());
			int maxX = Math.max(one.getX(), two.getX());
			int maxY = Math.max(one.getY(), two.getY());
			int maxZ = Math.max(one.getZ(), two.getZ());
			for (int x = minX; x <= maxX; x++) {
				for (int y = minY; y <= maxY; y++) {
					for (int z = minZ; z <= maxZ; z++) {
						if(x == minX || x == maxX || y == minY || y == maxY || z == minZ || z == maxZ){
							BlockPos b = new BlockPos(x, y, z);
							WorldSecurity.changeBlockState(state, b, world, player, true, true, true, true);
						}
					}
				}
			}
			logger.close();
		}), Squarepowered.id("outline"));
		registerTwoPoint(((one, two, block, player) -> {
			ServerWorld world = player.getServerWorld();
			Block block1 = Registries.BLOCK.get(block);
			BlockState state = block1.getDefaultState();
			ChangeLogger logger = ChangeLogger.getOrCreate(player);
			logger.open();
			int minX = Math.min(one.getX(), two.getX());
			int minY = Math.min(one.getY(), two.getY());
			int minZ = Math.min(one.getZ(), two.getZ());
			int maxX = Math.max(one.getX(), two.getX());
			int maxY = Math.max(one.getY(), two.getY());
			int maxZ = Math.max(one.getZ(), two.getZ());
			for (int x = minX; x <= maxX; x++) {
				for (int y = minY; y <= maxY; y++) {
					for (int z = minZ; z <= maxZ; z++) {
						if(x == minX || x == maxX || z == minZ || z == maxZ){
							BlockPos b = new BlockPos(x, y, z);
							WorldSecurity.changeBlockState(state, b, world, player, true, true, true, true);
						}
					}
				}
			}
			logger.close();
		}), Squarepowered.id("walls"));
	}
	private static void registerTwoPoint(TwoPointWorldEditation editation, Identifier id) {
		Registry.register(SquareRegistries.EDITATIONS, id, editation);
	}
}
