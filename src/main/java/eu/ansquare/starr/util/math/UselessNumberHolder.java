package eu.ansquare.starr.util.math;

public class UselessNumberHolder {
	public int x;
	public int y;
	public int z;
	public UselessNumberHolder(int x, int y ,int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public UselessNumberHolder(int[] array){
		this.x = array[0];
		this.y = array[1];
		this.z = array[2];
	}
	public int[] toArray(){
		return new int[]{x, y, z};
	}
}
