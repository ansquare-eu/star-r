package eu.ansquare.starr.client.screen;

import eu.ansquare.starr.screenhandler.TeleportScreenHandler;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;

public class TeleportSelectScreen extends HandledScreen<TeleportScreenHandler> {
	public ButtonWidget button1;
	public ButtonWidget button2;

	public TeleportSelectScreen(TeleportScreenHandler handler,PlayerInventory inventory, Text title) {
		super(handler, inventory, title);

	}

	@Override
	protected void init() {
		button1 = ButtonWidget.builder(Text.literal("Button 1"), button -> {
					System.out.println("You clicked button1!");
				})
				.positionAndSize(width / 2 - 205, 20, 200, 20)
				.tooltip(Tooltip.create(Text.literal("Tooltip of button1")))
				.build();
		button2 = ButtonWidget.builder(Text.literal("Button 2"), button -> {
					System.out.println("You clicked button2!");
				})
				.positionAndSize(width / 2 + 5, 20, 200, 20)
				.tooltip(Tooltip.create(Text.literal("Tooltip of button2")))
				.build();

		addDrawableChild(button1);
		addDrawableChild(button2);

	}

	@Override
	protected void drawBackground(GuiGraphics graphics, float delta, int mouseX, int mouseY) {

	}

}
