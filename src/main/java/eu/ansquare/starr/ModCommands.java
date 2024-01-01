package eu.ansquare.starr;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.command.argument.ResourceKeyArgument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
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
						.then(argument("colorint", IntegerArgumentType.integer())
								.requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(4))
								.executes(context -> {
									int color = context.getArgument("colorint", Integer.class);
									PlayerEntity entity = context.getSource().getPlayer();
									ItemStack stack = entity.getStackInHand(Hand.MAIN_HAND);
									stack.getOrCreateNbt().putInt("color", color);
									return 1;
								})))));

	}
}
