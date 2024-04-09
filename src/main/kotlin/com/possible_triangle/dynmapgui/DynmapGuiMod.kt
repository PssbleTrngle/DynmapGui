package com.possible_triangle.dynmapgui

import com.possible_triangle.dynmapgui.command.MarkerCommand
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import org.dynmap.DynmapCommonAPI
import org.dynmap.DynmapCommonAPIListener

object DynmapGuiMod : ModInitializer {

    lateinit var DYNMAP_API: DynmapCommonAPI
        private set

    private object Listener : DynmapCommonAPIListener() {
        override fun apiEnabled(api: DynmapCommonAPI) {
            DYNMAP_API = api
        }

    }

    override fun onInitialize() {
        DynmapCommonAPIListener.register(Listener)

        CommandRegistrationCallback.EVENT.register { dispatcher, _, _ ->
            MarkerCommand.register(dispatcher)
        }
    }

}