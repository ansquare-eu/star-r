package eu.ansquare.starr.mixin;

import eu.ansquare.squarepowered.cca.MultiInventoryComponent;
import eu.ansquare.starr.util.item.ItemUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.text.Text;
import net.minecraft.util.Nameable;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixn extends LivingEntity {
	@Shadow
	public abstract void sendMessage(Text message, boolean actionBar);

	@Shadow
	@Final
	private PlayerInventory inventory;

	@Shadow
	public abstract void equipStack(EquipmentSlot slot, ItemStack stack);

	@Shadow
	public abstract boolean shouldCancelInteraction();

	protected PlayerEntityMixn(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(method = "getEquippedStack", at = @At("RETURN"), cancellable = true)
	public void onSetStack(EquipmentSlot slot, CallbackInfoReturnable<ItemStack> cir) {
		ItemStack stack = cir.getReturnValue();
		NbtCompound compound = stack.getOrCreateNbt();
		if(compound.containsUuid(ItemUtils.SIGN_KEY)){
			if(!getWorld().isClient()){
				if(!compound.getUuid(ItemUtils.SIGN_KEY).equals(getUuid())){
					PlayerEntity owner = getServer().getPlayerManager().getPlayer(compound.getUuid(ItemUtils.SIGN_KEY));
					if(owner != null){
						sendMessage(Text.translatable("message.starr.signeditem.owner", new Object[]{stack.getItem().getName(), owner.getName()}), true);
					} else {
						sendMessage(Text.translatable("message.starr.signeditem", new Object[]{stack.getItem().getName()}), true);
					}
					equipStack(slot, ItemStack.EMPTY);
					cir.setReturnValue(ItemStack.EMPTY);
				}
			}
		}
	}

	@Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
	public void squarepowered_onWriteCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
		MultiInventoryComponent.save((PlayerEntity) (Object) this);
	}
}
