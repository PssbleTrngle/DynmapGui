package com.possible_triangle.dynmapgui.gui

import com.possible_triangle.dynmapgui.DynmapGuiMod
import com.possible_triangle.dynmapgui.DynmapGuiMod.DYNMAP_API
import com.possible_triangle.dynmapgui.extensions.createControl
import com.possible_triangle.dynmapgui.extensions.displayItem
import eu.pb4.sgui.api.elements.GuiElement
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.inventory.MenuType
import org.dynmap.markers.MarkerIcon

abstract class IconSelectGui(player: ServerPlayer) : PaginatedGui(MenuType.GENERIC_9x5, player, false) {

    abstract fun select(icon: MarkerIcon)

    override fun update() {
        super.update()

        title = DynmapGuiMod.translation("title.select_icon")
    }

    override fun getEntries(): List<GuiElement> {
        return DYNMAP_API.markerAPI.markerIcons.map { icon ->
            createControl(icon.displayItem(), icon.markerIconLabel) {
                select(icon)
                close()
            }.build()
        }
    }

}