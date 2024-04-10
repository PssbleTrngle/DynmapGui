package com.possible_triangle.dynmapgui.gui

import com.possible_triangle.dynmapgui.DynmapGuiMod
import com.possible_triangle.dynmapgui.DynmapGuiMod.DYNMAP_API
import com.possible_triangle.dynmapgui.extensions.dynmapName
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import org.dynmap.markers.MarkerIcon
import org.dynmap.markers.MarkerSet

class CreateMarkerGui(private val player: ServerPlayer) {

    companion object {
        fun open(player: ServerPlayer) {
            CreateMarkerGui(player).open()
        }
    }

    fun open() {
        EnterName().open()
    }

    fun submit(name: String, icon: MarkerIcon, set: MarkerSet) {
        val id = name.lowercase().replace("/\\w+/".toRegex(), "_")
        set.createMarker(id, name, false, player.level().dynmapName, player.x, player.y, player.z, icon, true)
    }

    private inner class EnterName : RenameGui(player) {
        override fun onOpen() {
            super.onOpen()
            title = DynmapGuiMod.translation("title.enter_name")
        }

        override fun submit(value: String) {
            SelectIcon(value).open()
        }
    }

    private inner class SelectIcon(private val name: String) : IconSelectGui(player) {
        override fun select(icon: MarkerIcon) {
            val sets = DYNMAP_API.markerAPI.markerSets

            if (sets.size == 1) submit(name, icon, sets.first())
            else SelectSet(name, icon).open()
        }
    }

    private inner class SelectSet(private val name: String, private val icon: MarkerIcon) : SetSelectGui(player) {
        override fun select(set: MarkerSet) {
            submit(name, icon, set)
        }
    }

}