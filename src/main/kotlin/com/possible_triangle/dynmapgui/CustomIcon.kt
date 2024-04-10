package com.possible_triangle.dynmapgui

import eu.pb4.sgui.api.elements.GuiElementBuilder
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.ItemLike

data class CustomIcon(val item: ItemLike, val data: Int, val name: String) {

    companion object {
        private val _ENTRIES = arrayListOf<CustomIcon>()

        val ENTRIES get() = _ENTRIES.toList()

        private const val BASE_DATA = 1203481

        private fun createIcon(item: ItemLike, defaultName: String, i: Int = 0): CustomIcon {
            return CustomIcon(item, BASE_DATA + i, defaultName).also {
                _ENTRIES.add(it)
            }
        }

        val PREVIOUS = createIcon(Items.ARROW, "previous", 0)
        val NEXT = createIcon(Items.ARROW, "next", 1)
        val BACK = createIcon(Items.ARROW, "back", 2)

        val CONFIRM = createIcon(Items.GREEN_WOOL, "confirm")
        val CANCEL = createIcon(Items.RED_WOOL, "cancel")

        val DELETE = createIcon(Items.RED_WOOL, "delete")
        val CHANGE_ICON = createIcon(Items.GLOBE_BANNER_PATTERN, "change_icon")
        val RENAME = createIcon(Items.WRITABLE_BOOK, "rename")
        val CREATE = createIcon(Items.WRITABLE_BOOK, "create", 1)
        val MOVE = createIcon(Items.PISTON, "move_here")
        val TELEPORT = createIcon(Items.ENDER_PEARL, "teleport")

        val CHANGE_DIMENSION = createIcon(Items.END_PORTAL_FRAME, "change_dimension")

    }

    fun guiElement(name: String = this.name, callback: () -> Unit) = GuiElementBuilder.from(ItemStack(item))
        .setCustomModelData(data)
        .setName(DynmapGuiMod.translation("control.$name"))
        .setCallback { _, _, _ -> callback() }

}