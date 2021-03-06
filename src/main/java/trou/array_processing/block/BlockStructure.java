package trou.array_processing.block;

import it.zerono.mods.zerocore.api.multiblock.IMultiblockPart;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import trou.array_processing.ArrayProcessing;
import trou.array_processing.multiblock.ProcessingArrayController;

public abstract class BlockStructure extends Block {
    public BlockStructure(String identity) {
        super(Material.ROCK);
        this.setRegistryName("array_" + identity);
        this.setTranslationKey(ArrayProcessing.MODID + ".array_" + identity);
        this.setCreativeTab(ArrayProcessing.TAB);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote && hand == EnumHand.MAIN_HAND && playerIn.getHeldItemMainhand().isEmpty()) {
            TileEntity te = worldIn.getTileEntity(pos);
            if (te instanceof IMultiblockPart) {
                ProcessingArrayController controller = (ProcessingArrayController) ((IMultiblockPart) te).getMultiblockController();
                if (controller != null && controller.getLastError() != null) {
                    playerIn.sendMessage(controller.getLastError().getChatMessage());
                } else {
                    return ActiveBlock(worldIn, pos, state, playerIn);
                }
            }
        }
        return false;
    }

    public abstract boolean ActiveBlock(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn);
}
