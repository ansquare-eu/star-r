package eu.ansquare.starr.items.wearable;

import dev.emi.trinkets.api.SlotReference;
import eu.ansquare.starr.StarR;
import eu.ansquare.starr.util.FourConsumer;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.model.Model;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
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
	private Identifier secondTexture;
	private TwoStateModelProvider modelProvider;

	public TwoStateWearable(Settings settings, String texture, String secondtexture, String name, TwoStateModelProvider modelProvider, FourConsumer<MatrixStack, PlayerEntityModel<AbstractClientPlayerEntity>, AbstractClientPlayerEntity, SlotReference> aligning) {
		super(settings, texture, modelProvider, aligning);
		this.name = name;
		this.secondTexture = new Identifier(StarR.MODID, secondtexture);
		this.modelProvider = modelProvider;
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
	public Identifier getSecondTexture(){
		return secondTexture;
	}
	public Model getFirstModel(){
		return modelProvider.getFirstModel();
	}
	public Model getSecondModel(){
		return modelProvider.getSecondModel();
	}
	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
		tooltip.add(Text.translatable("item.starr." + this.name +".tooltip." + stack.getOrCreateNbt().getBoolean("state")).formatted( Formatting.GRAY));
	}
}
