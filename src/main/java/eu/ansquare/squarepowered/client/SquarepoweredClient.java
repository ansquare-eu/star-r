package eu.ansquare.squarepowered.client;

import com.mojang.blaze3d.platform.InputUtil;
import io.github.apace100.apoli.ApoliClient;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBind;
import org.lwjgl.glfw.GLFW;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

public class SquarepoweredClient implements ClientModInitializer {
	public static KeyBind powerKeybind3;
	public static KeyBind powerKeybind4;
	public static KeyBind powerKeybind5;
	public static KeyBind powerKeybind6;
	public static KeyBind powerKeybind7;
	public static KeyBind powerKeybind8;
	public static KeyBind powerKeybind9;
	public static KeyBind powerKeybind10;
	public static KeyBind powerKeybindTransform;

	public static KeyBind incrementKey;
	public static KeyBind decrementKey;
	@Override
	public void onInitializeClient(ModContainer mod) {
		initKeyBinds();
	}
	private void initKeyBinds(){
		powerKeybind3 = new KeyBind("key.origins.3_active", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_M, "category.origins");
		powerKeybind4 = new KeyBind("key.origins.4_active", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_N, "category.origins");
		powerKeybind5 = new KeyBind("key.origins.5_active", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_B, "category.origins");
		powerKeybind6 = new KeyBind("key.origins.6_active", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_I, "category.origins");
		powerKeybind7 = new KeyBind("key.origins.7_active", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_H, "category.origins");
		powerKeybind8 = new KeyBind("key.origins.8_active", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_Y, "category.origins");
		powerKeybind9 = new KeyBind("key.origins.9_active", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_V, "category.origins");
		powerKeybind10 = new KeyBind("key.origins.10_active", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_Z, "category.origins");
		powerKeybindTransform = new KeyBind("key.origins.11_active", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_MINUS, "category.origins");
		incrementKey = new KeyBind("key.origins.increment", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_EQUAL, "category.origins");
		decrementKey = new KeyBind("key.origins.decrement", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_MINUS, "category.origins");
		ApoliClient.registerPowerKeybinding("key.origins.3_active", powerKeybind3);
		ApoliClient.registerPowerKeybinding("key.origins.4_active", powerKeybind4);
		ApoliClient.registerPowerKeybinding("key.origins.5_active", powerKeybind5);
		ApoliClient.registerPowerKeybinding("key.origins.6_active", powerKeybind6);
		ApoliClient.registerPowerKeybinding("key.origins.7_active", powerKeybind7);
		ApoliClient.registerPowerKeybinding("key.origins.8_active", powerKeybind8);
		ApoliClient.registerPowerKeybinding("key.origins.9_active", powerKeybind9);
		ApoliClient.registerPowerKeybinding("key.origins.10_active", powerKeybind10);
		ApoliClient.registerPowerKeybinding("key.origins.11_active", powerKeybindTransform);
		ApoliClient.registerPowerKeybinding("key.origins.increment", incrementKey);
		ApoliClient.registerPowerKeybinding("key.origins.decrement", decrementKey);
		KeyBindingHelper.registerKeyBinding(powerKeybind3);
		KeyBindingHelper.registerKeyBinding(powerKeybind4);
		KeyBindingHelper.registerKeyBinding(powerKeybind5);
		KeyBindingHelper.registerKeyBinding(powerKeybind6);
		KeyBindingHelper.registerKeyBinding(powerKeybind7);
		KeyBindingHelper.registerKeyBinding(powerKeybind8);
		KeyBindingHelper.registerKeyBinding(powerKeybind9);
		KeyBindingHelper.registerKeyBinding(powerKeybind10);
		KeyBindingHelper.registerKeyBinding(powerKeybindTransform);
		KeyBindingHelper.registerKeyBinding(incrementKey);
		KeyBindingHelper.registerKeyBinding(decrementKey);
	}
}
