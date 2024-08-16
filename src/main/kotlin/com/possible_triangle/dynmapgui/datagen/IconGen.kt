package com.possible_triangle.dynmapgui.datagen

import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.possible_triangle.dynmapgui.CustomIcon
import io.github.fabricators_of_create.porting_lib.data.ExistingFileHelper
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.ItemModelGenerators
import net.minecraft.data.models.model.ModelTemplates
import net.minecraft.data.models.model.TextureMapping
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.PackType
import java.io.FileNotFoundException

class IconGen(output: FabricDataOutput, private val existingFiles: ExistingFileHelper) : FabricModelProvider(output) {

    private val GSON = GsonBuilder().create()

    override fun generateBlockStateModels(generator: BlockModelGenerators) {
        // None
    }

    override fun generateItemModels(generator: ItemModelGenerators) {
        CustomIcon.ENTRIES.groupBy { it.item }.forEach { (item, icons) ->
            val id = BuiltInRegistries.ITEM.getKey(item.asItem())
            val existing = existingFiles.getResource(id, PackType.CLIENT_RESOURCES, ".json", "models/item")
            val json = GSON.fromJson(existing.openAsReader(), JsonObject::class.java)

            val models = icons.associateWith {
                ResourceLocation.fromNamespaceAndPath("dynmap", "item/icon/${it.name}")
            }

            models.forEach { (_, key) ->
                if(!existingFiles.exists(key, PackType.CLIENT_RESOURCES, ".png", "textures")) {
                    throw FileNotFoundException("missing texture for icon at '$key'")
                }

                ModelTemplates.FLAT_ITEM.create(
                    key,
                    TextureMapping.layer0(key),
                    generator.output
                )
            }

            json.add("overrides", JsonArray(icons.size).apply {
                models.forEach { (icon, model) ->
                    add(JsonObject().apply {
                        add("predicate", JsonObject().apply {
                            addProperty("custom_model_data", icon.data)
                        })

                        addProperty("model", model.toString())
                    })
                }
            })

            generator.output.accept(id.withPrefix("item/")) { json }
        }
    }

}