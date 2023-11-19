package eu.ansquare.starr.items;

import eu.ansquare.starr.items.wearable.TwoStateWearable;
import eu.ansquare.starr.items.wearable.WearableItem;

public class GetItemTypes {
	public static WearableItem[] getSimpleWearables(){
		return new WearableItem[]{
		};
	}
	public static TwoStateWearable[] getTwoStateWearables(){
		return new TwoStateWearable[]{
				ModItems.HARE,
				ModItems.CAPE
		};
	}
}
