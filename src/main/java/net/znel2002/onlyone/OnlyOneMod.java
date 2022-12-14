package net.znel2002.onlyone;

import net.fabricmc.api.ModInitializer;
import net.znel2002.onlyone.blocks.OnlyOneBlocks;
import net.znel2002.onlyone.blocks.entity.OnlyOneEntities;
import net.znel2002.onlyone.items.OnlyOneItems;
import net.znel2002.onlyone.networking.InternMessage;
import net.znel2002.onlyone.recipes.OnlyOneRecipes;
import net.znel2002.onlyone.screen.OnlyOneScreenHandlers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OnlyOneMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("onlyone");
	public static final String MOD_ID = "onlyone";
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
		OnlyOneItems.registerModItems();
		OnlyOneBlocks.registerModBlocks();
		InternMessage.registerC2SPackets();
		OnlyOneEntities.register();
		OnlyOneScreenHandlers.registerAllScreenHandlers();
		OnlyOneRecipes.registerRecipes();
	}
}
