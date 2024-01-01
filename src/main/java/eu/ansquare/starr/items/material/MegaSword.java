package eu.ansquare.starr.items.material;

import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class MegaSword implements ToolMaterial, ItemColorProvider {
	@Override
	public int getDurability() {
		return 1;
	}

	@Override
	public float getMiningSpeedMultiplier() {
		return 0;
	}

	@Override
	public float getAttackDamage() {
		return 0;
	}

	@Override
	public int getMiningLevel() {
		return 0;
	}

	@Override
	public int getEnchantability() {
		return 0;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return Ingredient.EMPTY;
	}

	@Override
	public int getColor(ItemStack itemStack, int i) {
		return itemStack.getOrCreateNbt().getInt("color");
	}
}
