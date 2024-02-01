package eu.ansquare.starr.util;

@FunctionalInterface
public interface FourConsumer<A, B, C, D> {
	void accept(A a, B b, C c, D d);
}
