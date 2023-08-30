package eu.ansquare.starr.items.testing;

import eu.ansquare.starr.util.datasaving.IDataSaver;
import eu.ansquare.starr.util.datasaving.SuperdudeDataManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class GetSuperTypeTesterItem extends Item {
	public GetSuperTypeTesterItem(Settings settings) {
		super(settings);
	}
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand){
		if(!world.isClient()){
			String type = SuperdudeDataManager.get((IDataSaver) user);
			if(type.equalsIgnoreCase("testtype")){
				user.damage(user.getDamageSources().magic(), 5);
			}

		}
		return TypedActionResult.success(user.getStackInHand(hand));
	}
}
