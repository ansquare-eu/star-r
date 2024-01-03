package eu.ansquare.squarepowered.actionscreen.client;

import com.mojang.blaze3d.systems.RenderSystem;
import eu.ansquare.squarepowered.actionscreen.ActionScreenHandler;
import eu.ansquare.squarepowered.actionscreen.LocationTeleportActionScreenHandler;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.AbstractFurnaceScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.LinkedList;
import java.util.List;

public abstract class ActionScreen<T extends ActionScreenHandler> extends HandledScreen<T> {
	private final Identifier texture;
	private List<Drawable> drawables = new LinkedList<>();
	private List<TextFieldWidget> textFields = new LinkedList<>();
	private int x, y;
	public String screenKey;

	protected void drawForeground(GuiGraphics graphics, int mouseX, int mouseY) {
		graphics.drawText(this.textRenderer, this.title, this.titleX, this.titleY, 4210752, false);
	}
	public ActionScreen(T handler, PlayerInventory inventory, Text title, Identifier texture, int width, int height) {
		super(handler, inventory, title);
		this.texture = texture;
		this.width = width;
		this.height = height;
	}
	@Override
	public void init() {
		super.init();
		this.x = (this.width / 2 - this.backgroundWidth / 2);
		this.y = (this.height / 2 - this.backgroundHeight / 2);
	}
	public TextFieldWidget addTextField(int x, int y, int i, int j, String translateKey, int maxlenght){
		int finalX = x;
		int finalY = y;
		if(x == -200) finalX = this.backgroundWidth / 2 - i/2;
		if(y == -200) finalY = this.backgroundHeight / 2 - j / 2;
		TextFieldWidget box = new TextFieldWidget(this.textRenderer, this.x + finalX, this.y + finalY, i, j, Text.translatable("screen.starr." + screenKey + ".textfield." + translateKey));
		box.setMaxLength(32767);
		this.addSelectableChild(box);
		drawables.add(box);
		textFields.add(box);
		return box;
	}
	public ButtonWidget addButton(String translateKey, int actionId, int x, int y, int i, int j){
		int finalX = x;
		int finalY = y;
		if(x == -200) finalX = this.backgroundWidth / 2 - i/2;
		if(y == -200) finalY = this.backgroundHeight / 2 - j / 2;
		ButtonWidget button = ButtonWidget.builder(Text.translatable("screen.starr." + screenKey +".button." + translateKey), e -> {
			sendPacket(actionId);
		}).positionAndSize(this.x + finalX, this.y + finalY, i, j).build();
		this.addDrawableChild(button);
		return button;
	}
	public abstract void sendPacket(int actionId);
	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.client.player.closeHandledScreen();
			return true;
		}
		for (TextFieldWidget t:textFields) {
			if(t.isFocused()){
				return t.keyPressed(key, b ,c);
			}
		}
		return super.keyPressed(key, b, c);
	}
	@Override
	public void handledScreenTick() {
		super.handledScreenTick();
		textFields.forEach(textFieldWidget -> textFieldWidget.tick());
	}
	@Override
	public void render(GuiGraphics ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.drawMouseoverTooltip(ms, mouseX, mouseY);
		drawables.forEach(drawable -> drawable.render(ms, mouseX, mouseY, partialTicks));
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
}
