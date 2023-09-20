package eu.ansquare.starr.power;

import eu.ansquare.starr.entity.LaserEntity;
import eu.ansquare.starr.entity.ModEntities;
import net.minecraft.entity.Entity;
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

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LaserPower extends ToggleablePower{
	private int damage;
	private Color color;
	public Map<UUID, LaserEntity> entityMap = new HashMap<>();
	public LaserPower(Color color, int damage){
		this.color = color;
		this.damage = damage;
	}
	@Override
	public String getName() {
		return "laser";
	}
	public Color getColor(){
		return this.color;
	}
	public int getDamage(){
		return this.damage;
	}
	@Override
	public void activationAction(ServerPlayerEntity player) {
		LaserEntity entity = new LaserEntity(ModEntities.LASER, player.getServerWorld());
		entityMap.put(player.getUuid(), entity);
		entity.setOwner(player);
		entity.setColor(this.color);
		entity.setPosition(player.getEyePos());
		player.getWorld().spawnEntity(entity);
	}

	@Override
	public void deactivationAction(ServerPlayerEntity player) {
		if(entityMap.containsKey(player.getUuid())){
			Entity entity = entityMap.get(player.getUuid());
			entityMap.remove(player.getUuid());
			((LaserEntity) entity).delete();
		}
	}

	@Override
	public void activeTick(LivingEntity entity) {
/*			ServerWorld world = ((ServerWorld) entity.getWorld());
			Vec3d playerEyePos = entity.getEyePos();
			Vec3d playerDir = entity.getRotationVec(1f).normalize().multiply(10);
			Vec3d newLoc = playerEyePos.add(playerDir);
			EntityType.COW.spawn(world, BlockPos.fromPosition(newLoc), SpawnReason.TRIGGERED);*/

	}
}
