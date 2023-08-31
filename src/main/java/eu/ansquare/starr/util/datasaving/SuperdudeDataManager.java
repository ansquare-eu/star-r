package eu.ansquare.starr.util.datasaving;

import eu.ansquare.starr.superdude.SuperDude;
import eu.ansquare.starr.superdude.SuperDudes;
import net.minecraft.nbt.NbtCompound;

public class SuperdudeDataManager {
	public static void set(IDataSaver dataSaver, String superType){
		NbtCompound nbt = dataSaver.getEntityData();
		nbt.putString("superType", superType);
	}
	public static SuperDude get(IDataSaver dataSaver){
		NbtCompound nbt = dataSaver.getEntityData();
		String superType = nbt.getString("superType");
		SuperDude superDude = SuperDudes.getSuperDude(superType);
		return superDude;
	}
}
