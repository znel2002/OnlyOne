package net.znel2002.onlyone.items.custom;

import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.znel2002.onlyone.OnlyOneMod;
import net.znel2002.onlyone.util.IEntityDataSaver;

public class CrocaniumMarker extends Item {
    public CrocaniumMarker(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        ((IEntityDataSaver) context.getPlayer()).getPersistentData().putInt("markedX", context.getBlockPos().getX());
        ((IEntityDataSaver) context.getPlayer()).getPersistentData().putInt("markedY", context.getBlockPos().getY());
        ((IEntityDataSaver) context.getPlayer()).getPersistentData().putInt("markedZ", context.getBlockPos().getZ());
        context.getPlayer().sendMessage(Text.of("Marked block at " + context.getBlockPos().getX() + ", " + context.getBlockPos().getY() + ", " + context.getBlockPos().getZ()), true);
        return super.useOnBlock(context);
    }
}
