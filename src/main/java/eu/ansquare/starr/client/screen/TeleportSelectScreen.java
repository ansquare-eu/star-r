package eu.ansquare.starr.client.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;

public class TeleportSelectScreen extends Screen {
	public ButtonWidget button1;
	public ButtonWidget button2;

	public TeleportSelectScreen(Text title) {
		super(title);
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

}
