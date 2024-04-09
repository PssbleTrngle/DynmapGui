package com.possible_triangle.dynmapgui.gui

import com.possible_triangle.dynmapgui.DynmapGuiMod.DYNMAP_API
import com.possible_triangle.dynmapgui.extensions.addControl
import com.possible_triangle.dynmapgui.extensions.displayStack
import eu.pb4.sgui.api.gui.SimpleGui
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.inventory.MenuType
import org.dynmap.markers.MarkerIcon

abstract class IconSelectGui(player: ServerPlayer) : SimpleGui(MenuType.GENERIC_9x5, player, false) {

    abstract fun select(icon: MarkerIcon)

    init {
        title = Component.literal("Select Icon")

        DYNMAP_API.markerAPI.markerIcons.forEachIndexed { i, icon ->
            addControl(i, icon.displayStack().item, icon.markerIconLabel) {
                select(icon)
                close()
            }
        }
    }

}