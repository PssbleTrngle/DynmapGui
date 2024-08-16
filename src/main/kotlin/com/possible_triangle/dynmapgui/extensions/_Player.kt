package com.possible_triangle.dynmapgui.extensions

import net.minecraft.server.level.ServerPlayer
import org.dynmap.fabric_1_21.DynmapPlugin
import org.dynmap.fabric_1_21.FabricPlayer

fun ServerPlayer.hasDynmapPermission(key: String): Boolean {
    if(hasPermissions(2)) return true
    return FabricPlayer(DynmapPlugin.plugin, this).hasPrivilege(key)
}