package com.possible_triangle.dynmapgui.gui

import com.possible_triangle.dynmapgui.CustomIcon
import com.possible_triangle.dynmapgui.DynmapGuiMod
import com.possible_triangle.dynmapgui.DynmapGuiMod.DYNMAP_API
import com.possible_triangle.dynmapgui.DynmapGuiMod.MAP_MANAGER
import com.possible_triangle.dynmapgui.extensions.dynmapName
import com.possible_triangle.dynmapgui.extensions.guiElement
import com.possible_triangle.dynmapgui.extensions.hasDynmapPermission
import eu.pb4.sgui.api.elements.GuiElement
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.inventory.MenuType
import net.minecraft.world.level.Level

class MarkerListGui(
    player: ServerPlayer,
    private var selectedLevel: Level = player.level(),
    private var selectedSet: String? = null,
) :
    PaginatedGui(MenuType.GENERIC_9x4, player, false) {

    companion object {
        fun open(player: ServerPlayer) {
            MarkerListGui(player).open()
        }
    }

    override fun getEntries(): List<GuiElement> {
        val sets = selectedSet
            ?.let { listOf(DYNMAP_API.markerAPI.getMarkerSet(it)) }
            ?: DYNMAP_API.markerAPI.markerSets

        val markers = sets.flatMap { it.markers }

        return markers
            .filter { selectedLevel.dynmapName == it.world }
            .map { marker ->
                marker.guiElement(player).setCallback { _, _, _ ->
                    MarkerGui.open(player, marker)
                }.build()
            }
    }

    override fun update() {
        super.update()

        title = DynmapGuiMod.translation("title.markers")

        setSlot(4, CustomIcon.CHANGE_DIMENSION.guiElement {
            val markerLevels = MAP_MANAGER.getWorlds()
                .filter { !it.isProtected || player.hasDynmapPermission("world.${it.name}") }
                .map { it.name }

            val levels = player.server.allLevels.filter { markerLevels.contains(it.dynmapName) }
            selectedLevel = levels[(levels.indexOf(selectedLevel) + 1) % levels.size]
            update()
        })

        if (player.hasDynmapPermission("marker.add")) {
            setSlot(8, CustomIcon.CREATE.guiElement {
                CreateMarkerGui.open(player)
            })
        }
    }

}