package eu.ansquare.starr.items.wearable;

import eu.ansquare.starr.client.wearables.model.cape.CapeWearableModel;
import eu.ansquare.starr.client.wearables.model.cape.SimpleCapeWearableModel;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.Model;

public class CapeModelProvider implements TwoStateModelProvider{
	@Override
	public Model getModel() {
		return getFirstModel();
	}

	@Override
	public Model getFirstModel() {
		return new CapeWearableModel(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(CapeWearableModel.LAYER_LOCATION));
	}

	@Override
	public Model getSecondModel() {
		return new SimpleCapeWearableModel(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(SimpleCapeWearableModel.LAYER_LOCATION));
	}
}
