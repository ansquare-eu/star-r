package eu.ansquare.starr;

import eu.ansquare.starr.StarR;
import eu.ansquare.starr.superdude.SuperDude;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.registry.DefaultedRegistry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.registry.attachment.impl.QuiltRegistryInternals;

public class ModRegistries {
	public static final DefaultedRegistry<SuperDude> SUPER_DUDES = FabricRegistryBuilder.createDefaulted(SuperDude.class, new Identifier(StarR.MODID, "superdudes"), new Identifier(StarR.MODID, "empty")).buildAndRegister();
}
