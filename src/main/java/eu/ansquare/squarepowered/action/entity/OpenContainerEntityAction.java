package eu.ansquare.squarepowered.action.entity;

import eu.ansquare.squarepowered.SquareRegistries;
import eu.ansquare.squarepowered.Squarepowered;
import eu.ansquare.squarepowered.cca.SquareEntityComponents;
import eu.ansquare.starr.util.item.ItemUtils;
import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.quiltmc.qsl.networking.api.PacketByteBufs;

public class OpenContainerEntityAction {
	public static void action(SerializableData.Instance data, Entity entity) {
		if (entity instanceof ServerPlayerEntity player) {
			player.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, playerInventory, playerx) -> {
				GenericContainerScreenHandler handler = ScreenHandlerType.GENERIC_9X3.create(syncId, playerInventory);
				Inventory inventory = handler.getInventory();
				ItemUtils.populate(inventory, data.get("stacks"), data.getBoolean("sign") ? playerInventory.player.getUuid() : null);
				return handler;
			}, Text.translatable(data.getString("translation_key"))));
		}
	}

	public static ActionFactory<Entity> getOpenActionScreenFactory() {
		return new ActionFactory<>(Squarepowered.id("open_container_screen"),
				new SerializableData()
						.add("stacks", SerializableDataTypes.ITEM_STACKS)
						.add("translation_key", SerializableDataTypes.STRING)
						.add("sign", SerializableDataTypes.BOOLEAN),
				OpenContainerEntityAction::action
		);
	}
}
