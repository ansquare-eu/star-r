package eu.ansquare.starr.mixin.square;

import com.mojang.authlib.GameProfile;
import eu.ansquare.squarepowered.cca.MultiInventoryComponent;
import eu.ansquare.starr.util.item.ItemUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixn extends PlayerEntity {

	public ServerPlayerEntityMixn(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
		super(world, pos, yaw, gameProfile);
	}

	@Inject(method = "dropSelectedItem", at = @At("HEAD"), cancellable = true)
	public void squarepowered_onDropItem(boolean entireStack, CallbackInfoReturnable<Boolean> cir) {
		if(MultiInventoryComponent.isPreventTransfer((PlayerEntity) (Object) this)) cir.setReturnValue(false);
	}

}
