package com.robertdavidrowland.robmod.setup;

import com.robertdavidrowland.robmod.RobMod;
import com.robertdavidrowland.robmod.items.AmthystPickaxeItem;
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
        AmthystPickaxeItem item = Registration.AMETHYST_PICKAXE.get();
        ItemProperties.register(item, DISTANCE_PROPERTY,
                (stack, level, entity, damage) -> item.getDistance(stack));
    }
}
