package eu.ansquare.squarepowered.util.editation;

import eu.ansquare.squarepowered.actionscreen.client.ActionScreen;
import net.minecraft.client.gui.widget.Widget;

public interface Editation {
	Type getType();
	void getRequriedWidgets(ActionScreen screen, int height, String editationKey);
	enum Type{
		SIMPLE,
		TWO_POINT,
		REPLACEMENT
	}
}
