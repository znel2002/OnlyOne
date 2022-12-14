package net.znel2002.onlyone.util;

import net.minecraft.nbt.NbtCompound;

public class MarkedBlockSaver{

    public static int addXYZ(IEntityDataSaver player, int x, int y, int z){
        NbtCompound nbt = player.getPersistentData();
        int marked_X = nbt.getInt("markedX");
        int marked_Y = nbt.getInt("markedY");
        int marked_Z = nbt.getInt("markedZ");


        nbt.putInt("markedX", x);
        nbt.putInt("markedY", y);
        nbt.putInt("markedZ", z);

        return x;
    }


}
