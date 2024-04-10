package com.possible_triangle.dynmapgui.gui

import com.possible_triangle.dynmapgui.CustomIcon
import eu.pb4.sgui.api.gui.AnvilInputGui
import net.minecraft.server.level.ServerPlayer

abstract class RenameGui(
    player: ServerPlayer,
    private val initialValue: String = "",
) :
    AnvilInputGui(player, false) {

    abstract fun submit(value: String)

    override fun onOpen() {
        setDefaultInputValue(initialValue)

        setSlot(2,  CustomIcon.CONFIRM.guiElement {
            submit(input)
            close()
        })
    }

}