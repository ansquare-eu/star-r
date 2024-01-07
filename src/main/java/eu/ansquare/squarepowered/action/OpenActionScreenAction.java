package eu.ansquare.squarepowered.action;

import eu.ansquare.squarepowered.SquareRegistries;
import eu.ansquare.squarepowered.Squarepowered;
import eu.ansquare.squarepowered.cca.SquareEntityComponents;
import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.minecraft.entity.Entity;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class OpenActionScreenAction {
	public static void action(SerializableData.Instance data, Entity entity){
		ScreenHandlerType type = SquareRegistries.ACTION_SCREEN.get(data.getId("screen"));
		if(entity instanceof ServerPlayerEntity player){
			SquareEntityComponents.SAVED_LOCATION_COMPONENT.sync(player);
			player.openHandledScreen(new SimpleNamedScreenHandlerFactory(((i, playerInventory, playerEntity) -> type.create(i, playerInventory)), Text.translatable("screen.starr."+data.getId("screen").getPath()+".title")));
		}
	}
	public static ActionFactory<net.minecraft.entity.Entity> getOpenActionScreenFactory() {
		return new ActionFactory<>(Squarepowered.id("open_action_screen"),
				new SerializableData()
						.add("screen", SerializableDataTypes.IDENTIFIER),
				OpenActionScreenAction::action
		);
	}
}
