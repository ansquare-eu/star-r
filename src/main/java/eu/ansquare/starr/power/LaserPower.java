package eu.ansquare.starr.power;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;

public class LaserPower extends ToggleablePower{
	private int damage;
	private int color;
	public LaserPower(int color, int damage){
		this.color = color;
		this.damage = damage;
	}
	@Override
	public String getName() {
		return "laser";
	}


	@Override
	public void activationAction(ServerPlayerEntity player) {

	}

	@Override
	public void deactivationAction(ServerPlayerEntity player) {

	}

	@Override
	public void activeTick(LivingEntity entity) {
			ServerWorld world = ((ServerWorld) entity.getWorld());
			Vec3d playerEyePos = entity.getEyePos();
			Vec3d playerDir = entity.getRotationVec(1f).normalize().multiply(10);
			Vec3d newLoc = playerEyePos.add(playerDir);
			EntityType.COW.spawn(world, BlockPos.fromPosition(newLoc), SpawnReason.TRIGGERED);

	}
}
