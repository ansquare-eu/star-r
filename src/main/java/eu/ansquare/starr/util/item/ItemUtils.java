package eu.ansquare.starr.util.item;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.UUID;

public class ItemUtils {
	public static final String SIGN_KEY = "starr:owner";
	public static Inventory populate(Inventory inventory, ItemArrayProvider provider){
		Item[] items = provider.get();
		for(int i = 0; i < items.length; i++){
			Item item = items[i];
			inventory.setStack(i, new ItemStack(item, 1));
		}
		return inventory;
	}
	public static Inventory populateSigned(Inventory inventory, ItemArrayProvider provider, UUID owner){
		Item[] items = provider.get();
		for(int i = 0; i < items.length; i++){
			Item item = items[i];
			ItemStack stack = new ItemStack(item, 1);
			inventory.setStack(i, ItemUtils.sign(stack, owner));
		}
		return inventory;
	}
	public static ItemStack sign(ItemStack stack, UUID owner){
		stack.getOrCreateNbt().putUuid(SIGN_KEY, owner);
		return stack;
	}
}
