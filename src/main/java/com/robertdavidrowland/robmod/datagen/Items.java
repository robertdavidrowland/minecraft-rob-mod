package com.robertdavidrowland.robmod.datagen;

import com.robertdavidrowland.robmod.RobMod;
import com.robertdavidrowland.robmod.setup.Registration;
import net.minecraft.data.DataGenerator;
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
        getBuilder(Registration.AMETHYST_PICKAXE.get().getRegistryName().getPath())
                .parent(getExistingFile(mcLoc("item/handheld")))
                .texture("layer0", "item/amethyst_pickaxe0")
                .override().predicate(DISTANCE_PROPERTY, 0).model(createAmethystPickaxeModel(0)).end()
                .override().predicate(DISTANCE_PROPERTY, 1).model(createAmethystPickaxeModel(1)).end()
                .override().predicate(DISTANCE_PROPERTY, 2).model(createAmethystPickaxeModel(2)).end()
                .override().predicate(DISTANCE_PROPERTY, 3).model(createAmethystPickaxeModel(3)).end();
    }

    private ItemModelBuilder createAmethystPickaxeModel(int suffix) {
        return getBuilder("amethyst_pickaxe" + suffix).parent(getExistingFile(mcLoc("item/handheld")))
                .texture("layer0", "item/amethyst_pickaxe" + suffix);
    }
}
