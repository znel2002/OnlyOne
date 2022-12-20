package net.znel2002.onlyone.recipes;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import net.znel2002.onlyone.OnlyOneMod;

public class OnlyOneRecipes {
    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(OnlyOneMod.MOD_ID, RobotPartForgeRecipes.Serializer.ID),
                RobotPartForgeRecipes.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(OnlyOneMod.MOD_ID, RobotPartForgeRecipes.Type.ID),
                RobotPartForgeRecipes.Type.INSTANCE);
    }
}
