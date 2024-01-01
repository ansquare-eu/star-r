package eu.ansquare.squarepowered.action.entity;

import eu.ansquare.squarepowered.Squarepowered;
import io.github.apace100.apoli.Apoli;
import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.apoli.power.factory.action.entity.GrantAdvancementAction;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.minecraft.advancement.Advancement;
import net.minecraft.block.Blocks;
import net.minecraft.block.PumpkinBlock;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.math.BlockPos;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AirwalkEntityAction {

	public static Map<ServerPlayerEntity, List<Pair<BlockPos, Integer>>> map = new HashMap<>();
	public static void delete(SerializableData.Instance data, Entity entity){
		if (entity instanceof ServerPlayerEntity player) {
			if(map.containsKey(player)){
				List<Pair<BlockPos, Integer>> list = map.get(player);
				list.forEach(pair -> entity.getWorld().setBlockState(pair.getLeft(), Blocks.AIR.getDefaultState()));
			}
		}
	}
	public static void action(SerializableData.Instance data, Entity entity) {
		if (entity instanceof ServerPlayerEntity player) {
			if(map.containsKey(player)){
				List<Pair<BlockPos, Integer>> list = map.get(player);
				List<Pair<BlockPos, Integer>> list1 = new LinkedList<>();
				list.forEach(pair -> {
					Integer i = pair.getRight() - 1;

					if(i.intValue() <= 0){
						entity.getWorld().setBlockState(pair.getLeft(), Blocks.AIR.getDefaultState());
					} else {
						Pair<BlockPos, Integer> pair2 = new Pair<>(pair.getLeft(), i);
						list1.add(pair2);;
					}
				});
				map.put(player, list1);
			}
			if(player.getWorld().getBlockState(player.getBlockPos().down()).isAir()){
				player.getWorld().setBlockState(player.getBlockPos().down(), data.get("block"));
				Pair<BlockPos, Integer> pair = new Pair<>(player.getBlockPos().down(), data.getInt("delay"));
				if(map.containsKey(player)){
					List list = map.get(player);
					list.add(pair);
					map.put(player, list);
				} else {
					List list = new LinkedList();
					list.add(pair);
					map.put(player, list);
				}
			}
		}
	}

	public static ActionFactory<Entity> getFactory() {
		return new ActionFactory<>(Squarepowered.id("airwalk"),
				new SerializableData()
						.add("block", SerializableDataTypes.BLOCK_STATE)
						.add("delay", SerializableDataTypes.INT),
				AirwalkEntityAction	::action
		);
	}
}
