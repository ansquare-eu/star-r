package eu.ansquare.starr.entity.projectile;

import eu.ansquare.starr.entity.PalicaEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.world.World;

public abstract class ReturningProjectileEntity extends PersistentProjectileEntity {
	protected int ticksUntilGroundRemoval = -2;
	protected int ticksUntilRetrieval = -1;
	public ReturningProjectileEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
		super(entityType, world);
	}
	public void retrieve(){

		if(this.getOwner() != null){
			this.setVelocity(this.getOwner().getRotationVec(1).normalize().multiply(-0.8));
			this.inGround = false;
			this.ticksUntilRetrieval = 20;
		} else {this.remove(RemovalReason.DISCARDED);
		}
	}
	public void tick(){
		super.tick();

		if (this.inGround || this.age >= 100) {
			this.retrieve();
		}

		if(ticksUntilRetrieval > 0) {
			this.ticksUntilRetrieval--;
			if(this.ticksUntilRetrieval <= 0){
				if(this.getOwner() instanceof PlayerEntity){
					this.tryPickup(((PlayerEntity) this.getOwner()));
					this.remove(RemovalReason.DISCARDED);
				}
			}
		}
	}
}
