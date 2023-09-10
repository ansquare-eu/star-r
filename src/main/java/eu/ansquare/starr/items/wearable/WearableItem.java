package eu.ansquare.starr.items.wearable;

import dev.emi.trinkets.api.TrinketItem;
import eu.ansquare.starr.StarR;
import net.minecraft.util.Identifier;

public class WearableItem extends TrinketItem {
	private Identifier texture;
	public WearableItem(Settings settings, String texture) {
		super(settings);
		this.texture = new Identifier(StarR.MODID, texture);
	}
	public Identifier getTexture(){
		return this.texture;
	}
}
