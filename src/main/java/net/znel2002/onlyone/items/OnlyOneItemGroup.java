package net.znel2002.onlyone.items;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.znel2002.onlyone.OnlyOneMod;

public class OnlyOneItemGroup {
    public static final ItemGroup CROCANIUM = FabricItemGroup.builder(new Identifier("example", "test_group"))
            .displayName(Text.literal("Example Item Group"))
            .icon(() -> new ItemStack(OnlyOneItems.CROCANIUM_INGOT))
            .entries((enabledFeatures, entries, operatorEnabled) -> {
                entries.add(OnlyOneItems.CROCANIUM_INGOT);
            })
            .build();
}
