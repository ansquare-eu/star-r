package eu.ansquare.starr.items.testing;

import eu.ansquare.starr.superdude.SuperDude;
import eu.ansquare.starr.util.datasaving.IDataSaver;
import eu.ansquare.starr.util.datasaving.SuperdudeDataManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ResetSuperTypeTesterItem extends Item {
	public ResetSuperTypeTesterItem(Settings settings) {
		super(settings);
	}
	@Override

	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand){
		if(!world.isClient()){
			SuperdudeDataManager.set((IDataSaver) user, "");
		}
		return TypedActionResult.success(user.getStackInHand(hand));
	}
}
