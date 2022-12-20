package net.znel2002.onlyone.blocks.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.znel2002.onlyone.items.OnlyOneItems;
import net.znel2002.onlyone.recipes.RobotPartForgeRecipes;
import net.znel2002.onlyone.screen.RobotPartForgeScreenHandler;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class RobotPartForgeEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(5, ItemStack.EMPTY);

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 100;


    public RobotPartForgeEntity(BlockPos pos, BlockState state){

        super(OnlyOneEntities.ROBOT_PART_FORGE, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                switch (index) {
                    case 0: return RobotPartForgeEntity.this.progress;
                    case 1: return RobotPartForgeEntity.this.maxProgress;

                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: RobotPartForgeEntity.this.progress = value; break;
                    case 1: RobotPartForgeEntity.this.maxProgress = value; break;

                }
            }

            public int size() {
                return 2;
            }
        };
    }


    @Override
    public Text getDisplayName() {
        return Text.literal("Player Robot Analyzer");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new RobotPartForgeScreenHandler(syncId, inv, this, this.propertyDelegate);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("robot_part_forge.progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
        progress = nbt.getInt("robot_part_forge.progress");
    }

    public static void tick(World world, BlockPos blockPos, BlockState state, RobotPartForgeEntity entity) {
        if(world.isClient) return;

        if(hasRecipe(entity)) {
            entity.progress++;
            markDirty(world, blockPos, state);
            if(entity.progress >= entity.maxProgress) {
                craftItem(entity);
            }
        } else {
            entity.resetProgress();
            markDirty(world, blockPos, state);
        }
    }
    private void resetProgress() {
        this.progress = 0;
    }
    private static void craftItem(RobotPartForgeEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }
        Optional<RobotPartForgeRecipes> recipe = entity.world.getRecipeManager().getFirstMatch(RobotPartForgeRecipes.Type.INSTANCE, inventory, entity.getWorld());

        if(hasRecipe(entity)) {
            entity.removeStack(0, 1);
            entity.removeStack(1, 1);
            entity.removeStack(2, 1);
            entity.removeStack(3, 1);
            entity.setStack(4, new ItemStack(recipe.get().getOutput().getItem(),
                    entity.getStack(4).getCount() + 1));

            entity.resetProgress();
        }
    }
    private static boolean hasRecipe(RobotPartForgeEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<RobotPartForgeRecipes> match = entity.world.getRecipeManager().getFirstMatch(RobotPartForgeRecipes.Type.INSTANCE, inventory, entity.getWorld());



        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getOutput().getItem());
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory, Item output) {
        return inventory.getStack(4).getItem() == output || inventory.getStack(4).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleInventory inventory) {
        return inventory.getStack(4).getMaxCount() > inventory.getStack(4).getCount();
    }


}
