package nl.avii.smartobserver.core.init;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import nl.avii.smartobserver.SmartObserverMod;
import nl.avii.smartobserver.common.blocks.SmartObserverBlock;

public class BlockInit {

    private static boolean never(BlockState p_235436_0_, IBlockReader p_235436_1_, BlockPos p_235436_2_) {
        return false;
    }

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            SmartObserverMod.MOD_ID);

    public static final RegistryObject<Block> SMART_OBSERVER_BLOCK = BLOCKS.register("smart_observer", () -> new SmartObserverBlock(AbstractBlock.Properties.of(Material.STONE).strength(3.0F).requiresCorrectToolForDrops().isRedstoneConductor(BlockInit::never)));
}
