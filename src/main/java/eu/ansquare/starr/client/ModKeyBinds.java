package eu.ansquare.starr.client;

import com.mojang.blaze3d.platform.InputUtil;
import eu.ansquare.starr.network.ModPackets;
import eu.ansquare.starr.superdude.PowerOrder;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBind;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import org.quiltmc.qsl.networking.api.PacketByteBufs;
import org.quiltmc.qsl.networking.api.client.ClientPlayNetworking;

public class ModKeyBinds {
	private static KeyBind transformPowerKey;
	private static KeyBind firstPowerKey;
	public static void init(){
		transformPowerKey = KeyBindingHelper.registerKeyBinding(new KeyBind(
				"key.starr.transformation", // The translation key of the keybinding's name
				InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
				GLFW.GLFW_KEY_O, // The keycode of the key
				"category.starr.powers" // The translation key of the keybinding's category.
		));
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (transformPowerKey.wasPressed()) {
				ClientPlayNetworking.send(ModPackets.POWER_PACKET_ID, PacketByteBufs.create().writeEnumConstant(PowerOrder.TRANSFORMATION));
			}
		});
		firstPowerKey = KeyBindingHelper.registerKeyBinding(new KeyBind(
				"key.starr.firstpower", // The translation key of the keybinding's name
				InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
				GLFW.GLFW_KEY_I, // The keycode of the key
				"category.starr.powers" // The translation key of the keybinding's category.
		));
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (firstPowerKey.wasPressed()) {
				ClientPlayNetworking.send(ModPackets.POWER_PACKET_ID, PacketByteBufs.create().writeEnumConstant(PowerOrder.FIRST));
			}
		});
	}
}
