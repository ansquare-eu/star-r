package eu.ansquare.starr.superdude;

public class SuperDudes {
	public static final SuperDude TEST_SUPER_DUDE = new TestSuperDude();
	public static SuperDude getSuperDude(String type){
		switch (type){
			case "testType":
				return TEST_SUPER_DUDE;
			case "npot":
				return TEST_SUPER_DUDE;
			default:
				return null;
		}
	}
}
