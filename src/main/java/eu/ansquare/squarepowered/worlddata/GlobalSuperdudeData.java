package eu.ansquare.squarepowered.worlddata;

import eu.ansquare.squarepowered.Squarepowered;
import eu.ansquare.squarepowered.util.SquareMiscUtils;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.World;

import java.util.*;

public class GlobalSuperdudeData extends PersistentState{
	private Map<UUID, Identifier> map = new HashMap<>();

	public GlobalSuperdudeData(){

	}
	protected GlobalSuperdudeData(NbtCompound nbt){
		NbtList listForMap = nbt.getList("map", NbtElement.COMPOUND_TYPE);
		listForMap.forEach(nbtElement -> {
			if(nbtElement instanceof NbtCompound compound){
				map.put(compound.getUuid("player"), new Identifier(compound.getString("id")));
			}
		});
	}
	@Override
	public NbtCompound writeNbt(NbtCompound nbt) {
		NbtList listFromMap = new NbtList();
		map.forEach((uuid, identifier) -> {
			NbtCompound compound = new NbtCompound();
			compound.putUuid("player", uuid);
			compound.putString("id", identifier.toString());
			listFromMap.add(compound);
		});
		nbt.put("map", listFromMap);
		return nbt;
	}

	public static GlobalSuperdudeData createFromNbt(NbtCompound tag) {
		GlobalSuperdudeData state = new GlobalSuperdudeData(tag);
		return state;
	}
	public static GlobalSuperdudeData getServerState(MinecraftServer server){
		PersistentStateManager persistentStateManager = server.getWorld(World.OVERWORLD).getPersistentStateManager();
		GlobalSuperdudeData globalSuperdudeData = persistentStateManager.getOrCreate(GlobalSuperdudeData::createFromNbt, GlobalSuperdudeData::new, "global_superdude_data");
		globalSuperdudeData.markDirty();
		return globalSuperdudeData;
	}
	public static boolean isTaken(MinecraftServer server, Identifier id){
		GlobalSuperdudeData globalSuperdudeData = getServerState(server);
		return globalSuperdudeData.map.containsValue(id);
	}
	public static boolean putOrReplace(MinecraftServer server, UUID uuid, Identifier replaceWith){
		GlobalSuperdudeData globalSuperdudeData = getServerState(server);
		boolean hasContained = globalSuperdudeData.map.containsKey(uuid);
		globalSuperdudeData.map.put(uuid, replaceWith);
		return hasContained;
	}
}
