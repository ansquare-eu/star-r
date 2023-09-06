package eu.ansquare.starr.items;

import eu.ansquare.starr.items.wearable.TwoStateWearable;
import eu.ansquare.starr.items.wearable.WearableItem;

public class GetItemTypes {
	public static WearableItem[] getCapes(){
		return new WearableItem[]{
				ModItems.CAPE
		};
	}
	public static WearableItem[] getFaceWearables(){
		return new WearableItem[]{};
	}
	public static TwoStateWearable[] getTwoStateWearables(){
		return new TwoStateWearable[]{
				ModItems.HARE
		};
	}
}
