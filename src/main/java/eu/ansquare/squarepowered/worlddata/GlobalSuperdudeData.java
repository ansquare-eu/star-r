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

import java.util.HashSet;
import java.util.Set;

public class GlobalSuperdudeData extends PersistentState{
	private Set<Identifier> takens = new HashSet<>();
	public GlobalSuperdudeData(){

	}
	protected GlobalSuperdudeData(NbtCompound nbt){
		NbtList list = nbt.getList("taken", NbtElement.STRING_TYPE);
		list.forEach(nbtElement -> {
			if(nbtElement instanceof NbtString string){
				takens.add(new Identifier(string.asString()));
			}
		});
	}
	@Override
	public NbtCompound writeNbt(NbtCompound nbt) {
		NbtList list = new NbtList();
		takens.forEach(identifier -> {
			list.add(NbtString.of(identifier.toString()));
		});
		nbt.put("taken", list);
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
}
