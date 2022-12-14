package net.znel2002.onlyone.recipes;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.znel2002.onlyone.OnlyOneMod;

public class OnlyOneRecipes {
    public static void registerRecipes() {
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(OnlyOneMod.MOD_ID, RobotPartForgeRecipes.Serializer.ID),
                RobotPartForgeRecipes.Serializer.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, new Identifier(OnlyOneMod.MOD_ID, RobotPartForgeRecipes.Type.ID),
                RobotPartForgeRecipes.Type.INSTANCE);
    }
}
