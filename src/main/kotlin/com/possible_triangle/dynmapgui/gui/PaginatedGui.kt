package com.possible_triangle.dynmapgui.gui

import com.possible_triangle.dynmapgui.CustomIcon
import eu.pb4.sgui.api.elements.GuiElement
import eu.pb4.sgui.api.gui.SimpleGui
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.inventory.MenuType
import kotlin.math.max

abstract class PaginatedGui(type: MenuType<*>?, player: ServerPlayer?, manipulatePlayerSlots: Boolean) :
    SimpleGui(type, player, manipulatePlayerSlots) {

    private var page = 0
        set(value) {
            field = max(0, value)
            update()
        }

    private val perPage get() = width * (height - 2)

    private val offset get() = page * perPage

    override fun onOpen() = update()

    protected abstract fun getEntries(): List<GuiElement>

    protected open fun update() {
        val limit = offset + perPage
        val entries = getEntries()

        for (i in offset until limit) {
            val slot = i - offset + width
            entries.getOrNull(i)?.let {
                setSlot(slot, it)
            } ?: run {
                clearSlot(slot)
            }
        }

        if (page > 0) {
            setSlot(width * (height - 1) + 1, CustomIcon.PREVIOUS.guiElement {
                page--
            })
        } else {
            clearSlot(width * (height - 1) + 1)
        }

        if (entries.size > limit) {
            setSlot(width * height - 2, CustomIcon.NEXT.guiElement {
                page++
            })
        } else {
            clearSlot(width * height - 2)
        }
    }

}