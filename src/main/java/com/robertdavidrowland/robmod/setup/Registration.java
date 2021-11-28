package com.robertdavidrowland.robmod.setup;

import com.robertdavidrowland.robmod.items.AmethystAxeItem;
import com.robertdavidrowland.robmod.items.AmethystPickaxeItem;
import com.robertdavidrowland.robmod.items.ChargedPickaxeItem;
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
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    public static void init() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(bus);
        BLOCKS.register(bus);
    }

    public static final RegistryObject<AmethystAxeItem> AMETHYST_AXE = ITEMS.register("amethyst_axe", () -> new AmethystAxeItem(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<AmethystPickaxeItem> AMETHYST_PICKAXE = ITEMS.register("amethyst_pickaxe", () -> new AmethystPickaxeItem(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<ChargedPickaxeItem> CHARGED_PICKAXE = ITEMS.register("charged_pickaxe", () -> new ChargedPickaxeItem(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));

}
