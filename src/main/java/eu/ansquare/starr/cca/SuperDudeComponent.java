package eu.ansquare.starr.cca;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import eu.ansquare.starr.power.LaserPower;
import eu.ansquare.starr.power.Power;
import eu.ansquare.starr.power.Powers;
import eu.ansquare.starr.superdude.PowerOrder;
import eu.ansquare.starr.superdude.SuperDude;
import eu.ansquare.starr.superdude.SuperDudes;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class SuperDudeComponent implements AutoSyncedComponent {
	private SuperDude type;
	@Override
	public void readFromNbt(NbtCompound tag) {
		this.type = SuperDudes.getSuperDude(new Identifier(tag.getString("superTypeN")));
	}
	public SuperDude getType(){
		if(type != null){
			return type;
		}
		return SuperDudes.EMPTY;
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
	public int getLaserPower(){
		for(PowerOrder powerOrder : this.getType().getPowers().keySet()){
			if(this.getPower(powerOrder) instanceof LaserPower){
				return ((LaserPower) this.getPower(powerOrder)).getDamage();
			}
		}
		return 0;
	}
	public boolean canFly(){
		return this.getType().flying;
	}
	@Override
	public void writeToNbt(NbtCompound tag) {
		tag.putString("superTypeN", this.getType().id.toString());
	}
}
