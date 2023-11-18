package eu.ansquare.starr;
import eu.ansquare.starr.superdude.SuperDude;
import eu.ansquare.starr.superdude.SuperDudes;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.player.PlayerEntity;
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
						.then(argument("id", string()).requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(4))
								.executes(context -> {
									SuperDude superDude = SuperDudes.getSuperDude(new Identifier(context.getArgument("id", String.class)));
									PlayerEntity player = EntityArgumentType.getPlayer(context, "target");
									SuperDudes.applyToPlayer(player, superDude);
									return 1;
								})))));
	}
}
