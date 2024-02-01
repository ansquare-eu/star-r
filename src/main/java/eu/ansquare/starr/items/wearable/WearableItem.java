package eu.ansquare.starr.items.wearable;

import com.ibm.icu.text.Normalizer2;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import eu.ansquare.starr.StarR;
import eu.ansquare.starr.util.FourConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class WearableItem extends TrinketItem {
	private Identifier texture;
	private Supplier<Model> modelProvider;
	public FourConsumer<MatrixStack, PlayerEntityModel<AbstractClientPlayerEntity>, AbstractClientPlayerEntity, SlotReference> aligning;
	public WearableItem(Settings settings, String texture, Supplier<Model> modelProvider, FourConsumer<MatrixStack, PlayerEntityModel<AbstractClientPlayerEntity>, AbstractClientPlayerEntity, SlotReference> aligning) {
		super(settings);
		this.texture = new Identifier(StarR.MODID, texture);
		this.modelProvider = modelProvider;
		this.aligning = aligning;
	}
	public Identifier getTexture(){
		return this.texture;
	}
	public Model getModel(){
		return modelProvider.get();
	}
}
