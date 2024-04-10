package com.possible_triangle.dynmapgui.extensions

import eu.pb4.sgui.api.elements.GuiElementBuilder
import net.minecraft.network.chat.Component
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.ItemLike

fun createControl(item: ItemLike, name: String, callback: () -> Unit): GuiElementBuilder {
    return GuiElementBuilder.from(ItemStack(item))
        .setName(Component.literal(name))
        .setCallback { _, _, _ -> callback() }
}