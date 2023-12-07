package eu.ansquare.starr.power.offense;

import eu.ansquare.starr.entity.damage.ModDamageStuff;
import eu.ansquare.starr.power.Power;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class PointerPower extends Power {
	public PointerPower(BiConsumer<Entity, PlayerEntity> action, int distance){
		this.action = action;
		this.distance = distance;
	}
	public int distance;
	public BiConsumer<Entity, PlayerEntity> action;
	@Override
	public void onActivate(ServerPlayerEntity player) {
		Predicate<Entity> filter = filteredEntity -> true;

		Vec3d playerDir = player.getRotationVec(1f).normalize().multiply(64);
		EntityHitResult trace = ProjectileUtil.raycast(player, player.getEyePos(), player.getEyePos().add(playerDir), Box.of(player.getEyePos(), 1, 1, 1).expand(distance), filter, distance*distance*distance);
		if(trace != null) {
			action.accept(trace.getEntity(), player);
		}
	}

}
