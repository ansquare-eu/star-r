package eu.ansquare.starr.client.particle;

import eu.ansquare.starr.StarR;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class ModParticles {
	public static Map<Identifier, DefaultParticleType> PARTICLES = new HashMap<>();

	public static final DefaultParticleType TORNADO = createParticleType("tornado", FabricParticleTypes.simple(true));
	public static DefaultParticleType createParticleType(String name, DefaultParticleType type){
		Identifier id = new Identifier(StarR.MODID, name);
		PARTICLES.put(id, type);
		return type;
	}
	public static void init(){
		PARTICLES.keySet().forEach(particle -> Registry.register(Registries.PARTICLE_TYPE, particle, PARTICLES.get(particle)));
		ParticleFactoryRegistry.getInstance().register(TORNADO, TornadoParticle.Factory::new);
	}
}
