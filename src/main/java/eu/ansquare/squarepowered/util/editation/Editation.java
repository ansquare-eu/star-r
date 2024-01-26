package eu.ansquare.squarepowered.util.editation;

public interface Editation {
	Type getType();
	enum Type{
		SIMPLE,
		TWO_POINT,
		REPLACEMENT
	}
}
