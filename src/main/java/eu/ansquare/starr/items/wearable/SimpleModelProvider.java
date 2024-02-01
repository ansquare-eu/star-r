package eu.ansquare.starr.items.wearable;

import net.minecraft.client.model.Model;

import java.util.function.Supplier;

@FunctionalInterface
public interface SimpleModelProvider extends Supplier<Model> {
	Model getModel();
	default Model get(){
		return getModel();
	}
}
