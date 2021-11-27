package com.robertdavidrowland.robmod;

import com.robertdavidrowland.robmod.setup.ClientSetup;
import com.robertdavidrowland.robmod.setup.Registration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(RobMod.MODID)
public class RobMod {

    public static final Logger LOGGER = LogManager.getLogger();

    public static final String MODID = "robmod";

    public RobMod() {
        Registration.init();

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);
        bus.addListener(ClientSetup::setup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
    }
}
