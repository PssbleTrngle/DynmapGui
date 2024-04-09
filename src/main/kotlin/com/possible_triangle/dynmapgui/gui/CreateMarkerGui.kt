package com.possible_triangle.dynmapgui.gui

import com.possible_triangle.dynmapgui.DynmapGuiMod.DYNMAP_API
import net.minecraft.server.level.ServerPlayer
import org.dynmap.fabric_1_20.FabricWorld
import org.dynmap.markers.MarkerIcon

class CreateMarkerGui(private val player: ServerPlayer) {

    companion object {
        fun open(player: ServerPlayer) {
            CreateMarkerGui(player).open()
        }
    }

    fun open() {
        EnterName().open()
    }

    fun submit(name: String, icon: MarkerIcon) {
        val set = DYNMAP_API.markerAPI.getMarkerSet("Markers")
        val level = FabricWorld.getWorldName(null, player.level())
        val id = name.lowercase().replace("/\\w+/".toRegex(), "_")
        set.createMarker(id, name, false, level, player.x, player.y, player.z, icon, true)
    }

    private inner class EnterName : RenameGui(player, "Enter Name:") {
        override fun submit(value: String) {
            SelectIcon(value).open()
        }
    }

    private inner class SelectIcon(private val name: String) : IconSelectGui(player) {
        override fun select(icon: MarkerIcon) {
            submit(name, icon)
        }
    }

}