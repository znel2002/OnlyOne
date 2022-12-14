package net.znel2002.onlyone.items;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.znel2002.onlyone.OnlyOneMod;

public class OnlyOneItemGroup {
    public static final ItemGroup CROCANIUM = FabricItemGroupBuilder.build(new Identifier(
            OnlyOneMod.MOD_ID, "crocanium"
    ),() -> new ItemStack(OnlyOneItems.CROCANIUM_INGOT));
}
