package eu.ansquare.starr.mixin;

import com.mojang.authlib.GameProfile;
import dev.onyxstudios.cca.api.v3.entity.PlayerCopyCallback;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {


	@Shadow
	public abstract void sendAbilitiesUpdate();

	public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
		super(world, pos, yaw, gameProfile);
	}
	@Inject(method = "copyFrom", at = @At("RETURN"))
	private void copyDataFrom(ServerPlayerEntity original, boolean lossless, CallbackInfo ci) {
		this.getAttributes().setFrom(original.getAttributes());

	}

}
