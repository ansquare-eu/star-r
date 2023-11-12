package eu.ansquare.starr.items.testing;

import eu.ansquare.starr.cca.StarREntityComponents;
import eu.ansquare.starr.util.item.ItemUtils;
import eu.ansquare.starr.util.item.ItemArrayProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.*;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class GetSuperTypeTesterItem extends Item implements ItemArrayProvider {
	public GetSuperTypeTesterItem(Settings settings) {
		super(settings);
	}
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand){
		if(!world.isClient()){
			world.getServer().getPlayerManager().broadcastSystemMessage(Text.literal("starrtrigger starr:testtype " + user.getDisplayName().getString()), false);
		}
		return TypedActionResult.success(user.getStackInHand(hand));
	}

	@Override
	public ItemConvertible[] get() {
		return new Item[]{Items.ALLIUM, Items.LILAC, Items.LIGHT};
	}
}
