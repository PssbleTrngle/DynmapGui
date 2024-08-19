package com.possible_triangle.dynmapgui.datagen

import io.github.fabricators_of_create.porting_lib.data.ExistingFileHelper
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput

object DynmapGuiDatagen : DataGeneratorEntrypoint {

    override fun onInitializeDataGenerator(generator: FabricDataGenerator) {
        val pack = generator.createPack()

        val existingFiles = ExistingFileHelper.withResourcesFromArg()

        pack.addProvider { output: FabricDataOutput -> IconGen(output, existingFiles) }
    }

}