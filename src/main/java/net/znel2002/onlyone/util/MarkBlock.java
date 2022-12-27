package net.znel2002.onlyone.util;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.ShaderProgram;
import net.minecraft.client.render.*;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import net.znel2002.onlyone.OnlyOneMod;
import org.joml.Matrix4f;
import org.joml.Quaternionf;


public class MarkBlock {

    public static void oldMarkBlock(WorldRenderContext worldRenderContext) {
        RenderSystem.disableDepthTest();
        RenderSystem.setShader(GameRenderer::getRenderTypeLinesProgram);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        RenderSystem.disableTexture();

        Vec3d cameraPos = worldRenderContext.camera().getPos();
        IEntityDataSaver saver = ((IEntityDataSaver) MinecraftClient.getInstance().player);
        double markedX = saver.getPersistentData().getInt("markedX");
        double markedY = saver.getPersistentData().getInt("markedY");
        double markedZ = saver.getPersistentData().getInt("markedZ");
        if(markedX == 0 && markedY == 0 && markedZ == 0) return;
        double cameraX = cameraPos.x;
        double cameraY = cameraPos.y;
        double cameraZ = cameraPos.z;

        worldRenderContext.matrixStack().loadIdentity();
        worldRenderContext.matrixStack().translate(markedX-cameraX, markedY-cameraY, markedZ-cameraZ);
        // worldRenderContext.matrixStack().multiply(RotationAxis.POSITIVE_Y.rotation((float) (45.0 / 57.3)));
        Matrix4f matrix = worldRenderContext.matrixStack().peek().getPositionMatrix();
        bufferBuilder.begin(VertexFormat.DrawMode.DEBUG_LINES, VertexFormats.POSITION_COLOR);
        var client = MinecraftClient.getInstance();
        assert client.world != null;
        float progress = (client.world.getTime() % 40.0F) / 100.0F * 1.25F;

        // CREATE WITH DEBUG_LINE
        float lowerX = 0.0F /* (float) (markedX - cameraX)*/;
        float lowerY = 0.0F /* (float) (markedY - cameraY)*/ ;
        float lowerZ = 0.0F /* (float) (markedZ - cameraZ)*/;
        float sideUpY = 1F + progress;
        float sideDownY = lowerY - progress;
        float lowerLeftX = lowerX;
        float lowerLeftZ = lowerZ;
        float lowerRightX = lowerX + 1.0F;
        float lowerRightZ = lowerZ;
        float upperLeftX = lowerX;
        float upperLeftZ = lowerZ + 1.0F;
        float upperRightX = lowerX + 1.0F;
        float upperRightZ = lowerZ + 1.0F;
        float upperY = 1.0F;

        // Draw
        bufferBuilder.vertex(matrix, lowerLeftX, lowerY, lowerLeftZ).color(0F, 1F, 0F, 1F).next();
        bufferBuilder.vertex(matrix, lowerRightX, lowerY, lowerRightZ).color(0F, 1F, 0F, 1F).next();
        bufferBuilder.vertex(matrix, lowerRightX, lowerY, lowerRightZ).color(0F, 1F, 0F, 1F).next();
        bufferBuilder.vertex(matrix, upperRightX, lowerY, upperRightZ).color(0F, 1F, 0F, 1F).next();
        bufferBuilder.vertex(matrix, upperRightX, lowerY, upperRightZ).color(0F, 1F, 0F, 1F).next();
        bufferBuilder.vertex(matrix, upperLeftX, lowerY, upperLeftZ).color(0F, 1F, 0F, 1F).next();
        bufferBuilder.vertex(matrix, upperLeftX, lowerY, upperLeftZ).color(0F, 1F, 0F, 1F).next();
        bufferBuilder.vertex(matrix, lowerLeftX, lowerY, lowerLeftZ).color(0F, 1F, 0F, 1F).next();
        bufferBuilder.vertex(matrix, lowerLeftX, lowerY, lowerLeftZ).color(0F, 1F, 0F, 1F).next();
        // Up
        bufferBuilder.vertex(matrix,lowerLeftX, upperY, lowerLeftZ).color(0F, 1F, 0F, 1F).next();
        bufferBuilder.vertex(matrix,lowerRightX, upperY, lowerRightZ).color(0F, 1F, 0F, 1F).next();
        bufferBuilder.vertex(matrix,lowerRightX, upperY, lowerRightZ).color(0F, 1F, 0F, 1F).next();
        bufferBuilder.vertex(matrix,upperRightX, upperY, upperRightZ).color(0F, 1F, 0F, 1F).next();
        bufferBuilder.vertex(matrix,upperRightX, upperY, upperRightZ).color(0F, 1F, 0F, 1F).next();
        bufferBuilder.vertex(matrix,upperLeftX, upperY, upperLeftZ).color(0F, 1F, 0F, 1F).next();
        bufferBuilder.vertex(matrix,upperLeftX, upperY, upperLeftZ).color(0F, 1F, 0F, 1F).next();
        bufferBuilder.vertex(matrix,lowerLeftX, upperY, lowerLeftZ).color(0F, 1F, 0F, 1F).next();
        bufferBuilder.vertex(matrix,lowerLeftX, upperY, lowerLeftZ).color(0F, 1F, 0F, 1F).next();

        // Sides

        bufferBuilder.vertex(matrix,lowerLeftX, sideDownY, lowerLeftZ).color(1F, 1F, 0F, 1F).next();
        bufferBuilder.vertex(matrix,lowerLeftX, sideUpY, lowerLeftZ).color(1F, 1F, 0F, 1F).next();
        bufferBuilder.vertex(matrix,lowerRightX, sideUpY, lowerRightZ).color(1F, 1F, 0F, 1F).next();
        bufferBuilder.vertex(matrix,lowerRightX, sideDownY, lowerRightZ).color(1F, 1F, 0F, 1F).next();
        bufferBuilder.vertex(matrix,upperRightX, sideUpY, upperRightZ).color(1F, 1F, 0F, 1F).next();
        bufferBuilder.vertex(matrix,upperRightX, sideDownY, upperRightZ).color(1F, 1F, 0F, 1F).next();
        bufferBuilder.vertex(matrix,upperLeftX, sideUpY, upperLeftZ).color(1F, 1F, 0F, 1F).next();
        bufferBuilder.vertex(matrix,upperLeftX, sideDownY, upperLeftZ).color(1F, 1F, 0F, 1F).next();
        // Top
        bufferBuilder.vertex(matrix, lowerLeftX, upperY, lowerLeftZ).color(0F, 1F, 0F, 1F).next();
        bufferBuilder.vertex(matrix, lowerRightX, upperY, lowerRightZ).color(0F, 1F, 0F, 1F).next();
        bufferBuilder.vertex(matrix, lowerRightX, upperY, lowerRightZ).color(0F, 1F, 0F, 1F).next();
        bufferBuilder.vertex(matrix, upperRightX, upperY, upperRightZ).color(0F, 1F, 0F, 1F).next();
        bufferBuilder.vertex(matrix, upperRightX, upperY, upperRightZ).color(0F, 1F, 0F, 1F).next();
        bufferBuilder.vertex(matrix, upperLeftX, upperY, upperLeftZ).color(0F, 1F, 0F, 1F).next();
        bufferBuilder.vertex(matrix, upperLeftX, upperY, upperLeftZ).color(0F, 1F, 0F, 1F).next();
        bufferBuilder.vertex(matrix, lowerLeftX, upperY, lowerLeftZ).color(0F, 1F, 0F, 1F).next();
        bufferBuilder.vertex(matrix, lowerLeftX, upperY, lowerLeftZ).color(0F, 1F, 0F, 1F).next();



        tessellator.draw();

        // Quad
        RenderSystem.setShader(GameRenderer::getPositionColorProgram);
        RenderSystem.enableBlend();
        RenderSystem.disableCull();
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
        bufferBuilder.vertex(matrix, 0, 0, 0).color(0F, 1F, 1F, 0.3F).next();
        bufferBuilder.vertex(matrix, 0, 1, 0).color(0F, 1F, 1F, 0.3F).next();
        bufferBuilder.vertex(matrix, 1, 1, 0).color(0F, 1F, 1F, 0.3F).next();
        bufferBuilder.vertex(matrix, 1, 0, 0).color(0F, 1F, 1F, 0.3F).next();
        // next side
        bufferBuilder.vertex(matrix, 0, 0, 0).color(0F, 1F, 1F, 0.3F).next();
        bufferBuilder.vertex(matrix, 0, 0, 1).color(0F, 1F, 1F, 0.3F).next();
        bufferBuilder.vertex(matrix, 0, 1, 1).color(0F, 1F, 1F, 0.3F).next();
        bufferBuilder.vertex(matrix, 0, 1, 0).color(0F, 1F, 1F, 0.3F).next();
        // next side
        // Bottom bufferBuilder.vertex(matrix, 0, 0, 0).color(0F, 1F, 0F, 0.1F).next();
        // Bottom bufferBuilder.vertex(matrix, 1, 0, 0).color(0F, 1F, 0F, 0.1F).next();
        // Bottom bufferBuilder.vertex(matrix, 1, 0, 1).color(0F, 1F, 0F, 0.1F).next();
        // Bottom bufferBuilder.vertex(matrix, 0, 0, 1).color(0F, 1F, 0F, 0.1F).next();
        // next side
        bufferBuilder.vertex(matrix, 1, 0, 0).color(0F, 1F, 1F, 0.3F).next();
        bufferBuilder.vertex(matrix, 1, 1, 0).color(0F, 1F, 1F, 0.3F).next();
        bufferBuilder.vertex(matrix, 1, 1, 1).color(0F, 1F, 1F, 0.3F).next();
        bufferBuilder.vertex(matrix, 1, 0, 1).color(0F, 1F, 1F, 0.3F).next();
        // next side
        // TOP bufferBuilder.vertex(matrix, 0, 1, 0).color(0F, 1F, 0F, 0.1F).next();
        // TOP bufferBuilder.vertex(matrix, 0, 1, 1).color(0F, 1F, 0F, 0.1F).next();
        // TOP bufferBuilder.vertex(matrix, 1, 1, 1).color(0F, 1F, 0F, 0.1F).next();
        // TOP bufferBuilder.vertex(matrix, 1, 1, 0).color(0F, 1F, 0F, 0.1F).next();
        // next side
        bufferBuilder.vertex(matrix, 0, 0, 1).color(0F, 1F, 1F, 0.3F).next();
        bufferBuilder.vertex(matrix, 1, 0, 1).color(0F, 1F, 1F, 0.3F).next();
        bufferBuilder.vertex(matrix, 1, 1, 1).color(0F, 1F, 1F, 0.3F).next();
        bufferBuilder.vertex(matrix, 0, 1, 1).color(0F, 1F, 1F, 0.3F).next();

        tessellator.draw();
        RenderSystem.enableCull();
        RenderSystem.enableDepthTest();
        RenderSystem.enableTexture();


    }
    public static void markBlock(WorldRenderContext context){

    }

}

// License: All rights reserved
// This file is part of OnlyOneMod.
