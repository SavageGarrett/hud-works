package com.garrett.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class ArmorStatusRenderer implements HudRenderCallback {
    public static final Logger LOGGER = LoggerFactory.getLogger("hud-works");

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.player.getInventory() == null)
            return; // Ensure player and inventory are present

        int x = 10; // X position of the HUD on the screen
        int y = 10; // Y position of the HUD on the screen
        int yOffset = 0;

        for (int i = 0; i < client.player.getInventory().armor.size(); i++) {
            ItemStack armorItem = client.player.getInventory().armor.get(i);
            if (!armorItem.isEmpty()) {
                String armorText = armorItem.getName().getString() + " : " + armorItem.getDamage() + "/"
                        + armorItem.getMaxDamage();
                drawContext.drawText(client.textRenderer, Text.literal(armorText).formatted(Formatting.GOLD), x,
                        y + yOffset, 0xFFFFFF, false);
                yOffset += 10;
            }
        }
    }
}
