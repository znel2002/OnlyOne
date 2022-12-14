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


public class OnlyModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        KeyInputHandler.register();
        InternMessage.registerS2CPackets();
        HudRenderCallback.EVENT.register(new ChargeHUD());
        HandledScreens.register(OnlyOneScreenHandlers.ROBOT_PART_FORGE, RobotPartForgeScreen::new);
        WorldRenderEvents.LAST.register((worldRenderContext) -> {
            RenderSystem.disableDepthTest();
            RenderSystem.setShader(GameRenderer::getRenderTypeLinesShader);
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferBuilder = tessellator.getBuffer();
            RenderSystem.disableTexture();
            RenderSystem.disableBlend();

            Vec3d cameraPos = worldRenderContext.camera().getPos();
            IEntityDataSaver saver = ((IEntityDataSaver) MinecraftClient.getInstance().player);
            double markedX = saver.getPersistentData().getInt("markedX");
            double markedY = saver.getPersistentData().getInt("markedY");
            double markedZ = saver.getPersistentData().getInt("markedZ");
            double cameraX = cameraPos.x;
            double cameraY = cameraPos.y;
            double cameraZ = cameraPos.z;

            bufferBuilder.begin(VertexFormat.DrawMode.DEBUG_LINE_STRIP, VertexFormats.POSITION_COLOR);
            // bufferBuilder.vertex(0.0 + markedX, 0+markedY, 0.0 + markedZ).color(0F, 1F, 0F, 1F).next();
            // bufferBuilder.vertex(1.0 + markedX, 0+markedY, 1.0 + markedZ).color(1F, 1F, 0F, 1F).next();
            bufferBuilder.vertex( 1.0+markedX - cameraX, markedY - cameraY, markedZ - cameraZ).color(0F, 1F, 0F, 1F).next();
            bufferBuilder.vertex(markedX - cameraX, markedY - cameraY, markedZ - cameraZ).color(0F, 1F, 0F, 1F).next();
            bufferBuilder.vertex(markedX - cameraX, markedY - cameraY, markedZ - cameraZ + 1.0).color(0F, 1F, 0F, 1F).next();
            bufferBuilder.vertex( 1.0+markedX - cameraX, markedY - cameraY, markedZ - cameraZ + 1.0).color(0F, 1F, 0F, 1F).next();
            bufferBuilder.vertex( 1.0+markedX - cameraX, markedY - cameraY, markedZ - cameraZ).color(0F, 1F, 0F, 1F).next();
            tessellator.draw();

            RenderSystem.enableDepthTest();
            RenderSystem.enableBlend();
            RenderSystem.enableTexture();

    });
    }
}
