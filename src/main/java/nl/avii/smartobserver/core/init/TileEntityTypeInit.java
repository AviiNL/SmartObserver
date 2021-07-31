package nl.avii.smartobserver.core.init;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import nl.avii.smartobserver.SmartObserverMod;
import nl.avii.smartobserver.common.blocks.SmartObserverBlock;
import nl.avii.smartobserver.common.tileentity.SmartObserverTileEntity;

import java.rmi.registry.Registry;

public class TileEntityTypeInit {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES,
            SmartObserverMod.MOD_ID);

    public static final RegistryObject<TileEntityType<SmartObserverTileEntity>> SMART_OBSERVER = TILE_ENTITY_TYPES.register("smart_observer", () -> TileEntityType.Builder.of(SmartObserverTileEntity::new, BlockInit.SMART_OBSERVER_BLOCK.get()).build(null));

}
