package eu.ansquare.starr.cca;

import dev.onyxstudios.cca.api.v3.component.Component;
import net.minecraft.nbt.NbtCompound;

public class TeleportLocComponent implements Component {
	public int[] base;
	public int[] first;
	public int[] second;
	public int[] getLoc(int i){
		int[] maybeLoc;
		switch (i){
			case 1 -> {
				maybeLoc = first;
			}
			case 2 -> {
				maybeLoc = second;
			}
			default -> {
				maybeLoc = base;
			}
		}
		if(maybeLoc != null){
			return maybeLoc;
		}
		else {
			return new int[] {0, 0, 0};
		}
	}
	public void writeLoc(int i, int[] loc){
		switch (i){
			case 1 -> {
				first = loc;
			}
			case 2 -> {
				second = loc;
			}
			default -> {
				base = loc;
			}
		}
	}
	@Override
	public void readFromNbt(NbtCompound tag) {
		this.base = tag.getIntArray("base");
		this.first = tag.getIntArray("first");
		this.second = tag.getIntArray("second");
	}

	@Override
	public void writeToNbt(NbtCompound tag) {
		tag.putIntArray("base", this.getLoc(0));
		tag.putIntArray("first", this.getLoc(1));
		tag.putIntArray("second", this.getLoc(2));
	}
}
