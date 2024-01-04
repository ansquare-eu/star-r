package eu.ansquare.squarepowered.cca;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.nbt.NbtCompound;

public class ClientStatesComponent implements AutoSyncedComponent {
	public boolean laser = false;
	public float x_offset = 0.0f;
	public float y_offset = 0.0f;
	public int[] color = new int[]{0, 0, 0};
	public void setColors(int r, int g, int b){
		color[0] = r;
		color[1] = g;
		color[2] = b;
	}
	public float[] getColors(int scale){
		float red = (color[0] / 255f) * scale;
		float green = (color[1] / 255f) * scale;
		float blue = (color[2] / 255f) * scale;
		return new float[] {red, green, blue};
	}
	@Override
	public void readFromNbt(NbtCompound tag) {
		laser = tag.getBoolean("laser");
		x_offset = tag.getFloat("x_offset");
		y_offset = tag.getFloat("y_offset");
		color = tag.getIntArray("laser_colors");
	}

	@Override
	public void writeToNbt(NbtCompound tag) {
		tag.putBoolean("laser", laser);
		tag.putFloat("x_offset", x_offset);
		tag.putFloat("y_offset", y_offset);
		tag.putIntArray("laser_colors", color);
	}
}
