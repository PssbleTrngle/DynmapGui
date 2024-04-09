package com.possible_triangle.dynmapgui.gui

import com.possible_triangle.dynmapgui.DynmapGuiMod.DYNMAP_API
import com.possible_triangle.dynmapgui.extensions.addControl
import com.possible_triangle.dynmapgui.extensions.guiElement
import eu.pb4.sgui.api.gui.SimpleGui
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.inventory.MenuType
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level
import org.dynmap.fabric_1_20.FabricWorld

class MarkerListGui(
    player: ServerPlayer,
    private var selectedLevel: Level = player.level(),
    private var selectedSet: String? = null,
) :
    SimpleGui(MenuType.GENERIC_9x4, player, false) {

    companion object {
        fun open(player: ServerPlayer) {
            MarkerListGui(player).apply {
                update()
                open()
            }
        }
    }

    fun update() {
        title = Component.literal("Markers")

        addControl(4, Items.END_PORTAL_FRAME, "Change Dimension") {
            selectedLevel = player.server.allLevels.toList().let { list ->
                list[(list.indexOf(selectedLevel) + 1) % list.size]
            }
            update()
        }

        addControl(8, Items.WRITABLE_BOOK, "Create") {
            CreateMarkerGui.open(player)
        }

        val selectedLevelName = FabricWorld.getWorldName(null, selectedLevel)

        val sets = selectedSet
            ?.let { listOf(DYNMAP_API.markerAPI.getMarkerSet(it)) }
            ?: DYNMAP_API.markerAPI.markerSets

        val markers = sets.flatMap { it.markers }

        markers
            .filter { selectedLevelName == it.world }
            .forEachIndexed { i, marker ->
                setSlot(width + i, marker.guiElement().setCallback { _, _, _ ->
                    MarkerGui.open(player, marker)
                })
            }
    }

}