package com.robertdavidrowland.robmod.setup;

import com.robertdavidrowland.robmod.RobMod;
import com.robertdavidrowland.robmod.items.AmethystAxeItem;
import com.robertdavidrowland.robmod.items.AmethystPickaxeItem;
import com.robertdavidrowland.robmod.items.ChargedPickaxeItem;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetup {

    public static final ResourceLocation DISTANCE_PROPERTY = new ResourceLocation(RobMod.MODID, "distance");

    public static void setup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            initTestItemOverrides();
        });
    }

    public static void initTestItemOverrides() {
        AmethystAxeItem amethystAxeItem = Registration.AMETHYST_AXE.get();
        ItemProperties.register(amethystAxeItem, DISTANCE_PROPERTY,
                (stack, level, entity, damage) -> amethystAxeItem.getDistance(stack));

        AmethystPickaxeItem amethystPickaxeItem = Registration.AMETHYST_PICKAXE.get();
        ItemProperties.register(amethystPickaxeItem, DISTANCE_PROPERTY,
                (stack, level, entity, damage) -> amethystPickaxeItem.getDistance(stack));

        ChargedPickaxeItem chargedPickaxeItem = Registration.CHARGED_PICKAXE.get();
        ItemProperties.register(chargedPickaxeItem, DISTANCE_PROPERTY,
                (stack, level, entity, damage) -> chargedPickaxeItem.getDistance(stack));
    }
}
