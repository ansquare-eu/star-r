package eu.ansquare.squarepowered.actionscreen.client;

import eu.ansquare.squarepowered.SquareNetworking;
import eu.ansquare.squarepowered.Squarepowered;
import eu.ansquare.squarepowered.actionscreen.handler.LocalizeActionScreenHandler;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.scoreboard.Team;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.GameMode;
import org.quiltmc.qsl.networking.api.PacketByteBufs;
import org.quiltmc.qsl.networking.api.client.ClientPlayNetworking;

import java.util.List;

public class LocalizeScreen extends ActionScreen<LocalizeActionScreenHandler> {
	List<PlayerListEntry> entries;

	public LocalizeScreen(LocalizeActionScreenHandler handler, PlayerInventory inventory, Text title) {
		super(handler, inventory, title, Squarepowered.id("textures/screen/teleportscreen.png"), 176, 166);
		screenKey = "localize";
	}
	public void init(){
		super.init();
		entries = getVisibleEntries();
		for (int i = 0; i < Math.min(entries.size(), 8); i++) {
			TextWidget widget = new TextWidget(getPlayerName(entries.get(i)), textRenderer);
			widget.setPosition(x + 15, y + i * 30 + 25);
			widget.setTextColor(0xFF0000);
			addDrawable(widget);
			addButton("tp", this.backgroundWidth - 65, i * 30 + 20, 50, 20 ,i);
		}
	}
	private List<PlayerListEntry> getVisibleEntries() {
		return this.client.player.networkHandler.getListedPlayers().stream().toList();
	}
	@Override
	public void sendPacket(int... actions) {
		PacketByteBuf buf = PacketByteBufs.create();
		buf.writeInt(2);
		buf.writeUuid(entries.get(actions[0]).getProfile().getId());
		ClientPlayNetworking.send(SquareNetworking.ACTION_SCREEN_PACKET, buf);
	}

	public Text getPlayerName(PlayerListEntry entry) {
		return entry.getDisplayName() != null
				? this.applyGameModeFormatting(entry, entry.getDisplayName().copy())
				: this.applyGameModeFormatting(entry, Team.decorateName(entry.getScoreboardTeam(), Text.literal(entry.getProfile().getName())));
	}

	/**
	 * {@linkplain net.minecraft.util.Formatting#ITALIC Italicizes} the given text if
	 * the given player is in {@linkplain net.minecraft.world.GameMode#SPECTATOR spectator mode}.
	 */
	private Text applyGameModeFormatting(PlayerListEntry entry, MutableText name) {
		return entry.getGameMode() == GameMode.SPECTATOR ? name.formatted(Formatting.ITALIC) : name;
	}
}
