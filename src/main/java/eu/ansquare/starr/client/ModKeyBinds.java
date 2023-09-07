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
	private static KeyBind secondPowerKey;
	private static KeyBind thirdPowerKey;
	private static KeyBind fourthPowerKey;
	private static KeyBind fifthPowerKey;
	private static KeyBind sixthPowerKey;
	private static KeyBind seventhPowerKey;
	private static KeyBind eightPowerKey;
	private static KeyBind ninthPowerKey;
	private static KeyBind tenthPowerKey;

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
		secondPowerKey = KeyBindingHelper.registerKeyBinding(new KeyBind(
				"key.starr.secondpower", // The translation key of the keybinding's name
				InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
				GLFW.GLFW_KEY_K, // The keycode of the key
				"category.starr.powers" // The translation key of the keybinding's category.
		));
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (secondPowerKey.wasPressed()) {
				ClientPlayNetworking.send(ModPackets.POWER_PACKET_ID, PacketByteBufs.create().writeEnumConstant(PowerOrder.SECOND));
			}
		});
		thirdPowerKey = KeyBindingHelper.registerKeyBinding(new KeyBind(
				"key.starr.thirdpower", // The translation key of the keybinding's name
				InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
				GLFW.GLFW_KEY_M, // The keycode of the key
				"category.starr.powers" // The translation key of the keybinding's category.
		));
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (thirdPowerKey.wasPressed()) {
				ClientPlayNetworking.send(ModPackets.POWER_PACKET_ID, PacketByteBufs.create().writeEnumConstant(PowerOrder.THIRD));
			}
		});
		fourthPowerKey = KeyBindingHelper.registerKeyBinding(new KeyBind(
				"key.starr.fourthpower", // The translation key of the keybinding's name
				InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
				GLFW.GLFW_KEY_N, // The keycode of the key
				"category.starr.powers" // The translation key of the keybinding's category.
		));
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (fourthPowerKey.wasPressed()) {
				ClientPlayNetworking.send(ModPackets.POWER_PACKET_ID, PacketByteBufs.create().writeEnumConstant(PowerOrder.FOURTH));
			}
		});
		fifthPowerKey = KeyBindingHelper.registerKeyBinding(new KeyBind(
				"key.starr.fifthpower", // The translation key of the keybinding's name
				InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
				GLFW.GLFW_KEY_B, // The keycode of the key
				"category.starr.powers" // The translation key of the keybinding's category.
		));
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (fifthPowerKey.wasPressed()) {
				ClientPlayNetworking.send(ModPackets.POWER_PACKET_ID, PacketByteBufs.create().writeEnumConstant(PowerOrder.FIFTH));
			}
		});
		sixthPowerKey = KeyBindingHelper.registerKeyBinding(new KeyBind(
				"key.starr.sixthpower", // The translation key of the keybinding's name
				InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
				GLFW.GLFW_KEY_G, // The keycode of the key
				"category.starr.powers" // The translation key of the keybinding's category.
		));
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (sixthPowerKey.wasPressed()) {
				ClientPlayNetworking.send(ModPackets.POWER_PACKET_ID, PacketByteBufs.create().writeEnumConstant(PowerOrder.SIXTH));
			}
		});
		seventhPowerKey = KeyBindingHelper.registerKeyBinding(new KeyBind(
				"key.starr.seventhpower", // The translation key of the keybinding's name
				InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
				GLFW.GLFW_KEY_H, // The keycode of the key
				"category.starr.powers" // The translation key of the keybinding's category.
		));
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (seventhPowerKey.wasPressed()) {
				ClientPlayNetworking.send(ModPackets.POWER_PACKET_ID, PacketByteBufs.create().writeEnumConstant(PowerOrder.SEVENTH));
			}
		});
		eightPowerKey = KeyBindingHelper.registerKeyBinding(new KeyBind(
				"key.starr.eightpower", // The translation key of the keybinding's name
				InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
				GLFW.GLFW_KEY_Y, // The keycode of the key
				"category.starr.powers" // The translation key of the keybinding's category.
		));
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (eightPowerKey.wasPressed()) {
				ClientPlayNetworking.send(ModPackets.POWER_PACKET_ID, PacketByteBufs.create().writeEnumConstant(PowerOrder.EIGHT));
			}
		});
		ninthPowerKey = KeyBindingHelper.registerKeyBinding(new KeyBind(
				"key.starr.ninthpower", // The translation key of the keybinding's name
				InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
				GLFW.GLFW_KEY_V, // The keycode of the key
				"category.starr.powers" // The translation key of the keybinding's category.
		));
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (ninthPowerKey.wasPressed()) {
				ClientPlayNetworking.send(ModPackets.POWER_PACKET_ID, PacketByteBufs.create().writeEnumConstant(PowerOrder.NINHT));
			}
		});
		tenthPowerKey = KeyBindingHelper.registerKeyBinding(new KeyBind(
				"key.starr.tenthpower", // The translation key of the keybinding's name
				InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
				GLFW.GLFW_KEY_Z, // The keycode of the key
				"category.starr.powers" // The translation key of the keybinding's category.
		));
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (tenthPowerKey.wasPressed()) {
				ClientPlayNetworking.send(ModPackets.POWER_PACKET_ID, PacketByteBufs.create().writeEnumConstant(PowerOrder.TENTH));
			}
		});
	}
}
