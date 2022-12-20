package net.znel2002.onlyone.blocks.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.znel2002.onlyone.OnlyOneMod;
import net.znel2002.onlyone.blocks.OnlyOneBlocks;

public class OnlyOneEntities {
    public static BlockEntityType<RobotPartForgeEntity> ROBOT_PART_FORGE;

    public static void register() {
        ROBOT_PART_FORGE = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(OnlyOneMod.MOD_ID, "player_robot_analyzer"),
                FabricBlockEntityTypeBuilder.create(RobotPartForgeEntity::new, OnlyOneBlocks.ROBOT_PART_FORGE).build(null));
    }
}
