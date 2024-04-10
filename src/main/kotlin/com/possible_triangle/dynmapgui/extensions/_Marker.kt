package com.possible_triangle.dynmapgui.extensions

import com.possible_triangle.dynmapgui.DynmapGuiMod
import eu.pb4.sgui.api.elements.GuiElementBuilder
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.ItemLike
import org.dynmap.markers.Marker
import org.dynmap.markers.MarkerIcon
import kotlin.math.sqrt

private val BUILTIN_MARKERS = mapOf(
    "whiteflag" to Items.WHITE_BANNER,
    "orangeflag" to Items.ORANGE_BANNER,
    "magentaflag" to Items.MAGENTA_BANNER,
    "yellowflag" to Items.YELLOW_BANNER,
    "limeflag" to Items.LIME_BANNER,
    "pinkflag" to Items.PINK_BANNER,
    "grayflag" to Items.GRAY_BANNER,
    "cyanflag" to Items.CYAN_BANNER,
    "purpleflag" to Items.PURPLE_BANNER,
    "blueflag" to Items.BLUE_BANNER,
    "brownflag" to Items.BROWN_BANNER,
    "greenflag" to Items.GREEN_BANNER,
    "redflag" to Items.RED_BANNER,
    "blackflag" to Items.BLACK_BANNER,

    "tree" to Items.OAK_SAPLING,
    "bomb" to Items.TNT,
    "cross" to Items.STRUCTURE_VOID,
    "star" to Items.NETHER_STAR,
    "bed" to Items.WHITE_BED,
    "sign" to Items.OAK_HANGING_SIGN,
    "door" to Items.SPRUCE_DOOR,
    "sun" to Items.SUNFLOWER,
    "flower" to Items.CORNFLOWER,
    "portal" to Items.END_PORTAL_FRAME,
    "skull" to Items.SKELETON_SKULL,
    "fire" to Items.BLAZE_POWDER,
    "lightbulb" to Items.LIGHT,
    "drink" to Items.POTION,
)

fun MarkerIcon.displayItem(): ItemLike {
    return BuiltInRegistries.ITEM.get(ResourceLocation(markerIconID))
        .takeUnless { it == Items.AIR }
        ?: BUILTIN_MARKERS[markerIconID]
        ?: Items.WHITE_BANNER
}

fun Marker.guiElement(player: ServerPlayer? = null): GuiElementBuilder {
    val withDebug = player?.hasDynmapPermission("dynmap.debug") ?: false
    return GuiElementBuilder.from(ItemStack(markerIcon.displayItem()))
        .setName(Component.literal(label))
        .setLore(
            listOfNotNull(
                Component.literal(markerID).withStyle { it.withItalic(true) }.takeIf { withDebug },
                Component.literal("[${x.toInt()} / ${y.toInt()} / ${z.toInt()}]"),
                player?.let { DynmapGuiMod.translation("blocks_away", sqrt(it.distanceToSqr(x, y, z))) },
                Component.translatable(markerSet.markerSetLabel),
            )
        )
        .hideFlags()
}