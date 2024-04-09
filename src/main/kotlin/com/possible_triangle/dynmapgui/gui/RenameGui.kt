package com.possible_triangle.dynmapgui.gui

import com.possible_triangle.dynmapgui.extensions.addControl
import eu.pb4.sgui.api.gui.AnvilInputGui
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.item.Items

abstract class RenameGui(
    player: ServerPlayer,
    initialValue: String,
) :
    AnvilInputGui(player, false) {

    abstract fun submit(value: String)

    init {
        title = Component.literal("Renaming $initialValue")
        setDefaultInputValue(initialValue)

        addControl(2, Items.GREEN_WOOL, "Confirm") {
            submit(input)
            close()
        }
    }

}