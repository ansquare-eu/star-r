package eu.ansquare.starr.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import eu.ansquare.starr.network.ModPackets;
import eu.ansquare.starr.screenhandler.TeleportScreenHandler;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.quiltmc.qsl.networking.api.PacketByteBufs;
import org.quiltmc.qsl.networking.api.client.ClientPlayNetworking;

import java.util.HashMap;

public class TeleportSelectScreen extends HandledScreen<TeleportScreenHandler> {
	private final static HashMap<String, Object> guistate = TeleportScreenHandler.guistate;
	private final World world;
	private int x, y;
	private final PlayerEntity entity;
	TextFieldWidget zabox;
	TextFieldWidget xbox;
	TextFieldWidget ybox;
	ButtonWidget button_base;
	ButtonWidget button_1;
	ButtonWidget button_2;
	ButtonWidget button_save_1;
	ButtonWidget button_save_2;
	ButtonWidget button_save_base;
	ButtonWidget button_tp;

	public TeleportSelectScreen(TeleportScreenHandler container, PlayerInventory inventory, Text text) {
		super(container, inventory, text);
		this.world = container.world;

		this.entity = container.entity;
		this.backgroundWidth = 176;
		this.backgroundHeight = 166;
	}

	private static final Identifier texture = new Identifier("starr:textures/screen/teleportscreen.png");

	@Override
	public void render(GuiGraphics ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.drawMouseoverTooltip(ms, mouseX, mouseY);
		zabox.render(ms, mouseX, mouseY, partialTicks);
		xbox.render(ms, mouseX, mouseY, partialTicks);
		ybox.render(ms, mouseX, mouseY, partialTicks);
	}

	@Override
	protected void drawBackground(GuiGraphics ms, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShaderTexture(0, texture);
		ms.drawTexture(texture, this.x, this.y, 0, 0, this.backgroundWidth, this.backgroundHeight, this.backgroundWidth, this.backgroundHeight);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.client.player.closeHandledScreen();
			return true;
		}
		if (zabox.isFocused())
			return zabox.keyPressed(key, b, c);
		if (xbox.isFocused())
			return xbox.keyPressed(key, b, c);
		if (ybox.isFocused())
			return ybox.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void handledScreenTick() {
		super.handledScreenTick();
		zabox.tick();
		xbox.tick();
		ybox.tick();
	}

	@Override
	protected void drawForeground(GuiGraphics poseStack, int mouseX, int mouseY) {
	}

	@Override
	public void closeScreen() {
		super.closeScreen();
	}

	@Override
	public void init() {
		super.init();
		this.x = this.getScreenHandler().x + (this.width / 2 - this.backgroundWidth / 2);
		this.y = this.getScreenHandler().y + (this.height / 2 - this.backgroundHeight / 2);
		zabox = new TextFieldWidget(this.textRenderer, this.x + 42, this.y + 142, 120, 20, Text.translatable("gui.starr.teleport.zabox"));
		zabox.setMaxLength(32767);
		guistate.put("text:zabox", zabox);
		this.addSelectableChild(this.zabox);
		xbox = new TextFieldWidget(this.textRenderer, this.x + 42, this.y + 106, 120, 20, Text.translatable("gui.starr.teleport.xbox"));
		xbox.setMaxLength(32767);
		guistate.put("text:xbox", xbox);
		this.addSelectableChild(this.xbox);
		ybox = new TextFieldWidget(this.textRenderer, this.x + 42, this.y + 124, 120, 20, Text.translatable("gui.starr.teleport.ybox"));
		ybox.setMaxLength(32767);
		guistate.put("text:ybox", ybox);
		this.addSelectableChild(this.ybox);
		button_base = ButtonWidget.builder(Text.translatable("gui.starr.teleport.button_base"), e -> {
			ClientPlayNetworking.send(ModPackets.TPSAVED_PACKET_ID, PacketByteBufs.create().writeString("0"));
		}).positionAndSize(this.x + 15, this.y + 25, 46, 20).build();
		guistate.put("button:button_base", button_base);
		this.addDrawableChild(button_base);
		button_1 = ButtonWidget.builder(Text.translatable("gui.starr.teleport.button_1"), e -> {
			ClientPlayNetworking.send(ModPackets.TPSAVED_PACKET_ID, PacketByteBufs.create().writeString("1"));
		}).positionAndSize(this.x + 15, this.y + 52, 30, 20).build();
		guistate.put("button:button_1", button_1);
		this.addDrawableChild(button_1);
		button_2 = ButtonWidget.builder(Text.translatable("gui.starr.teleport.button_2"), e -> {
			ClientPlayNetworking.send(ModPackets.TPSAVED_PACKET_ID, PacketByteBufs.create().writeString("2"));
		}).positionAndSize(this.x + 15, this.y + 79, 30, 20).build();
		guistate.put("button:button_2", button_2);
		this.addDrawableChild(button_2);
		button_save_1 = ButtonWidget.builder(Text.translatable("gui.starr.teleport.button_save_1"), e -> {
			ClientPlayNetworking.send(ModPackets.SAVELOC_PACKET_ID, PacketByteBufs.create().writeString("1"));
		}).positionAndSize(this.x + 105, this.y + 52, 56, 20).build();
		guistate.put("button:button_save_1", button_save_1);
		this.addDrawableChild(button_save_1);
		button_save_2 = ButtonWidget.builder(Text.translatable("gui.starr.teleport.button_save_2"), e -> {
			ClientPlayNetworking.send(ModPackets.SAVELOC_PACKET_ID, PacketByteBufs.create().writeString("2"));
		}).positionAndSize(this.x + 105, this.y + 79, 56, 20).build();
		guistate.put("button:button_save_2", button_save_2);
		this.addDrawableChild(button_save_2);
		button_save_base = ButtonWidget.builder(Text.translatable("gui.starr.teleport.button_save_base"), e -> {
			ClientPlayNetworking.send(ModPackets.SAVELOC_PACKET_ID, PacketByteBufs.create().writeString("0"));
		}).positionAndSize(this.x + 96, this.y + 25, 72, 20).build();
		guistate.put("button:button_save_base", button_save_base);
		this.addDrawableChild(button_save_base);
		button_tp = ButtonWidget.builder(Text.translatable("gui.starr.teleport.button_tp"), e -> {
			try{
				ClientPlayNetworking.send(ModPackets.TPLOC_PACKET_ID, PacketByteBufs.create().writeIntArray(new int[] {Integer.parseInt(xbox.getText()), Integer.parseInt(ybox.getText()), Integer.parseInt(zabox.getText())}));
			} catch (NumberFormatException exc){

			}
		}).positionAndSize(this.x + 6, this.y + 124, 35, 20).build();
		guistate.put("button:button_tp", button_tp);
		this.addDrawableChild(button_tp);
	}

}
