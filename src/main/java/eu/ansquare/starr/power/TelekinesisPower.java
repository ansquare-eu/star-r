package eu.ansquare.starr.power;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import org.apache.logging.log4j.core.appender.rolling.action.IfAll;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;

public class TelekinesisPower extends ToggleablePower{
	@Override
	public String getName() {
		return "telekinesis";
	}
	public Map<UUID, Entity> entities = new HashMap<>();
	@Override
	public void activationAction(ServerPlayerEntity player) {
		@Nullable Predicate<Entity> filter = new Predicate<Entity>() {
			@Override
			public boolean test(Entity entity) {
				if(entity.getType() == EntityType.PLAYER){
					return false;
				}
				else {
					return true;
				}
			}
		};
		Double distance = 64d;
		Vec3d playerDir = player.getRotationVec(1f).normalize().multiply(64);
		//BlockHitResult trace = player.getWorld().raycastBlock(player.getEyePos(), player.getRotationVec(1), 128, 1, filter);
		EntityHitResult trace = ProjectileUtil.raycast(player, player.getEyePos(), player.getEyePos().add(playerDir), Box.of(player.getEyePos(), 1, 1, 1).expand(distance), filter, distance*distance*distance);
		if(trace != null){
			player.sendMessage(trace.getEntity().getName(), false);
			if(trace.getEntity() instanceof LivingEntity){
				entities.put(player.getUuid(), trace.getEntity());
				trace.getEntity().setInvulnerable(true);

			}
		}
			BlockHitResult blockHitResult = player.getWorld().raycast(new RaycastContext(player.getEyePos(), player.getEyePos().add(playerDir), RaycastContext.ShapeType.VISUAL, RaycastContext.FluidHandling.NONE, player));
			if(blockHitResult != null){
				FallingBlockEntity fallingBlock = FallingBlockEntity.fall(player.getWorld(), blockHitResult.getBlockPos(), player.getWorld().getBlockState(blockHitResult.getBlockPos()));
				entities.put(player.getUuid(), fallingBlock);
			}

	}

	@Override
	public void deactivationAction(ServerPlayerEntity player) {
		Entity entity = entities.remove(player.getUuid());
		if(entity != null){
			entity.setInvulnerable(false);
		}
	}

	@Override
	public void activeTick(LivingEntity entity) {
		if(entities.containsKey(entity.getUuid())){
			Entity anotherEntity = entities.get(entity.getUuid());{
				Vec3d playerDir = entity.getRotationVec(1f).normalize().multiply(10);
				Vec3d dest = entity.getEyePos().add(playerDir);
				anotherEntity.teleport(dest.x, dest.y, dest.z);
			}

		}
	}
}
