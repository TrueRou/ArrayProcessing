package trou.array_processing.multiblock;

import it.zerono.mods.zerocore.api.multiblock.IMultiblockPart;
import it.zerono.mods.zerocore.api.multiblock.MultiblockControllerBase;
import it.zerono.mods.zerocore.api.multiblock.rectangular.RectangularMultiblockControllerBase;
import it.zerono.mods.zerocore.api.multiblock.validation.IMultiblockValidator;
import it.zerono.mods.zerocore.api.multiblock.validation.ValidationError;
import it.zerono.mods.zerocore.lib.block.ModTileEntity;
import net.minecraft.block.BlockAir;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import trou.array_processing.tile.TileArrayEnergyHatch;
import trou.array_processing.tile.TileArrayItemHatch;

public class ProcessingArrayController extends RectangularMultiblockControllerBase {
    private TileArrayItemHatch inputHatch;
    private TileArrayItemHatch outputHatch;
    private TileArrayEnergyHatch energyHatch;
    public ProcessingArrayController(World world) {
        super(world);
    }

    @Override
    public void onAttachedPartWithMultiblockData(IMultiblockPart var1, NBTTagCompound var2) {

    }

    @Override
    protected boolean isMachineWhole(IMultiblockValidator validatorCallback) {
        refreshHatches();
        if (energyHatch != null && inputHatch != null && outputHatch != null) {
            return true;
        }
        validatorCallback.setLastError(new ValidationError("array_processing.multiblock.validation.lack_hatch"));
        return false;
    }

    private void refreshHatches() {
        energyHatch = null;
        inputHatch = null;
        outputHatch = null;
        for (IMultiblockPart part : connectedParts) {
            if (part instanceof TileArrayEnergyHatch) energyHatch = (TileArrayEnergyHatch) part;
            if (part instanceof TileArrayItemHatch) {
                TileArrayItemHatch itemHatch = (TileArrayItemHatch) part;
                if (itemHatch.output) outputHatch = itemHatch; else inputHatch = itemHatch;
            }
        }
    }

    @Override
    protected void onBlockAdded(IMultiblockPart var1) {

    }

    @Override
    protected void onBlockRemoved(IMultiblockPart var1) {

    }

    @Override
    protected void onMachineAssembled() {
        refreshHatches();
    }

    @Override
    protected void onMachineRestored() {
        refreshHatches();
    }

    @Override
    protected void onMachinePaused() {

    }

    @Override
    protected void onMachineDisassembled() {

    }

    @Override
    protected int getMinimumNumberOfBlocksForAssembledMachine() {
        return 26;
    }

    @Override
    protected int getMaximumXSize() {
        return 8;
    }

    @Override
    protected int getMaximumZSize() {
        return 8;
    }

    @Override
    protected int getMaximumYSize() {
        return 8;
    }

    @Override
    protected void onAssimilate(MultiblockControllerBase var1) {

    }

    @Override
    protected void onAssimilated(MultiblockControllerBase var1) {

    }

    @Override
    protected boolean updateServer() {
        //Do Actual Works
        return false;
    }

    @Override
    protected void updateClient() {

    }

    @Override
    protected boolean isBlockGoodForFrame(World world, int x, int y, int z, IMultiblockValidator validator) {
        validator.setLastError(new ValidationError("array_processing.multiblock.validation.invalid_block_notwall", x, y, z));
        return false;
    }

    @Override
    protected boolean isBlockGoodForTop(World world, int x, int y, int z, IMultiblockValidator validator) {
        validator.setLastError(new ValidationError("array_processing.multiblock.validation.invalid_block", x, y, z));
        return false;
    }

    @Override
    protected boolean isBlockGoodForBottom(World world, int x, int y, int z, IMultiblockValidator validator) {
        validator.setLastError(new ValidationError("array_processing.multiblock.validation.invalid_block", x, y, z));
        return false;
    }

    @Override
    protected boolean isBlockGoodForSides(World world, int x, int y, int z, IMultiblockValidator validator) {
        validator.setLastError(new ValidationError("array_processing.multiblock.validation.invalid_block", x, y, z));
        return false;
    }

    @Override
    protected boolean isBlockGoodForInterior(World world, int x, int y, int z, IMultiblockValidator validator) {
        if (world.getBlockState(new BlockPos(x, y, z)).getBlock() instanceof BlockAir) return true;
        validator.setLastError(new ValidationError("array_processing.multiblock.validation.invalid_block_inner", x, y, z));
        return false;
    }

    @Override
    protected void syncDataFrom(NBTTagCompound var1, ModTileEntity.SyncReason var2) {

    }

    @Override
    protected void syncDataTo(NBTTagCompound var1, ModTileEntity.SyncReason var2) {

    }
}
