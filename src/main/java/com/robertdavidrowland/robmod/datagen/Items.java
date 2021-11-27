package com.robertdavidrowland.robmod.datagen;

import com.robertdavidrowland.robmod.RobMod;
import com.robertdavidrowland.robmod.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import static com.robertdavidrowland.robmod.setup.ClientSetup.DISTANCE_PROPERTY;

public class Items extends ItemModelProvider {
    public Items(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, RobMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
//        singleTexture(
//                Registration.FANCYPICKAXE.get().getRegistryName().getPath(),
//                new ResourceLocation("item/handheld"),
//                "layer0",
//                new ResourceLocation(RobMod.MODID, "item/fancypickaxe")
//        );

        getBuilder(Registration.FANCYPICKAXE.get().getRegistryName().getPath())
                .parent(getExistingFile(mcLoc("item/handheld")))
                .texture("layer0", "item/fancypickaxe0")
                .override().predicate(DISTANCE_PROPERTY, 0).model(createFancyPickaxeModel(0)).end()
                .override().predicate(DISTANCE_PROPERTY, 1).model(createFancyPickaxeModel(1)).end()
                .override().predicate(DISTANCE_PROPERTY, 2).model(createFancyPickaxeModel(2)).end()
                .override().predicate(DISTANCE_PROPERTY, 3).model(createFancyPickaxeModel(3)).end();
    }

    private ItemModelBuilder createFancyPickaxeModel(int suffix) {
        return getBuilder("fancypickaxe" + suffix).parent(getExistingFile(mcLoc("item/handheld")))
                .texture("layer0", "item/fancypickaxe" + suffix);
    }
}
