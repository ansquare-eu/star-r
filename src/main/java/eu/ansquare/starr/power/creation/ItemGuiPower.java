package eu.ansquare.starr.power.creation;

import eu.ansquare.starr.cca.StarREntityComponents;
import eu.ansquare.starr.power.Power;
import eu.ansquare.starr.superdude.SuperDude;
import eu.ansquare.starr.util.item.ItemUtils;
import eu.ansquare.starr.util.item.ItemArrayProvider;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class ItemGuiPower extends Power {
	@Override
	public void onActivate(ServerPlayerEntity player) {
		SuperDude superDude = StarREntityComponents.SUPER_DUDE_COMPONENT.get(player).getType();
		if (superDude instanceof ItemArrayProvider) {
			player.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, playerInventory, playerx) -> {
				GenericContainerScreenHandler handler = ScreenHandlerType.GENERIC_9X3.create(syncId, playerInventory);
				ItemUtils.populateSigned(handler.getInventory(), (ItemArrayProvider) superDude, playerInventory.player.getUuid());
				return handler;
			}, Text.literal("meme")));
		}
	}


}
