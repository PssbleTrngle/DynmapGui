package com.possible_triangle.dynmapgui.extensions

import net.minecraft.server.level.ServerPlayer
import org.dynmap.common.DynmapCommandSender

fun ServerPlayer.hasDynmapPermission(key: String): Boolean {
    if(hasPermissions(2)) return true
    if(this !is DynmapCommandSender) return false
    return hasPrivilege(key)
}