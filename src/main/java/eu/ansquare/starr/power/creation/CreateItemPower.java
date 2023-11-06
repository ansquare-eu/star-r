package eu.ansquare.starr.power.creation;

import eu.ansquare.starr.power.Power;
import eu.ansquare.starr.util.item.ItemUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;

public class CreateItemPower extends Power {
	public ItemConvertible item;
	public int count;
	public boolean sign;
	public CreateItemPower(ItemConvertible item, int count, boolean sign){
		this.item = item;
		this.count = count;
		this.sign = sign;
	}
	@Override
	public void onActivate(ServerPlayerEntity player) {
		ItemStack stack = new ItemStack(item, count);
		if(sign){
			ItemUtils.sign(stack, player.getUuid());
		}
		player.getInventory().insertStack(stack);
	}
}
