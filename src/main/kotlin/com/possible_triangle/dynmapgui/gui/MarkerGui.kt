package com.possible_triangle.dynmapgui.gui

import com.possible_triangle.dynmapgui.extensions.addControl
import com.possible_triangle.dynmapgui.extensions.guiElement
import eu.pb4.sgui.api.gui.SimpleGui
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.inventory.MenuType
import net.minecraft.world.item.Items
import org.dynmap.fabric_1_20.FabricWorld
import org.dynmap.markers.Marker

class MarkerGui(
    player: ServerPlayer,
    private val marker: Marker,
) :
    SimpleGui(MenuType.GENERIC_9x3, player, false) {

    companion object {

        fun open(player: ServerPlayer, marker: Marker) {
            MarkerGui(player, marker).apply {
                update()
                open()
            }
        }
    }

    fun update() {
        title = Component.literal(marker.label)

        setSlot(4, marker.guiElement())

        addControl(width + 2, Items.WRITABLE_BOOK, "Rename") {
            Rename().open()
        }

        addControl(width + 3, Items.SPYGLASS, "Change Icon") {
            close()
        }

        addControl(width + 5, Items.ENDER_PEARL, "Move Here") {
            val level = FabricWorld.getWorldName(null, player.level())
            marker.setLocation(level, player.x, player.y, player.z)
            close()
        }

        addControl(width * 2 - 2, Items.LAVA_BUCKET, "Delete") {
            Delete().open()
        }
    }

    private inner class Rename : RenameGui(player, marker.label) {
        override fun submit(value: String) {
            marker.label = value
            this@MarkerGui.open()
        }
    }

    private inner class Delete : ConfirmGui(player, "Delete ${marker.label}?") {
        override fun submit() {
            this@MarkerGui.open()
        }

        override fun cancel() {
            this@MarkerGui.open()
        }
    }

}