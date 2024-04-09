package com.possible_triangle.dynmapgui.gui

import com.possible_triangle.dynmapgui.extensions.addControl
import eu.pb4.sgui.api.gui.SimpleGui
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.inventory.MenuType
import net.minecraft.world.item.Items

abstract class ConfirmGui(
    player: ServerPlayer,
    title: String,
) :
    SimpleGui(MenuType.GENERIC_9x3, player, false) {

    abstract fun submit()

    open fun cancel() {}

    init {
        this.title = Component.literal(title)

        addControl(width + 2, Items.GREEN_WOOL, "Confirm") {
            submit()
            close()
        }

        addControl(width * 2 - 3, Items.RED_WOOL, "Cancel") {
            cancel()
            close()
        }
    }

}