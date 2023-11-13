package eu.ansquare.starr.power.offense;

import eu.ansquare.starr.StarR;
import eu.ansquare.starr.entity.LaserEntity;
import eu.ansquare.starr.entity.damage.ModDamageStuff;
import eu.ansquare.starr.network.ModPackets;
import eu.ansquare.starr.power.ToggleablePower;
import eu.ansquare.starr.util.math.Math3D;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Pair;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.qsl.networking.api.PacketByteBufs;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;

public class LaserPower extends ToggleablePower {
	private Offset offset;
	private int damage;
	private Color color;
	public LaserPower(Color color, int damage, Offset offset){
		this.color = color;
		this.damage = damage;
		this.offset = offset;
	}
	public Color getColor(){
		return this.color;
	}
	public int getDamage(){
		return this.damage;
	}
	@Override
	public void activationAction(ServerPlayerEntity player) {
		PacketByteBuf buf = PacketByteBufs.create().writeUuid(player.getUuid()).writeString(String.valueOf(color.getRGB()));
		Float xoff = offset.run(player, player.getMainHandStack(), player.getOffHandStack()).getLeft();
		Float yoff = offset.run(player, player.getMainHandStack(), player.getOffHandStack()).getRight();
		buf.writeDouble(xoff);
		buf.writeFloat(yoff);
		ServerPlayNetworking.send(player.getServerWorld().getPlayers(), ModPackets.RENDER_LASER_PACKET_ID, buf);
	}

	@Override
	public void deactivationAction(ServerPlayerEntity player) {
		ServerPlayNetworking.send(player.getServerWorld().getPlayers(), ModPackets.UNRENDER_LASER_PACKET_ID, PacketByteBufs.create().writeUuid(player.getUuid()));
	}

	@Override
	public void activeTick(LivingEntity entity) {
		@Nullable Predicate<Entity> filter = filteredEntity -> true;
		Double distance = 64d;
		Vec3d playerDir = entity.getRotationVec(1f).normalize().multiply(64);
		//BlockHitResult trace = player.getWorld().raycastBlock(player.getEyePos(), player.getRotationVec(1), 128, 1, filter);
		EntityHitResult trace = ProjectileUtil.raycast(entity, entity.getEyePos(), entity.getEyePos().add(playerDir), Box.of(entity.getEyePos(), 1, 1, 1).expand(distance), filter, distance*distance*distance);
		if(trace != null) {
			trace.getEntity().damage(ModDamageStuff.of(trace.getEntity().getWorld(), ModDamageStuff.LASER_DAMAGE_TYPE, entity), damage);
		}

	}
	@FunctionalInterface
	public interface Offset{
		Pair<Float, Float> run(ServerPlayerEntity entity, ItemStack handStack, ItemStack offhandStack);
	}
}
