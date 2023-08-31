package eu.ansquare.starr.entity;

import eu.ansquare.starr.StarR;
import net.minecraft.entity.*;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CapeEntity extends Entity {
	private static final TrackedData<String> TYPE = DataTracker.registerData(CapeEntity.class, TrackedDataHandlerRegistry.STRING);

	public CapeEntity(EntityType<?> variant, World world) {
		super(variant, world);
	}
	public void tick(){
		super.tick();
		PlayerEntity player = this.getWorld().getClosestPlayer(this, 32);
		this.setPos(player.getX(), player.getY(), player.getZ());
		this.setYaw(player.getYaw());
		this.setPitch(player.getPitch());
	}
	@Override
	protected void initDataTracker() {
	}

	@Override
	protected void readCustomDataFromNbt(NbtCompound nbt) {

	}

	@Override
	protected void writeCustomDataToNbt(NbtCompound nbt) {

	}
}
