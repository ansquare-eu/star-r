package eu.ansquare.starr;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import eu.ansquare.squarepowered.cca.SquareEntityComponents;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.command.argument.ResourceKeyArgument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.command.api.CommandRegistrationCallback;

import static com.mojang.brigadier.arguments.StringArgumentType.*;
import static net.minecraft.command.argument.EntityArgumentType.entities;
import static net.minecraft.command.argument.EntityArgumentType.player;
import static net.minecraft.server.command.CommandManager.literal;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.*;
public class ModCommands {
	public static void init(){
		CommandRegistrationCallback.EVENT.register((dispatcher, buildContext, environment) -> dispatcher.register(literal("color")
				.then(literal("item")
						.then(argument("color", StringArgumentType.string())
								.requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(4))
								.executes(context -> {
									String hexcolor = context.getArgument("color", String.class);
									int color = Integer.parseInt(hexcolor, 16);
									PlayerEntity entity = context.getSource().getPlayer();
									ItemStack stack = entity.getStackInHand(Hand.MAIN_HAND);
									stack.getOrCreateNbt().putInt("color", color);
									return 1;
								})))));
		CommandRegistrationCallback.EVENT.register((dispatcher, buildContext, environment) -> dispatcher.register(literal("wipe")
				.then(literal("saved_locations").requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(4)).executes(context -> {
					ServerPlayerEntity player = context.getSource().getPlayerOrThrow();
					SquareEntityComponents.SAVED_LOCATION_COMPONENT.maybeGet(player).ifPresent(savedLocationComponent -> savedLocationComponent.wipe());
					return 1;
				}))));

	}
}
