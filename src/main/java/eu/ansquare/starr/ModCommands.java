package eu.ansquare.starr;
import eu.ansquare.starr.superdude.SuperDude;
import eu.ansquare.starr.superdude.SuperDudes;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.command.argument.ResourceKeyArgument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
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
		CommandRegistrationCallback.EVENT.register((dispatcher, buildContext, environment) -> dispatcher.register(literal("superdude")
				.then(argument("target", player())
						.then(argument("id", ResourceKeyArgument.key(ModRegistries.SUPER_DUDES.getKey())).requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(4))
								.executes(context -> {
									SuperDude superDude = SuperDudes.getSuperDude(context.getArgument("id", RegistryKey.class).getValue());
									PlayerEntity player = EntityArgumentType.getPlayer(context, "target");
									SuperDudes.applyToPlayer(player, superDude);
									return 1;
								})).then(literal("get").executes(context -> {
									SuperDude superDude = SuperDudes.getFromPlayer(EntityArgumentType.getPlayer(context, "target"));
									context.getSource().sendFeedback(() -> Text.literal(superDude.id.toString()), true);
									return 1;
						})))));
	}
}
