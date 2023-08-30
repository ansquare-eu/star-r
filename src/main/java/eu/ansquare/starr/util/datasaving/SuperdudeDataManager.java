package eu.ansquare.starr.util.datasaving;

import net.minecraft.nbt.NbtCompound;

public class SuperdudeDataManager {
	public static void set(IDataSaver dataSaver, String superType){
		NbtCompound nbt = dataSaver.getEntityData();
		nbt.putString("superType", superType);
	}
	public static String get(IDataSaver dataSaver){
		NbtCompound nbt = dataSaver.getEntityData();
		String superType = nbt.getString("superType");
		return superType;
	}
}
