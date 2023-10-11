package eu.ansquare.starr.power;

import eu.ansquare.starr.cca.StarREntityComponents;
import eu.ansquare.starr.superdude.SuperDude;
import eu.ansquare.starr.util.inventory.InventoryPopulator;
import eu.ansquare.starr.util.inventory.ItemArrayProvider;
import net.minecraft.item.Item;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class ItemGuiPower extends Power {
	@Override
	public String getName() {
		return "itemgui";
	}

	@Override
	public void onActivate(ServerPlayerEntity player) {
		SuperDude superDude = StarREntityComponents.SUPER_DUDE_COMPONENT.get(player).getType();
		if (superDude instanceof ItemArrayProvider) {
			player.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, playerInventory, playerx) -> {
				GenericContainerScreenHandler handler = ScreenHandlerType.GENERIC_9X3.create(syncId, playerInventory);
				InventoryPopulator.populate(handler.getInventory(), (ItemArrayProvider) superDude);
				return handler;
			}, Text.literal("meme")));
		}
	}


}
