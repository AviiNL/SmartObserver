package nl.avii.smartobserver.core.init;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import nl.avii.smartobserver.SmartObserverMod;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SmartObserverMod.MOD_ID);

    // Block Items
    public static final RegistryObject<BlockItem> SMART_OBSERVER_BLOCK = ITEMS.register(
            "smart_observer", () ->
                    new BlockItem(
                            BlockInit.SMART_OBSERVER_BLOCK.get(),
                            (new Item.Properties()).tab(ItemGroup.TAB_REDSTONE)
                    )
    );

}
