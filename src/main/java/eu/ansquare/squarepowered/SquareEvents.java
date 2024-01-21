package eu.ansquare.squarepowered;

import eu.ansquare.sbd.BlockDataApi;
import eu.ansquare.squarepowered.cca.MultiInventoryComponent;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

public class SquareEvents {
	public static void init(){
		UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
			if(player instanceof ServerPlayerEntity){
				BlockPos pos = hitResult.getBlockPos().offset(hitResult.getSide());
				if(MultiInventoryComponent.isNoDrop(player)) BlockDataApi.setBoolean(pos, world, "no_drop", true);
			}
			return ActionResult.PASS;
		});
	}
}
