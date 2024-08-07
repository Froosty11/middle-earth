package net.jukoz.me.block.special.forge;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.block.ModBlockEntities;
import net.jukoz.me.block.ModDecorativeBlocks;
import net.jukoz.me.gui.forge.ForgeScreenHandler;
import net.jukoz.me.item.ModResourceItems;
import net.jukoz.me.network.packets.ForgeOutputPacket;
import net.jukoz.me.recipe.AlloyingRecipe;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ForgeBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, SidedInventory {
    private static final String ID = "forge";
    public static final int MAX_PROGRESS = 1200;
    public static final int MAX_STORAGE = 2304;
    public static final int MAX_BOOST_TIME = 10;
    public static final int FUEL_SLOT = 0;
    public static final int OUTPUT_SLOT = 5;
    private final DefaultedList<ItemStack> inventory =
            DefaultedList.ofSize(6, ItemStack.EMPTY);
    protected final PropertyDelegate propertyDelegate;
    protected final Map<Item, Integer> fuelTimeMap = AbstractFurnaceBlockEntity.createFuelTimeMap();
    private int progress = 0;
    private int boostTime = 0;
    private int fuelTime = 0;
    private int maxFuelTime = 0;
    private int mode = 0;

    public void setStorage(int storage) {
        this.storage = storage;
    }

    private int storage = 0;

    public ForgeBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FORGE, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> ForgeBlockEntity.this.progress;
                    case 1 -> ForgeBlockEntity.this.fuelTime;
                    case 2 -> ForgeBlockEntity.this.maxFuelTime;
                    case 3 -> ForgeBlockEntity.this.mode;
                    case 4 -> ForgeBlockEntity.this.storage;
                    case 5 -> ForgeBlockEntity.this.getPos().getX();
                    case 6 -> ForgeBlockEntity.this.getPos().getZ();
                    case 7 -> ForgeBlockEntity.this.getPos().getY();
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> ForgeBlockEntity.this.progress = value;
                    case 1 -> ForgeBlockEntity.this.fuelTime = value;
                    case 2 -> ForgeBlockEntity.this.maxFuelTime = value;
                    case 3 -> ForgeBlockEntity.this.mode = value;
                    case 4 -> ForgeBlockEntity.this.storage = value;
                }
            }

            @Override
            public int size() {
                return 8;
            }
        };
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("screen." + MiddleEarth.MOD_ID + "." + ID);
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new ForgeScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public int hasBellows(World world, BlockPos pos, BlockState state){
        Direction direction = state.get(Properties.HORIZONTAL_FACING);
        BlockPos pos1 = pos.offset(direction.rotateYClockwise());
        BlockPos pos2 = pos.offset(direction.rotateYClockwise().getOpposite());

        if(world.getBlockState(pos1).isOf(ModDecorativeBlocks.BELLOWS) && world.getBlockState(pos2).isOf(ModDecorativeBlocks.BELLOWS)){
            return 1;
        } else {
            return 0;
        }
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
        nbt.putInt(ID + ".progress", progress);
        nbt.putInt(ID + ".boost-time", boostTime);
        nbt.putInt(ID + ".fuel-time", fuelTime);
        nbt.putInt(ID + ".max-fuel-time", maxFuelTime);
        nbt.putInt(ID + ".mode", mode);
        nbt.putInt(ID + ".storage", storage);
    }

    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, inventory, registryLookup);
        progress = nbt.getInt(ID + ".progress");
        boostTime = nbt.getInt(ID + ".boost-time");
        fuelTime = nbt.getInt(ID + ".fuel-time");
        maxFuelTime = nbt.getInt(ID + ".max-fuel-time");
        mode = nbt.getInt(ID + ".mode");
        storage = nbt.getInt(ID + ".storage");
    }

    public ItemStack getRenderStack() {
        return this.getStack(OUTPUT_SLOT);
    }
    public void setInventory(DefaultedList<ItemStack> inventory) {
        for (int i = 0; i < inventory.size(); i++) {
            this.inventory.set(i, inventory.get(i));
        }
    }


    @Override
    public void markDirty() {
        /* if(world != null && !world.isClient()) {
            PacketByteBuf data = PacketByteBufs.create();
            data.writeInt(inventory.size());
            for(int i = 0; i < inventory.size(); i++) {
                data.writeItemStack(inventory.get(i)); // TODO writeItemStack() no longer exists...
            }
            data.writeBlockPos(getPos());

            for (ServerPlayerEntity player : PlayerLookup.tracking((ServerWorld) world, getPos())) {
                ServerPlayNetworking.send(player, ModNetworks.ITEM_SYNC, data);
            }
        } */
        super.markDirty();
    }

    protected boolean isFuel(Item item) {
        for (Item item1: fuelTimeMap.keySet()) {
            if(item1.equals(item)) return true;
        }
        return false;
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        int[] slots = new int[inventory.size()];
        for (int i = 0; i < slots.length; i++) {
            slots[i] = i;
        }
        return slots;
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        if (slot == FUEL_SLOT) {
            return isFuel(stack.getItem());
        }
        return true;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return slot != OUTPUT_SLOT;
    }

    @Override
    public int size() {
        return inventory.size();
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < size(); i++) {
            ItemStack itemStack = getStack(i);
            if (!itemStack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getStack(int slot) {
        return inventory.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        return Inventories.splitStack(this.inventory, slot, amount);
    }

    @Override
    public ItemStack removeStack(int slot) {
        return Inventories.removeStack(inventory, slot);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        inventory.set(slot, stack);
        if (stack.getCount() > getMaxCountPerStack()) {
            stack.setCount(getMaxCountPerStack());
        }
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return Inventory.canPlayerUse(this, player);
    }

    @Override
    public void clear() {
        inventory.clear();
    }

    public void bellowsBoost() {
        boostTime = MAX_BOOST_TIME;
    }

    public static void lowerAmount(ForgeOutputPacket packet, ServerPlayNetworking.Context context){
        Optional<ForgeBlockEntity> forgeBlockEntity = context.player().getWorld().getBlockEntity(new BlockPos(packet.entityX(), packet.entityZ(), packet.entityY()), ModBlockEntities.FORGE);
        forgeBlockEntity.ifPresent(entity -> entity.storage = entity.storage - packet.amount());
    }

    public static void tick(World world, BlockPos blockPos, BlockState blockState, ForgeBlockEntity entity) {
        if(world.isClient()) return;

        entity.fuelTime = Math.max(0, entity.fuelTime - 1);
        entity.boostTime = Math.max(0, entity.boostTime - 1);
        boolean progress = false;
        entity.mode = entity.hasBellows(world, blockPos, blockState);
        if(hasRecipe(entity)) {
            if(entity.hasFuel(entity)) {
                int progressValue = 1;
                if(entity.boostTime > 0) {
                    progressValue = 8;
                }
                entity.progress += progressValue;
                progress = true;
                markDirty(world, blockPos, blockState); // Reloads the origin in this chunk, for sync & saving.
                if(entity.progress >= MAX_PROGRESS) {
                    craftItem(entity);
                    entity.progress = 0;
                }
            }
        }
        if (!progress){
            entity.progress = Math.max(entity.progress - 2, 0);
            markDirty(world, blockPos, blockState);
        }
        boolean isCooking = entity.fuelTime > 0;
        blockState = blockState.with(AbstractFurnaceBlock.LIT, isCooking);
        world.setBlockState(blockPos, blockState, Block.NOTIFY_ALL);
    }

    private static void craftItem(ForgeBlockEntity entity) {
        SimpleInventory inventory1 = new SimpleInventory(entity.size());
        List<ItemStack> inputs = new ArrayList<>();
        for (int i = 0; i < entity.size(); i++) {
            inventory1.setStack(i, entity.getStack(i));
            if(i != FUEL_SLOT && i != OUTPUT_SLOT) inputs.add(entity.getStack(i));
        }

        Optional<RecipeEntry<AlloyingRecipe>> match = entity.getWorld().getRecipeManager()
                .getFirstMatch(AlloyingRecipe.Type.INSTANCE, new MultipleStackRecipeInput(inputs), entity.getWorld());
        if(match.isEmpty()) throw new RuntimeException("Somehow... you crafted an item without recipe?!");

        if(hasRecipe(entity)) {
            for (int i = 1; i <= 4; i++) {
                entity.removeStack(i, 1);
            }
            entity.storage = entity.storage + match.get().value().output.getCount() * 144;
            entity.markDirty();
        }
    }

    private static boolean hasRecipe(ForgeBlockEntity entity) {
        SimpleInventory inventory1 = new SimpleInventory(entity.size());
        List<ItemStack> inputs = new ArrayList<>();
        for (int i = 0; i < entity.size(); i++) {
            inventory1.setStack(i, entity.getStack(i));
            if(i != FUEL_SLOT && i != OUTPUT_SLOT) inputs.add(entity.getStack(i));
        }
        if(inputs.isEmpty()) return false;

        MultipleStackRecipeInput input = new MultipleStackRecipeInput(inputs);
        Optional<RecipeEntry<AlloyingRecipe>> match = entity.getWorld().getRecipeManager()
                .getFirstMatch(AlloyingRecipe.Type.INSTANCE, input, entity.getWorld());

        if(match.isEmpty()) return false;

        return canInsertAmountIntoOutput(inventory1, match.get().value().output.getCount())
                && canInsertRecipeIntoOutput(inventory1, match.get().value().output.getItem())
                && canInsertLiquid(entity.storage, match);
    }

    private boolean hasFuel(ForgeBlockEntity entity) {

        SimpleInventory inventory1 = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory1.setStack(i, entity.getStack(i));
        }

        Item fuelItem = inventory1.getStack(FUEL_SLOT).getItem();
        if(this.fuelTime > 0) return true;
        else {
            if(isFuel(fuelItem)) {
                getFuel(entity, fuelItem);
                return true;
            } else return false;
        }
    }

    private void getFuel(ForgeBlockEntity entity, Item fuelItem) {
        fuelTime = Math.round((float) fuelTimeMap.get(fuelItem) / 16);
        maxFuelTime = fuelTime;
        if(fuelItem == Items.LAVA_BUCKET) {
            entity.removeStack(FUEL_SLOT);
            entity.setStack(FUEL_SLOT, Items.BUCKET.getDefaultStack());
        }
        else entity.getStack(FUEL_SLOT).decrement(1);
    }

    private static boolean canInsertAmountIntoOutput(SimpleInventory inventory1, int count) {
        int maxCount = inventory1.getStack(OUTPUT_SLOT).getMaxCount();
        if(inventory1.getStack(OUTPUT_SLOT) == ItemStack.EMPTY) maxCount = 64;
        int newCount = inventory1.getStack(OUTPUT_SLOT).getCount() + (count- 1);
        return maxCount > newCount;
    }
    private static boolean canInsertRecipeIntoOutput(SimpleInventory inventory1, Item item) {
        boolean sameItem = inventory1.getStack(OUTPUT_SLOT).getItem() == item;
        boolean isEmpty = inventory1.getStack(OUTPUT_SLOT).isEmpty();
        return sameItem || isEmpty;
    }

    private static boolean canInsertLiquid(int storage, Optional<RecipeEntry<AlloyingRecipe>> match ) {
        return (storage + match.get().value().output.getCount() * 144) <= MAX_STORAGE;
    }
}
