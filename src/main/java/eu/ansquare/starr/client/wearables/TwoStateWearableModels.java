package eu.ansquare.starr.client.wearables;

import eu.ansquare.starr.client.wearables.model.hare.HareOneModel;
import eu.ansquare.starr.client.wearables.model.hare.HareTwoModel;
import eu.ansquare.starr.items.ModItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class TwoStateWearableModels {
	public static Map<Item, Pair<Class, Class>> models = new HashMap<>();
	public static void init(){
		models.put(ModItems.HARE, new Pair(new HareOneModel(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(HareOneModel.LAYER_LOCATION)),
				new HareTwoModel(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(HareTwoModel.LAYER_LOCATION))));
	}
	public static Pair<Class, Class> getModels(Item item){
		return models.get(item);
	}
}
