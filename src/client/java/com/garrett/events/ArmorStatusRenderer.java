package com.garrett.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class ArmorStatusRenderer implements HudRenderCallback {

    private static final int ITEM_SIZE = 16; // Standard size of an item icon
    private static final int SPACING = 4; // Spacing between items

    public static final Logger LOGGER = LoggerFactory.getLogger("hud-works");

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.player.getInventory() == null)
            return; // Ensure player and inventory are present

        int x = 10; // X position of the HUD on the screen
        int y = 10; // Y position of the HUD on the screen
        int yOffset = 0;

        for (ItemStack armorItem : client.player.getInventory().armor.reversed()) {
            if (!armorItem.isEmpty()) {

                this.drawDurabilityBar(drawContext, armorItem, x, y + yOffset + ITEM_SIZE);
                drawContext.drawItem(armorItem, x, y + yOffset);

                yOffset += ITEM_SIZE + SPACING;
            }
        }
    }

    private void drawDurabilityBar(DrawContext drawContext, ItemStack itemStack, int x, int y) {
        int width = 13;

        int damage = itemStack.getDamage();
        int maxDamage = itemStack.getMaxDamage();
        int durabilityLeft = maxDamage - damage;

        float durabilityPercentage = (float) durabilityLeft / maxDamage;

        // Background of the durability bar
        drawContext.drawHorizontalLine(x + 2, x + width, y - 3, 0xFF000000);
        drawContext.drawHorizontalLine(x + 2, x + width, y - 2, 0xFF000000);

        int alpha = 0xFF;

        // Foreground of the durability bar (red to green gradient or a solid color)
        int barWidth = (int) (durabilityPercentage * width);
        int color = (int) (255 * (1 - durabilityPercentage)); // More damage, more red
        color = (color << 16) | 0x0000FF00; // Green to red gradient
        color = color | alpha << 24;
        drawContext.drawHorizontalLine(x + 2, x + barWidth, y - 3, color);
    }
}
