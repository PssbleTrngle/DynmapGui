package com.possible_triangle.dynmapgui.gui

import com.possible_triangle.dynmapgui.CustomIcon
import eu.pb4.sgui.api.gui.SimpleGui
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.inventory.MenuType

abstract class ConfirmGui(
    player: ServerPlayer
) :
    SimpleGui(MenuType.GENERIC_9x3, player, false) {

    abstract fun submit()

    open fun cancel() {}

    override fun onOpen() {
        setSlot(width + 2, CustomIcon.CONFIRM.guiElement {
            submit()
            close()
        })

        setSlot(width * 2 - 3, CustomIcon.CANCEL.guiElement {
            cancel()
            close()
        })
    }

}