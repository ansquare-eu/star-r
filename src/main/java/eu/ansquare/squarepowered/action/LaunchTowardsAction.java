package eu.ansquare.squarepowered.action;

import eu.ansquare.squarepowered.Squarepowered;
import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Pair;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3f;

public class LaunchTowardsAction {
	public static void action(SerializableData.Instance data, Pair<Entity, Entity> entities){
		if(entities.getLeft().getWorld() instanceof ServerWorld world){
			if(data.getBoolean("rotate")) entities.getLeft().lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, entities.getRight().getEyePos());
			Entity entity = entities.getLeft();
			Entity target = entities.getRight();
			Vec3d playerVector = entity.getPos();
			Vec3d targetVector = target.getPos();
			Vec3d normalizedConnection = targetVector.subtract(playerVector).normalize();
			Vec3d verticalprojection = new Vec3d(0, 1, 0).multiply(normalizedConnection.dotProduct(new Vec3d(0, 1, 0)));
			Vec3d horizontalprojection = normalizedConnection.subtract(verticalprojection);
			Vec3d unitvectorinhorizontalplain = horizontalprojection.normalize();
			double d = targetVector.subtract(playerVector).dotProduct(unitvectorinhorizontalplain);
			double h = targetVector.y - playerVector.y;
			double a = 0.08 * 20;
			double fi = Math.toRadians(30);
			double v = d * Math.sqrt(a / (2 * (d * Math.sin(fi) - h * Math.cos(fi))));
			Vec3d velocity = new Vec3d(v * unitvectorinhorizontalplain.x * Math.cos(fi), v * Math.sin(fi), v * unitvectorinhorizontalplain.z * Math.cos(fi));
			entity.setVelocity(velocity);
			entity.velocityModified = true;
		}
	}
	public static ActionFactory<Pair<Entity, Entity>> getFactory(){
		return new ActionFactory<>(Squarepowered.id("launch_to"),
				new SerializableData()
						.add("rotate", SerializableDataTypes.BOOLEAN, true)
						.add("speed", SerializableDataTypes.INT, 10),
				LaunchTowardsAction::action);
	}
}
