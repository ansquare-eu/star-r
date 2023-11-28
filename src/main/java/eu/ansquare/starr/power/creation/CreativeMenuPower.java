package eu.ansquare.starr.power.creation;

import eu.ansquare.starr.network.ModPackets;
import eu.ansquare.starr.power.Power;
import eu.ansquare.starr.power.Powers;
import eu.ansquare.starr.power.ToggleablePower;
import eu.ansquare.starr.util.item.ItemArrayProvider;
import eu.ansquare.starr.util.item.ItemUtils;
import eu.ansquare.starr.util.network.ClientPlayerState;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.GameMode;
import org.quiltmc.qsl.networking.api.PacketByteBufs;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;

public class CreativeMenuPower extends ToggleablePower {


	@Override
	public void activationAction(ServerPlayerEntity player) {
		ServerPlayNetworking.send(player, ModPackets.TOGGLE_PLAYER_STATE_PACKET_ID, PacketByteBufs.create().writeEnumConstant(ClientPlayerState.CREATIVE).writeUuid(player.getUuid()));
	}

	@Override
	public void deactivationAction(ServerPlayerEntity player) {
		ServerPlayNetworking.send(player, ModPackets.TOGGLE_PLAYER_STATE_PACKET_ID, PacketByteBufs.create().writeEnumConstant(ClientPlayerState.CREATIVE).writeUuid(player.getUuid()));
	}

	@Override
	public void activeTick(LivingEntity entity) {

	}
}
