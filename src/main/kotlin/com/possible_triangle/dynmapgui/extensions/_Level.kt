package com.possible_triangle.dynmapgui.extensions

import net.minecraft.world.level.Level
import org.dynmap.fabric_1_21.FabricWorld

val Level.dynmapName get() = FabricWorld.getWorldName(null, this)