package eu.ansquare.starr.cca;

import dev.onyxstudios.cca.api.v3.component.Component;
import eu.ansquare.starr.util.math.UselessNumberHolder;
import eu.ansquare.starr.util.power.SavedLocOrder;
import net.minecraft.nbt.NbtCompound;

import java.awt.*;

public class TeleportLocComponent implements Component {
	public int[] base;
	public int[] first;
	public int[] second;
	public int[] getLoc(SavedLocOrder order){
		int[] maybeLoc;
		switch (order){
			case FIRST -> {
				maybeLoc = first;
			}
			case SECOND -> {
				maybeLoc = second;
			}
			default -> {
				maybeLoc = base;
			}
		}
		return maybeLoc;
	}
	@Override
	public void readFromNbt(NbtCompound tag) {
		this.base = tag.getIntArray("base");
		this.base = tag.getIntArray("first");
		this.base = tag.getIntArray("second");
	}

	@Override
	public void writeToNbt(NbtCompound tag) {
		tag.putIntArray("base", this.base);
		tag.putIntArray("first", this.first);
		tag.putIntArray("second", this.second);
	}
}
