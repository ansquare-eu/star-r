package eu.ansquare.starr.mixin;

import eu.ansquare.starr.power.Powers;
import eu.ansquare.starr.screenhandler.TeleportScreenHandler;
import eu.ansquare.starr.util.power.ServerTeleportHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;
import java.util.OptionalInt;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
	protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}
	@Shadow
	public abstract OptionalInt openHandledScreen(@Nullable NamedScreenHandlerFactory factory);
	@Inject(method = "interact", at = @At("HEAD"))
	public void onInteract(Entity entity, Hand hand, CallbackInfoReturnable<ActionResult> cir){
		if(entity instanceof ServerPlayerEntity otherplayer){
			if(Powers.TELEPORT_OTHERS_POWER.isActiveFor(getUuid())){
				ServerTeleportHandler.addTask((ServerPlayerEntity) (Object) this, otherplayer);
				this.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, playerInventory, playerx) -> new TeleportScreenHandler(syncId, playerInventory), Text.literal("enjoy")));

			}
		}
	}
}
