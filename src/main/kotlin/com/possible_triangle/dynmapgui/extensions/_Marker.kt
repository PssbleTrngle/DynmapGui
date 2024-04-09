package com.possible_triangle.dynmapgui.extensions

import eu.pb4.sgui.api.elements.GuiElementBuilder
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import org.dynmap.markers.Marker
import org.dynmap.markers.MarkerIcon

fun MarkerIcon.displayStack(): ItemStack {
    return ItemStack(
        BuiltInRegistries.ITEM.get(ResourceLocation(markerIconID))
            .takeUnless { it == Items.AIR }
            ?: Items.WHITE_BANNER
    )
}

fun Marker.guiElement(): GuiElementBuilder {
    return GuiElementBuilder.from(markerIcon.displayStack())
        .setName(Component.literal(label))
        .setLore(
            listOf(
                Component.literal("[${x.toInt()} / ${y.toInt()} / ${z.toInt()}]"),
                Component.translatable(markerSet.markerSetLabel),
            )
        )
        .hideFlags()
}