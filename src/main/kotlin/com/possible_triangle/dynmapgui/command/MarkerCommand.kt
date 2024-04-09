package com.possible_triangle.dynmapgui.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.context.CommandContext
import com.possible_triangle.dynmapgui.gui.MarkerListGui
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands.literal

object MarkerCommand {

    fun register(dispatcher: CommandDispatcher<CommandSourceStack>) {
        dispatcher.register(
            literal("markers")
                .executes(::showMarkers)
        )
    }

    private fun showMarkers(ctx: CommandContext<CommandSourceStack>): Int {
        MarkerListGui.open(ctx.source.playerOrException)
        return 1
    }

}
