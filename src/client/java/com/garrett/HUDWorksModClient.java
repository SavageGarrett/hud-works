package com.garrett;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.garrett.events.ArmorStatusRenderer;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class HUDWorksModClient implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("hud-works");

	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as
		// rendering.

		HudRenderCallback.EVENT.register(new ArmorStatusRenderer());

		LOGGER.info("HUD Works Mod Initialized");
	}
}