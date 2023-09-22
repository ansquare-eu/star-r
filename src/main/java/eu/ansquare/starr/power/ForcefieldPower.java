package eu.ansquare.starr.power;

import eu.ansquare.starr.blocks.ModBlocks;
import eu.ansquare.starr.util.world.WorldStructure;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ForcefieldPower extends ToggleablePower{
	public Map<UUID, WorldStructure> structureMap = new HashMap<>();
	@Override
	public String getName() {
		return "forcefield";
	}

	@Override
	public void activationAction(ServerPlayerEntity player) {
		WorldStructure structure = WorldStructure.hollowCube(player.getBlockPos(), 8, ModBlocks.FORCEFIELD, player.getWorld());
		structureMap.put(player.getUuid() ,structure);
	}

	@Override
	public void deactivationAction(ServerPlayerEntity player) {
		if(structureMap.containsKey(player.getUuid())){
			WorldStructure structure = structureMap.get(player.getUuid());
			structure.delete();
			structureMap.remove(player.getUuid());
		}
	}

	@Override
	public void activeTick(LivingEntity entity) {

	}
}
