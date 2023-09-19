package eu.ansquare.starr.items.testing;

import eu.ansquare.starr.cca.StarREntityComponents;
import eu.ansquare.starr.superdude.SuperDude;
import eu.ansquare.starr.superdude.SuperDudes;
import eu.ansquare.starr.util.datasaving.IDataSaver;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
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
			StarREntityComponents.SUPER_DUDE_COMPONENT.maybeGet(user).ifPresent(superDudeComponent -> {
				String message = superDudeComponent.getType().queryMessage();
				user.sendMessage(Text.literal(message), false);
			});

		}
		return TypedActionResult.success(user.getStackInHand(hand));
	}
}
