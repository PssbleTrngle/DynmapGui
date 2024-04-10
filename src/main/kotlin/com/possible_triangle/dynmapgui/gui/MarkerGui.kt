package com.possible_triangle.dynmapgui.gui

import com.possible_triangle.dynmapgui.CustomIcon
import com.possible_triangle.dynmapgui.DynmapGuiMod
import com.possible_triangle.dynmapgui.extensions.dynmapName
import com.possible_triangle.dynmapgui.extensions.guiElement
import com.possible_triangle.dynmapgui.extensions.hasDynmapPermission
import eu.pb4.sgui.api.gui.SimpleGui
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.inventory.MenuType
import org.dynmap.markers.Marker
import org.dynmap.markers.MarkerIcon

class MarkerGui(
    player: ServerPlayer,
    private val marker: Marker,
) :
    SimpleGui(MenuType.GENERIC_9x3, player, false) {

    companion object {
        fun open(player: ServerPlayer, marker: Marker) {
            MarkerGui(player, marker).open()
        }
    }

    override fun onOpen() = update()

    private fun update() {
        title = Component.literal(marker.label)

        setSlot(4, marker.guiElement(player))

        setSlot(0, CustomIcon.BACK.guiElement {
            MarkerListGui.open(player)
        })

        if (player.hasDynmapPermission("marker.update")) {
            setSlot(width + 1, CustomIcon.RENAME.guiElement {
                Rename().open()
            })
        }

        if (player.hasDynmapPermission("marker.update")) {
            setSlot(width + 2, CustomIcon.CHANGE_ICON.guiElement {
                ChangeIcon().open()
            })
        }

        if (player.hasDynmapPermission("marker.movehere")) {
            setSlot(width + 3, CustomIcon.MOVE.guiElement {
                marker.setLocation(player.level().dynmapName, player.x, player.y, player.z)
                close()
            })
        }

        if (player.hasDynmapPermission("marker.teleport")) {
            setSlot(width + 5, CustomIcon.TELEPORT.guiElement {
                val level = player.server.allLevels.find { it.dynmapName == marker.world }!!
                player.teleportTo(level, marker.x, marker.y, marker.z, player.yRot, player.xRot)
                close()
            })
        }

        if (player.hasDynmapPermission("marker.delete")) {
            setSlot(width * 2 - 2, CustomIcon.DELETE.guiElement {
                Delete().open()
            })
        }
    }

    private inner class Rename : RenameGui(player, marker.label) {
        override fun onOpen() {
            super.onOpen()
            title = DynmapGuiMod.translation("title.rename", marker.label)
        }

        override fun submit(value: String) {
            marker.label = value
            this@MarkerGui.open()
        }
    }

    private inner class Delete : ConfirmGui(player) {
        override fun onOpen() {
            super.onOpen()
            title = DynmapGuiMod.translation("title.delete", marker.label)
        }

        override fun submit() {
            marker.deleteMarker()
            this@MarkerGui.open()
        }

        override fun cancel() {
            this@MarkerGui.open()
        }
    }

    private inner class ChangeIcon : IconSelectGui(player) {
        override fun select(icon: MarkerIcon) {
            marker.setMarkerIcon(icon)
            this@MarkerGui.open()
        }
    }

}