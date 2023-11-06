package eu.ansquare.starr.superdude;

import eu.ansquare.starr.items.ModItems;
import eu.ansquare.starr.power.Powers;
import eu.ansquare.starr.power.creation.CreateItemPower;
import eu.ansquare.starr.power.creation.SummonEntityPower;
import eu.ansquare.starr.util.power.FlightType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;

import java.awt.*;

public class ShieldboySuperDude extends SuperDude{
	public ShieldboySuperDude(FlightType flight, Color color) {
		super(flight, color);
	}

	@Override
	public void initPowers() {
		addPower(PowerOrder.FIRST, Powers.FORCEFIELD_POWER);
		addPower(PowerOrder.SECOND, new CreateItemPower(ModItems.FORCESWORD, 1, true));
		addPower(PowerOrder.THIRD, new SummonEntityPower((player) -> {
			Vec3d playerDir = player.getRotationVec(1f).normalize().multiply(64);
			BlockHitResult blockHitResult = player.getWorld().raycast(new RaycastContext(player.getEyePos(), player.getEyePos().add(playerDir), RaycastContext.ShapeType.VISUAL, RaycastContext.FluidHandling.NONE, player));
			if (blockHitResult != null) {
				TurtleEntity turtle = EntityType.TURTLE.create(player.getServerWorld());
				turtle.setPosition(blockHitResult.getPos());
				turtle.setBaby(true);
				player.getServerWorld().spawnEntity(turtle);
			}
		}));
		addPower(PowerOrder.FOURTH, new SummonEntityPower((player) -> {
			Vec3d playerDir = player.getRotationVec(1f).normalize().multiply(64);
			BlockHitResult blockHitResult = player.getWorld().raycast(new RaycastContext(player.getEyePos(), player.getEyePos().add(playerDir), RaycastContext.ShapeType.VISUAL, RaycastContext.FluidHandling.NONE, player));
			if (blockHitResult != null) {
				TurtleEntity turtle = EntityType.TURTLE.create(player.getServerWorld());
				turtle.setPosition(blockHitResult.getPos());
				player.getServerWorld().spawnEntity(turtle);
			}
		}));
		addPower(PowerOrder.FIFTH, new SummonEntityPower((player) -> {
			TurtleEntity turtle = EntityType.TURTLE.create(player.getServerWorld());
			turtle.startRiding(player);
			player.getServerWorld().spawnEntity(turtle);
		}));
	}
	@Override
	public void initModifiers() {

	}

	@Override
	public String queryMessage() {
		return "im not making the captain readyroad america shield";
	}
}
