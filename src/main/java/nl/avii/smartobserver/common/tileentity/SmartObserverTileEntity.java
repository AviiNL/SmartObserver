package nl.avii.smartobserver.common.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.GameData;
import nl.avii.smartobserver.core.init.TileEntityTypeInit;

public class SmartObserverTileEntity extends TileEntity {

    public String obserableBlock = "minecraft:air";

    public SmartObserverTileEntity() {
        this(TileEntityTypeInit.SMART_OBSERVER.get());
    }

    public SmartObserverTileEntity(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {

        nbt.putString("observe_block", obserableBlock);

        return super.save(nbt);
    }

    @Override
    public void load(BlockState p_230337_1_, CompoundNBT nbt) {
        this.obserableBlock = nbt.getString("observe_block");
        super.load(p_230337_1_, nbt);
    }
}
