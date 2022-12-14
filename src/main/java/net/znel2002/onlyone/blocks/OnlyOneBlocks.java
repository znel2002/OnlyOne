package net.znel2002.onlyone.blocks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.znel2002.onlyone.OnlyOneMod;
import net.znel2002.onlyone.blocks.custom.CrocaniumLamp;
import net.znel2002.onlyone.blocks.custom.JumpyBlock;
import net.znel2002.onlyone.blocks.custom.RobotPartForge;
import net.znel2002.onlyone.items.OnlyOneItemGroup;

public class OnlyOneBlocks {

    public static final Block CROCANIUM_BLOCK = registerBlock("crocanium_block",
            new Block(FabricBlockSettings.of(Material.METAL).strength(4f)), OnlyOneItemGroup.CROCANIUM);
    public static final Block CROCANIUM_ORE = registerBlock("crocanium_ore",
            new Block(FabricBlockSettings.of(Material.STONE).strength(3f)), OnlyOneItemGroup.CROCANIUM);
    public static final Block DEEPSLATE_CROCANIUM_ORE = registerBlock("deepslate_crocanium_ore",
            new Block(FabricBlockSettings.of(Material.STONE).strength(3f)), OnlyOneItemGroup.CROCANIUM);

    // Custom

    public static final Block JUMPYBLOCK = registerBlock("jumpyblock",
            new JumpyBlock(FabricBlockSettings.of(Material.STONE).strength(3f)), OnlyOneItemGroup.CROCANIUM);
    public static final Block CROCANIUM_LAMP = registerBlock("crocanium_lamp",
            new CrocaniumLamp(FabricBlockSettings.of(Material.STONE).strength(3f).luminance(state -> state.get(CrocaniumLamp.LIT) ? 15 : 0)), OnlyOneItemGroup.CROCANIUM);
    public static final Block ROBOT_PART_FORGE = registerBlock("robot_part_forge",
            new RobotPartForge(FabricBlockSettings.of(Material.STONE).strength(3f)), OnlyOneItemGroup.CROCANIUM);
    private static Block registerBlock(String name, Block block, ItemGroup tab){
        registerBlockItem(name, block, tab);
        return Registry.register(Registry.BLOCK, new Identifier(OnlyOneMod.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup tab){
        return Registry.register(Registry.ITEM, new Identifier(OnlyOneMod.MOD_ID,name), new BlockItem(block,new FabricItemSettings().group(tab)));
    }

    public static void registerModBlocks() {
        OnlyOneMod.LOGGER.debug("Registering");
    }

}
