package net.znel2002.onlyone.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.znel2002.onlyone.util.EnergySaver;

import net.znel2002.onlyone.util.IEntityDataSaver;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_ONLYONE = "key.category.onylone.onlyone";
    public static final String KEY_DRINK_WATER = "key.onlyone.drink_water";

    public static KeyBinding drinkingKey;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (drinkingKey.wasPressed()) {
                client.player.sendMessage(Text.of("HELLO WORLD"), true);
                EnergySaver.addEnergy(((IEntityDataSaver) client.player), 10);
                client.player.sendMessage(Text.of(String.valueOf(((IEntityDataSaver) client.player).getPersistentData().getInt("energy"))));

            }
        });
    }

    public static void register() {
        drinkingKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_DRINK_WATER,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_C,
                KEY_CATEGORY_ONLYONE
        ));
        registerKeyInputs();
    }
}
