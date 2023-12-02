package eu.ansquare.starr.power.utility;

import eu.ansquare.starr.power.ActiveEventable;
import eu.ansquare.starr.power.ToggleablePower;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class LocalizePower extends ToggleablePower implements ActiveEventable {
	@Override
	public void activationAction(ServerPlayerEntity player) {

	}

	@Override
	public void deactivationAction(ServerPlayerEntity player) {

	}

	@Override
	public void activeTick(LivingEntity entity) {

	}

	@Override
	public void activeAction(ServerPlayerEntity player, Entity entity) {
		BlockPos pos = entity.getBlockPos();
		player.sendMessage(Text.literal(pos.getX() + " " + pos.getY() + " " + pos.getZ()), false);
	}
}
