package eu.ansquare.starr.power;

import eu.ansquare.starr.entity.ModEntities;
import eu.ansquare.starr.entity.PalicaEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.server.network.ServerPlayerEntity;

public class TestTransformationPower extends Power{
	@Override
	public String getName() {
		return "testTransformation";
	}

	@Override
	public void onActivate(ServerPlayerEntity player) {
		PalicaEntity entity = (PalicaEntity) ModEntities.PALICA.create(player.getWorld());
		entity.setOwner(player);
		entity.setPosition(player.getEyePos().add(player.getRotationVec(1).normalize().multiply(5)));
		entity.setNoGravity(true);
		entity.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
		entity.retrieve();
		player.getWorld().spawnEntity(entity);
	}
}
