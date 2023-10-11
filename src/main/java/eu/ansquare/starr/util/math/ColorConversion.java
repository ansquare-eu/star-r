package eu.ansquare.starr.util.math;

import eu.ansquare.starr.StarR;

import java.awt.*;

public class ColorConversion {
	public static float[] toScaledArray(Color color, float scale){
		float red = (color.getRed() / 255f) * scale;
		float green = (color.getGreen() / 255f) * scale;
		float blue = (color.getBlue() / 255f) * scale;
		return new float[] {red, green, blue};
	}
}
