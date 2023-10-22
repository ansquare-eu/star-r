package eu.ansquare.starr.util.network;

import java.awt.*;

public class ClientLaser {
	public Color COLOR;
	public float xOff;
	public float yOff;
	public ClientLaser(int color, float x, double y){
		this.COLOR = new Color(color);
		this.xOff = x;
		this.yOff = (float) y;
	}
}
