package eu.ansquare.starr.mixin.client;

import eu.ansquare.squarepowered.Squarepowered;
import eu.ansquare.squarepowered.power.LimitCreativeMenuPower;
import io.github.apace100.apoli.component.PowerHolderComponent;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.feature_flags.FeatureFlagBitSet;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.HolderLookup;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;

@Mixin(CreativeInventoryScreen.class)
public abstract class CreativeInventoryScreenMixin extends AbstractInventoryScreen<CreativeInventoryScreen.CreativeScreenHandler> {
	@Shadow
	private static ItemGroup selectedTab;

	@Shadow
	protected abstract void setSelectedTab(ItemGroup group);

	@Shadow
	protected abstract void setItemList(Collection<ItemStack> stacks);

	public CreativeInventoryScreenMixin(CreativeInventoryScreen.CreativeScreenHandler screenHandler, PlayerInventory playerInventory, Text text) {
		super(screenHandler, playerInventory, text);
	}

	@Inject(method = "reloadItems", at = @At("HEAD"), cancellable = true)
	private void reloadItems(FeatureFlagBitSet featureFlags, boolean hasPermissions, HolderLookup.Provider lookupProvider, CallbackInfo ci) {
		if(PowerHolderComponent.hasPower(client.player, LimitCreativeMenuPower.class)){
			if (ItemGroups.tryReloadEntries(featureFlags, hasPermissions, lookupProvider)) {
				for(ItemGroup itemGroup : ItemGroups.getAllGroups()) {
					Collection<ItemStack> collection = itemGroup.getOrInitTabStacks();
					Squarepowered.log(PowerHolderComponent.getPowers(client.player, LimitCreativeMenuPower.class).get(0).stacks.get(0).toString(), 0);
					if (itemGroup == selectedTab) {
						if (itemGroup.getType() == ItemGroup.Type.CATEGORY && collection.isEmpty()) {
							this.setSelectedTab(ItemGroups.getDefaultTab());
						} else {
							this.setItemList(collection);
						}
					}
				}
			} else {
				for(ItemGroup itemGroup : ItemGroups.getAllGroups()) {
					Collection<ItemStack> collection = itemGroup.getOrInitTabStacks();
					Squarepowered.log(PowerHolderComponent.getPowers(client.player, LimitCreativeMenuPower.class).get(0).stacks.get(0).toString(), 0);
					if (itemGroup == selectedTab) {
						if (itemGroup.getType() == ItemGroup.Type.CATEGORY && collection.isEmpty()) {
							this.setSelectedTab(ItemGroups.getDefaultTab());
						} else {
							this.setItemList(collection);
						}
					}
				}
			}
			ci.cancel();
		}
	}
}
