package eu.ansquare.starr.items;

import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.util.Identifier;

public class CapeItem extends TrinketItem {
	private Identifier texture;
	public CapeItem(Settings settings, Identifier texture) {
		super(settings);
		this.texture = texture;
	}
	public Identifier getTexture(){
		return this.texture;
	}
}
