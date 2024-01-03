package eu.ansquare.starr.util.item;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class ItemUtils {
	public static final String SIGN_KEY = "signlib:owner";
	public static Inventory populate(Inventory inventory, List<ItemStack> stacks, @Nullable UUID uuid){
		for(int i = 0; i < stacks.size(); i++){
			ItemStack stack = stacks.get(i);
			if(uuid != null) sign(stack, uuid);
			inventory.setStack(i, stack);
		}
		return inventory;
	}

	public static ItemStack sign(ItemStack stack, UUID owner){
		stack.getOrCreateNbt().putUuid(SIGN_KEY, owner);
		return stack;
	}
}
