package net.znel2002.onlyone.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.znel2002.onlyone.OnlyOneMod;
import net.znel2002.onlyone.items.custom.CrocaniumMarker;
public class OnlyOneItems {

    public static final Item RAW_CROCANIUM = registerItem("raw_crocanium",
            new Item(new FabricItemSettings().fireproof().maxCount(24)));
    public static final Item CROCANIUM_INGOT = registerItem("crocanium_ingot",
            new Item(new FabricItemSettings().fireproof().maxCount(16)));

    // Custom

    public static final Item CrocaniumMarker = registerItem("crocanium_marker",
            new CrocaniumMarker(new FabricItemSettings().fireproof().maxCount(1)));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(OnlyOneMod.MOD_ID, name), item);
    }

    public static  void registerModItems(){
        OnlyOneMod.LOGGER.debug("Items registered");
        ItemGroupEvents.modifyEntriesEvent(OnlyOneItemGroup.CROCANIUM).register(entries -> entries.add(RAW_CROCANIUM));
        ItemGroupEvents.modifyEntriesEvent(OnlyOneItemGroup.CROCANIUM).register(entries -> entries.add(CROCANIUM_INGOT));
        ItemGroupEvents.modifyEntriesEvent(OnlyOneItemGroup.CROCANIUM).register(entries -> entries.add(CrocaniumMarker));
    }

}
