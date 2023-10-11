package eu.ansquare.starr.items.wearable;

import net.minecraft.client.model.Model;

public interface TwoStateModelProvider extends SimpleModelProvider {
	Model getFirstModel();
	Model getSecondModel();

}
