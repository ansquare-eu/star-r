package eu.ansquare.starr.mixin.client;

import eu.ansquare.squarepowered.Squarepowered;
import eu.ansquare.squarepowered.power.LimitCreativeMenuPower;
import io.github.apace100.apoli.component.PowerHolderComponent;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.search.SearchManager;
import net.minecraft.client.search.Searchable;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.feature_flags.FeatureFlagBitSet;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.HolderLookup;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;
import java.util.Locale;
import java.util.Set;

@Mixin(CreativeInventoryScreen.class)
public abstract class CreativeInventoryScreenMixin extends AbstractInventoryScreen<CreativeInventoryScreen.CreativeScreenHandler> {
	@Shadow
	private static ItemGroup selectedTab;

	@Shadow
	protected abstract void setSelectedTab(ItemGroup group);

	@Shadow
	protected abstract void setItemList(Collection<ItemStack> stacks);

	@Shadow
	@Final
	private Set<TagKey<Item>> searchResultTags;

	@Shadow
	private TextFieldWidget searchBox;

	@Shadow
	protected abstract void searchForTags(String id);

	@Shadow
	private float scrollPosition;

	public CreativeInventoryScreenMixin(CreativeInventoryScreen.CreativeScreenHandler screenHandler, PlayerInventory playerInventory, Text text) {
		super(screenHandler, playerInventory, text);
	}

	@Inject(method = "reloadItems", at = @At("HEAD"), cancellable = true)
	private void onReloadItems(FeatureFlagBitSet featureFlags, boolean hasPermissions, HolderLookup.Provider lookupProvider, CallbackInfo ci) {

		if(PowerHolderComponent.hasPower(client.player, LimitCreativeMenuPower.class)){

				for(ItemGroup itemGroup : ItemGroups.getAllGroups()) {
					Collection<ItemStack> collection = itemGroup.getOrInitTabStacks();
					Collection<ItemStack> collection2 = PowerHolderComponent.getPowers(client.player, LimitCreativeMenuPower.class).get(0).filterStacks(collection);
					if (itemGroup == selectedTab) {
						if (itemGroup.getType() == ItemGroup.Type.CATEGORY && collection2.isEmpty()) {
							this.setSelectedTab(ItemGroups.getDefaultTab());
						} else {
							this.setItemList(collection2);
						}
					}
				}
			ci.cancel();
		}
	}
	@Inject(method = "search", at = @At("HEAD"), cancellable = true)
	private void onSearch(CallbackInfo ci) {
		if(PowerHolderComponent.hasPower(client.player, LimitCreativeMenuPower.class)){
			this.handler.itemList.clear();
			this.searchResultTags.clear();
			String string = this.searchBox.getText();
			if (string.isEmpty()) {
				Collection<ItemStack> collection = selectedTab.getOrInitTabStacks();
				Collection<ItemStack> collection2 = PowerHolderComponent.getPowers(client.player, LimitCreativeMenuPower.class).get(0).filterStacks(collection);
				this.handler.itemList.addAll(collection2);
			} else {
				Searchable<ItemStack> searchable;
				if (string.startsWith("#")) {
					string = string.substring(1);
					searchable = this.client.getSearchableContainer(SearchManager.ITEM_TAG);
					this.searchForTags(string);
				} else {
					searchable = this.client.getSearchableContainer(SearchManager.ITEM_TOOLTIP);
				}
				Collection<ItemStack> collection = searchable.search(string.toLowerCase(Locale.ROOT));
				Collection<ItemStack> collection2 = PowerHolderComponent.getPowers(client.player, LimitCreativeMenuPower.class).get(0).filterStacks(collection);
				this.handler.itemList.addAll(collection2);
			}

			this.scrollPosition = 0.0F;
			this.handler.scrollItems(0.0F);
			ci.cancel();
		}
	}
}
