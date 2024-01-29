package eu.ansquare.squarepowered.action;

import eu.ansquare.squarepowered.Squarepowered;
import eu.ansquare.squarepowered.util.WorldSecurity;
import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class TpToDimensionAction {
	public static void action(SerializableData.Instance data, Entity entity){
		RegistryKey<World> worldRegistryKey = data.get("dimension");
		if(entity instanceof ServerPlayerEntity player){
			player.teleport(player.getServer().getWorld(worldRegistryKey), data.getDouble("x"), data.getDouble("y"), data.getDouble("z"), player.getYaw(), player.getPitch());
			if(data.getBoolean("platformIfUnsupported") && player.getServerWorld().getBlockState(player.getBlockPos().down()).isAir()){
				BlockState state = data.get("block");
				WorldSecurity.changeBlockState(state, player.getBlockPos().down(), player.getServerWorld(), player, true, true, false, false);
			}
		}
	}
	public static ActionFactory<Entity> getFactory(){
		return new ActionFactory<>(Squarepowered.id("tp_dimension"),
				new SerializableData()
						.add("x", SerializableDataTypes.DOUBLE, 0.0d)
						.add("y", SerializableDataTypes.DOUBLE, 0.0d)
						.add("z", SerializableDataTypes.DOUBLE, 0.0d)
						.add("dimension",SerializableDataTypes.DIMENSION)
						.add("platformIfUnsupported", SerializableDataTypes.BOOLEAN, false)
						.add("block", SerializableDataTypes.BLOCK_STATE, Blocks.STONE.getDefaultState()),
				TpToDimensionAction::action);
	}
}
