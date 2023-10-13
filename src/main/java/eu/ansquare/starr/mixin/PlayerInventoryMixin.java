package eu.ansquare.starr.mixin;

import eu.ansquare.starr.StarR;
import eu.ansquare.starr.util.item.ItemUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Nameable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;


@Mixin(PlayerInventory.class)
public abstract class PlayerInventoryMixin implements Inventory, Nameable {
	@Shadow
	@Final
	public PlayerEntity player;

	@Inject(method = "setStack", at = @At("HEAD"), cancellable = true)
	public void onSetStack(int slot, ItemStack stack, CallbackInfo ci) {
		NbtCompound compound = stack.getOrCreateNbt();
		if(compound.containsUuid(ItemUtils.SIGN_KEY)){
			if(!compound.getUuid(ItemUtils.SIGN_KEY).equals(this.player.getUuid())){
				PlayerEntity owner = this.player.getServer().getPlayerManager().getPlayer(compound.getUuid(ItemUtils.SIGN_KEY));
				if(owner != null){
					this.player.sendMessage(Text.translatable("message.starr.signeditem.owner", new Object[]{stack.getItem().getName(), owner.getName()}), true);
				} else {
					this.player.sendMessage(Text.translatable("message.starr.signeditem", new Object[]{stack.getItem().getName()}), true);
				}
				ci.cancel();
			}
		}
	}
}
