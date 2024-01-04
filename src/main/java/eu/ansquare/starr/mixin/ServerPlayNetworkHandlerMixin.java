package eu.ansquare.starr.mixin;

import eu.ansquare.squarepowered.power.CreativeInvPower;
import eu.ansquare.starr.StarR;
import io.github.apace100.apoli.component.PowerHolderComponent;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.c2s.play.CreativeInventoryActionC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {

	@Shadow
	public ServerPlayerEntity player;

	@Shadow
	private int creativeItemDropThreshold;

	@Inject(method = "onCreativeInventoryAction", at = @At("TAIL"))
	public void isCreative(CreativeInventoryActionC2SPacket packet, CallbackInfo ci){
		if (!player.interactionManager.isCreative() && PowerHolderComponent.hasPower(player, CreativeInvPower.class)) {
			boolean bl = packet.getSlot() < 0;
			ItemStack itemStack = packet.getItemStack();
			if (!itemStack.isEnabled(this.player.getWorld().getEnabledFlags())) {
				return;
			}

			NbtCompound nbtCompound = BlockItem.getBlockEntityNbtFromStack(itemStack);
			if (!itemStack.isEmpty() && nbtCompound != null && nbtCompound.contains("x") && nbtCompound.contains("y") && nbtCompound.contains("z")) {
				BlockPos blockPos = BlockEntity.readBlockPosFromNbt(nbtCompound);
				if (this.player.getWorld().canSetBlock(blockPos)) {
					BlockEntity blockEntity = this.player.getWorld().getBlockEntity(blockPos);
					if (blockEntity != null) {
						blockEntity.writeNbtToStack(itemStack);
					}
				}
			}

			boolean bl2 = packet.getSlot() >= 1 && packet.getSlot() <= 45;
			boolean bl3 = itemStack.isEmpty() || itemStack.getDamage() >= 0 && itemStack.getCount() <= 64 && !itemStack.isEmpty();
			if (bl2 && bl3) {
				this.player.playerScreenHandler.getSlot(packet.getSlot()).setStackByPlayer(itemStack);
				this.player.playerScreenHandler.sendContentUpdates();
			} else if (bl && bl3 && creativeItemDropThreshold < 200) {
				this.creativeItemDropThreshold += 20;
				this.player.dropItem(itemStack, true);
			}
		}	}
}
