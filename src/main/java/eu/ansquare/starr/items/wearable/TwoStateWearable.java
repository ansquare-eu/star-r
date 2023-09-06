package eu.ansquare.starr.items.wearable;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TwoStateWearable extends WearableItem{
	private String name;
	public TwoStateWearable(Settings settings, Identifier texture, String name) {
		super(settings, texture);
		this.name = name;
	}
	public boolean getState(ItemStack stack) {
		return stack.getOrCreateNbt().getBoolean("state");
	}
	public void toggleState(ItemStack stack, boolean sneaking) {
		if (sneaking) {
			NbtCompound compound = stack.getOrCreateNbt();
			compound.putBoolean("state", !compound.getBoolean("state"));
		}
	}
	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
		tooltip.add(Text.translatable("item.starr." + this.name +".tooltip." + stack.getOrCreateNbt().getBoolean("state")).formatted( Formatting.GRAY));
	}
}
