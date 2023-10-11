package eu.ansquare.starr.util.inventory;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class InventoryPopulator {
	public static Inventory populate(Inventory inventory, ItemArrayProvider provider){
		Item[] items = provider.get();
		for(int i = 0; i < items.length; i++){
			Item item = items[i];
			inventory.setStack(i, new ItemStack(item, 1));
		}
		return inventory;
	}
}
