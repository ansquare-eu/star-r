package eu.ansquare.starr.mixin.client;

import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketInventory;
import dev.emi.trinkets.api.TrinketsApi;
import eu.ansquare.starr.items.wearable.WearableItem;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(BipedEntityModel.class)
public abstract class BipedEntityModelMixin<T extends LivingEntity> extends AnimalModel<T> {
	@Shadow
	@Final
	public ModelPart leftLeg;

	@Shadow
	@Final
	public ModelPart rightLeg;

	@Inject(method = "setAngles(Lnet/minecraft/entity/LivingEntity;FFFFF)V", at = @At("TAIL"))
	public void starr_cancelLegSwing(T livingEntity, float f, float g, float h, float i, float j, CallbackInfo ci) {
		if (TrinketsApi.getTrinketComponent(livingEntity).isPresent()) {
			TrinketComponent component = TrinketsApi.getTrinketComponent(livingEntity).get();
			if (component.getInventory().containsKey("feet")) {
				Map<String, TrinketInventory> map = component.getInventory().get("feet");
				if (map.containsKey("left_shoe")) {
					TrinketInventory inventory = map.get("left_shoe");
					if (inventory.getStack(0).getItem() instanceof WearableItem) {
						leftLeg.pitch = 0;
					}


				}
				if (map.containsKey("right_shoe")) {
					TrinketInventory inventory = map.get("right_shoe");
					if (inventory.getStack(0).getItem() instanceof WearableItem) {
						rightLeg.pitch = 0;
					}


				}

			}
		}
	}
}
