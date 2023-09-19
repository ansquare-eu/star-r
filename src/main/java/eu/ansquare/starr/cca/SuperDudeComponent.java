package eu.ansquare.starr.cca;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import eu.ansquare.starr.power.Power;
import eu.ansquare.starr.power.Powers;
import eu.ansquare.starr.superdude.PowerOrder;
import eu.ansquare.starr.superdude.SuperDude;
import eu.ansquare.starr.superdude.SuperDudes;
import net.minecraft.nbt.NbtCompound;

import java.awt.*;

public class SuperDudeComponent implements AutoSyncedComponent {
	private SuperDude type;
	@Override
	public void readFromNbt(NbtCompound tag) {
		this.type = SuperDudes.getSuperDude(tag.getString("superTypeN"));
	}
	public SuperDude getType(){
		if(type != null){
			return type;
		}
		return SuperDudes.EMPTY;
	}
	public boolean isFlying(){
		return this.getType().flying;
	}
	public void setType(SuperDude type){
		this.type = type;
	}
	public Power getPower(PowerOrder order){
		if(type != null){
			if(type.hasPower(order)){
				return type.getPower(order);
			}
		}
		return Powers.EMPTY_POWER;
	}
	public Color getCustomColor(){
		return this.getType().color;
	}
	@Override
	public void writeToNbt(NbtCompound tag) {
		tag.putString("superTypeN", this.getType().getName());
	}
}
