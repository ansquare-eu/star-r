package eu.ansquare.squarepowered.cca;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.math.BlockPos;

public class SavedLocationComponent implements AutoSyncedComponent {
	private NbtList list = new NbtList();
	public BlockPos get(int i){
		NbtElement e = list.get(i);
		if(i >= list.size()) return null;
		if(e instanceof NbtCompound compound){
			int x = compound.getInt("x");
			int y = compound.getInt("y");
			int z = compound.getInt("z");
			return new BlockPos(x, y ,z);
		}
		return null;
	}
	public void wipe(){
		list.clear();
	}
	public void put(int i, int x, int y, int z){
		NbtCompound compound = new NbtCompound();
		compound.putInt("x", x);
		compound.putInt("y", y);
		compound.putInt("z", z);
		if(i >= list.size())list.add(i, compound);
		if(i < list.size())list.set(i , compound);
	}
	public int amount(){
		return list.size();
	}
	@Override
	public void readFromNbt(NbtCompound tag) {
		list = tag.getList("list", 10);
	}

	@Override
	public void writeToNbt(NbtCompound tag) {
		tag.put("list", list);
	}
}
