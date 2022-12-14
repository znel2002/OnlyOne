package net.znel2002.onlyone.util;

import net.minecraft.nbt.NbtCompound;

public class EnergySaver {

    public static int addEnergy(IEntityDataSaver player, int energyToAdd){
        NbtCompound nbt = player.getPersistentData();
        int energy = nbt.getInt("energy");
        if(energy + energyToAdd > 256){
            energy = 256;
}else{
            energy += energyToAdd;
        }
        nbt.putInt("energy", energy);
        return energy;
        }


    public static int removeEnergy(IEntityDataSaver player, int energyToAdd){
        NbtCompound nbt = player.getPersistentData();
        int energy = nbt.getInt("energy");
        if(energy - energyToAdd < 0){
            energy = 0;
        }else{
            energy -= energyToAdd;
        }
        nbt.putInt("energy", energy);
        return energy;
    }

}
