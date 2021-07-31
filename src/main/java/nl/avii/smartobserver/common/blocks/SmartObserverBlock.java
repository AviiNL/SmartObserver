package nl.avii.smartobserver.common.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.ObserverBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import nl.avii.smartobserver.common.tileentity.SmartObserverTileEntity;
import nl.avii.smartobserver.core.init.TileEntityTypeInit;

import javax.annotation.Nullable;
import java.util.Random;

public class SmartObserverBlock extends ObserverBlock {

    public SmartObserverBlock(Properties p_i48358_1_) {
        super(p_i48358_1_);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TileEntityTypeInit.SMART_OBSERVER.get().create();
    }

    @Override
    public ActionResultType use(BlockState blockState, World p_225533_2_, BlockPos p_225533_3_, PlayerEntity p_225533_4_, Hand p_225533_5_, BlockRayTraceResult p_225533_6_) {
        if (p_225533_2_.isClientSide) {
            return ActionResultType.SUCCESS;
        } else {
            SmartObserverTileEntity tileEntity = (SmartObserverTileEntity) p_225533_2_.getBlockEntity(p_225533_3_);
            if(tileEntity == null) return ActionResultType.CONSUME;
            tileEntity.obserableBlock = p_225533_4_.getMainHandItem().getItem().getRegistryName().toString();

            p_225533_4_.sendMessage(new TranslationTextComponent("smartobserver.observed", tileEntity.obserableBlock), p_225533_4_.getUUID());

            return ActionResultType.CONSUME;
        }
    }

    @Override
    public void tick(BlockState p_225534_1_, ServerWorld p_225534_2_, BlockPos p_225534_3_, Random p_225534_4_) {
        if (p_225534_1_.getValue(POWERED)) {
            p_225534_2_.setBlock(p_225534_3_, p_225534_1_.setValue(POWERED, Boolean.valueOf(false)), 2);
        } else {
            p_225534_2_.setBlock(p_225534_3_, p_225534_1_.setValue(POWERED, Boolean.valueOf(true)), 2);
            p_225534_2_.getBlockTicks().scheduleTick(p_225534_3_, this, 2);
        }

        this.updateNeighborsInFront(p_225534_2_, p_225534_3_, p_225534_1_);
    }

    @Override
    protected void updateNeighborsInFront(World p_190961_1_, BlockPos p_190961_2_, BlockState p_190961_3_) {
        Direction direction = p_190961_3_.getValue(FACING);
        BlockPos  blockpos  = p_190961_2_.relative(direction.getOpposite());

        BlockPos   changedBlockPos   = p_190961_2_.relative(direction);
        BlockState changedBlockState = p_190961_1_.getBlockState(changedBlockPos);

        SmartObserverTileEntity tileEntity = (SmartObserverTileEntity) p_190961_1_.getBlockEntity(p_190961_2_);
        if(tileEntity == null) return;

        if(changedBlockState.getBlock().getRegistryName().toString().equals(tileEntity.obserableBlock) ||
                changedBlockState.getBlock().getRegistryName().toString().equals("minecraft:air")) {
            p_190961_1_.neighborChanged(blockpos, this, p_190961_2_);
            p_190961_1_.updateNeighborsAtExceptFromFacing(blockpos, this, direction);
            p_190961_1_.updateNeighborsAt(blockpos, this);
        }
    }
}
