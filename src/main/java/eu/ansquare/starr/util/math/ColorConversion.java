package eu.ansquare.starr.util.math;

import java.awt.*;

public class ColorConversion {
	public static float[] toScaledArray(Color color, int scale){
		float red = (color.getRed() / 255) * scale;
		float green = (color.getGreen() / 255) * scale;
		float blue = (color.getBlue() / 255) * scale;
		return new float[] {red, green, blue};
	}
}
