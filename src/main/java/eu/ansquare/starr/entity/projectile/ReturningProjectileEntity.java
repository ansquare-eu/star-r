package eu.ansquare.starr.entity.projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public abstract class ReturningProjectileEntity extends PersistentProjectileEntity {
	protected int ticksUntilGroundRemoval = -2;
	public int untilremove = -1;
	public int maxDistance;
	public ReturningProjectileEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
		super(entityType, world);
		maxDistance = 1;
	}
	public void retrieve(){

		if(this.getOwner() != null){
			this.setVelocity(this.getOwner().getPos().subtract(this.getEyePos()).normalize().multiply(2));
			this.inGround = false;
			this.untilremove = 20;
		} else {this.remove(RemovalReason.DISCARDED);
		}
	}

	public void onEntityHit(EntityHitResult result){
		if(this.getOwner() != null){
			if(!result.getEntity().getUuid().equals(this.getOwner().getUuid())){
				super.onEntityHit(result);
			}
		}

	}
	public void tick(){
		super.tick();
		if(!this.getWorld().isClient()){

		if (this.inGround) {
			this.tryPickup(((PlayerEntity) this.getOwner()));

			this.discard();
		}
		if(this.getOwner() != null){
			if(this.getPos().distanceTo(getOwner().getPos())>maxDistance){
				this.retrieve();
			}
		} else {
			this.retrieve();
		}
		if(untilremove > 0) {
			this.untilremove--;
			if(this.untilremove <= 0){
				if(this.getOwner() instanceof PlayerEntity){
					this.tryPickup(((PlayerEntity) this.getOwner()));
					this.remove(RemovalReason.DISCARDED);
				}
			}
		}
	}}
}
