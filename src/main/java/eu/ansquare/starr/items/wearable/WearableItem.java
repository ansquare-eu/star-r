package eu.ansquare.starr.items.wearable;

import dev.emi.trinkets.api.TrinketItem;
import eu.ansquare.starr.StarR;
import net.minecraft.client.model.Model;
import net.minecraft.util.Identifier;

public class WearableItem extends TrinketItem {
	private Identifier texture;
	private SimpleModelProvider modelProvider;
	public boolean rotateWithHead;
	public WearableItem(Settings settings, String texture, SimpleModelProvider modelProvider, boolean headRotated) {
		super(settings);
		this.texture = new Identifier(StarR.MODID, texture);
		this.modelProvider = modelProvider;
		this.rotateWithHead = headRotated;
	}
	public Identifier getTexture(){
		return this.texture;
	}
	public Model getModel(){
		return modelProvider.getModel();
	}
}
