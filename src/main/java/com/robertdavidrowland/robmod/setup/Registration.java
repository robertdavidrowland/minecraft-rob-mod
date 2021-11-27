package com.robertdavidrowland.robmod.setup;

import com.robertdavidrowland.robmod.items.FancyPickaxeItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.robertdavidrowland.robmod.RobMod.MODID;

public class Registration {

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<FancyPickaxeItem> FANCYPICKAXE = ITEMS.register("fancypickaxe", () -> new FancyPickaxeItem(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));

}
