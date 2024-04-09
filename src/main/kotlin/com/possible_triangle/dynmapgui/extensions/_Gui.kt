package com.possible_triangle.dynmapgui.extensions

import com.possible_triangle.dynmapgui.Constants
import eu.pb4.sgui.api.elements.GuiElementBuilder
import eu.pb4.sgui.api.gui.SimpleGui
import net.minecraft.network.chat.Component
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.ItemLike

fun SimpleGui.addControl(slot: Int, item: ItemLike, name: String, callback: () -> Unit) {
    setSlot(slot, GuiElementBuilder.from(ItemStack(item))
        .setCustomModelData(Constants.CONTROL_MODEL_DATA)
        .setName(Component.literal(name))
        .setCallback { _, _, _ ->  callback() }
    )
}