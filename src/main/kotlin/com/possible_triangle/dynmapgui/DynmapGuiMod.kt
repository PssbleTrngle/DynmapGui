package com.possible_triangle.dynmapgui

import com.possible_triangle.dynmapgui.command.MarkerCommand
import com.possible_triangle.dynmapgui.datagen.IconGen
import io.github.fabricators_of_create.porting_lib.data.ExistingFileHelper
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent
import org.dynmap.DynmapCommonAPI
import org.dynmap.DynmapCommonAPIListener
import org.dynmap.MapManager

object DynmapGuiMod : ModInitializer, DataGeneratorEntrypoint {

    lateinit var DYNMAP_API: DynmapCommonAPI
        private set

    val MAP_MANAGER: MapManager get() = MapManager.mapman

    fun translation(key: String, vararg args: Any): MutableComponent = Component.translatable("dynmapgui.$key", *args)

    private object Listener : DynmapCommonAPIListener() {
        override fun apiEnabled(api: DynmapCommonAPI) {
            DYNMAP_API = api
        }

    }

    override fun onInitialize() {
        DynmapCommonAPIListener.register(Listener)

        CommandRegistrationCallback.EVENT.register { dispatcher, _, _ ->
            MarkerCommand.register(dispatcher)
        }
    }

    override fun onInitializeDataGenerator(generator: FabricDataGenerator) {
        val pack = generator.createPack()

        val existingFiles = ExistingFileHelper.withResourcesFromArg()

        pack.addProvider { output: FabricDataOutput -> IconGen(output, existingFiles) }
    }
}