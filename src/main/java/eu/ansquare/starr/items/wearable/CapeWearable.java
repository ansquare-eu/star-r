package eu.ansquare.starr.items.wearable;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class CapeWearable extends TwoStateWearable{
	public CapeWearable(Settings settings, String texture, String secondtexture, String name, TwoStateModelProvider modelProvider) {
		super(settings, texture, secondtexture, name, modelProvider,((stack, abstractClientPlayerEntityPlayerEntityModel, abstractClientPlayerEntity, integer) -> {}));
	}
	public boolean getCapeState(ItemStack stack) {
		return stack.getOrCreateNbt().getBoolean("capestate");
	}
	public void toggleCapeState(ItemStack stack, boolean sneaking) {
		if (sneaking) {
			NbtCompound compound = stack.getOrCreateNbt();
			compound.putBoolean("capestate", !compound.getBoolean("capestate"));
		}
	}
}
