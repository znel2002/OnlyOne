package net.znel2002.onlyone.networking;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import net.znel2002.onlyone.OnlyOneMod;
import net.znel2002.onlyone.networking.packets.ExampleC2S;

public class InternMessage {

    public static final Identifier ENERGY_ID = new Identifier(OnlyOneMod.MOD_ID, "energy");
    public static final Identifier ENERGY_SYNC = new Identifier(OnlyOneMod.MOD_ID, "energy_sync");
    public static final Identifier EXAMPLE_ID = new Identifier(OnlyOneMod.MOD_ID, "example");

    public static void registerC2SPackets(){
        ServerPlayNetworking.registerGlobalReceiver(EXAMPLE_ID, ExampleC2S::recieve);
    }
    public static void registerS2CPackets(){

    }
}
