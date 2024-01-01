package eu.ansquare.squarepowered.worldstructure;

import com.mojang.datafixers.types.Func;
import io.github.apace100.calio.data.SerializableData;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class WorldStructureFactory {
	public Identifier id;
	public final Function<SerializableData.Instance, WorldStructure> constructor;

	public WorldStructureFactory(Identifier id, Function<SerializableData.Instance, WorldStructure> constructor){
		this.constructor = constructor;
		this.id = id;
	}
	public WorldStructure build(SerializableData.Instance data){
		return constructor.apply(data);
	}
}
