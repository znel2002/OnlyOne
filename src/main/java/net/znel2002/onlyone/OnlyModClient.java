package net.znel2002.onlyone;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.WorldEvents;
import net.znel2002.onlyone.client.ChargeHUD;
import net.znel2002.onlyone.event.KeyInputHandler;
import net.znel2002.onlyone.networking.InternMessage;
import net.znel2002.onlyone.screen.OnlyOneScreenHandlers;
import net.znel2002.onlyone.screen.RobotPartForgeScreen;
import net.znel2002.onlyone.util.IEntityDataSaver;
import net.znel2002.onlyone.util.MarkBlock;


public class OnlyModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // Don't copy
        KeyInputHandler.register();
        InternMessage.registerS2CPackets();
        HudRenderCallback.EVENT.register(new ChargeHUD());
        HandledScreens.register(OnlyOneScreenHandlers.ROBOT_PART_FORGE, RobotPartForgeScreen::new);
        // For you relevant
        WorldRenderEvents.LAST.register((worldRenderContext) -> {
            MarkBlock.oldMarkBlock(worldRenderContext);

    });
    }
}
