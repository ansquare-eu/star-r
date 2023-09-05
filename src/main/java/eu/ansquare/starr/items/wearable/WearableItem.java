package eu.ansquare.starr.items.wearable;

import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.util.Identifier;

public class WearableItem extends TrinketItem {
	private Identifier texture;
	public WearableItem(Settings settings, Identifier texture) {
		super(settings);
		this.texture = texture;
	}
	public Identifier getTexture(){
		return this.texture;
	}
}
