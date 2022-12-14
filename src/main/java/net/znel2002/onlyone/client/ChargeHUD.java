package net.znel2002.onlyone.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;
import net.znel2002.onlyone.OnlyOneMod;
import net.znel2002.onlyone.util.IEntityDataSaver;

public class ChargeHUD implements HudRenderCallback {

    private static final Identifier Battery = new Identifier(OnlyOneMod.MOD_ID,"textures/hud/battery.png");
    private static final Identifier Full = new Identifier(OnlyOneMod.MOD_ID,"textures/hud/full1.png");


    @Override
    public void onHudRender(MatrixStack matrixStack, float tickDelta) {

        int x = 0;
        int y = 0;
        int text_x = 0;
        int text_y = 0;
        String text = "";
        MinecraftClient client = MinecraftClient.getInstance();

        if(client != null){

            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();


            x = width - 64;
            y = height - 256;
            text_x = x;
            text_y = y+26;

            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, Full);

            DrawableHelper.drawTexture(matrixStack, x, y+256-((IEntityDataSaver) client.player).getPersistentData().getInt("energy"), 0, 0, 64, ((IEntityDataSaver) client.player).getPersistentData().getInt("energy"), 64, 256);

            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, Battery);

            DrawableHelper.drawTexture(matrixStack, x, y, 0, 0, 64, 256, 64, 256);

            DrawableHelper.drawCenteredTextWithShadow(matrixStack, client.textRenderer, Text.of(((((IEntityDataSaver) client.player).getPersistentData().getInt("energy") / 256.0) * 100.0) + "%").asOrderedText(), text_x+32, text_y, 0xFFFFFF);

        }
    }
}
