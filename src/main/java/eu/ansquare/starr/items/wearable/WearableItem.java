package eu.ansquare.starr.items.wearable;

import dev.emi.trinkets.api.TrinketItem;
import eu.ansquare.starr.StarR;
import eu.ansquare.starr.util.TriConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class WearableItem extends TrinketItem {
	private Identifier texture;
	private SimpleModelProvider modelProvider;
	public TriConsumer<MatrixStack, PlayerEntityModel<AbstractClientPlayerEntity>, AbstractClientPlayerEntity> aligning;
	public WearableItem(Settings settings, String texture, SimpleModelProvider modelProvider, eu.ansquare.starr.util.TriConsumer<MatrixStack, PlayerEntityModel<AbstractClientPlayerEntity>, AbstractClientPlayerEntity> aligning) {
		super(settings);
		this.texture = new Identifier(StarR.MODID, texture);
		this.modelProvider = modelProvider;
		this.aligning = aligning;
	}
	public Identifier getTexture(){
		return this.texture;
	}
	public Model getModel(){
		return modelProvider.getModel();
	}
}
