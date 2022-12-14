package net.znel2002.onlyone.screen;

import net.minecraft.screen.ScreenHandlerType;
import net.znel2002.onlyone.OnlyOneMod;
import net.znel2002.onlyone.blocks.entity.RobotPartForgeEntity;

public class OnlyOneScreenHandlers {
    public static ScreenHandlerType<RobotPartForgeScreenHandler> ROBOT_PART_FORGE;

    public static void registerAllScreenHandlers(){
        ROBOT_PART_FORGE = new ScreenHandlerType<>(RobotPartForgeScreenHandler::new);
    }

}
