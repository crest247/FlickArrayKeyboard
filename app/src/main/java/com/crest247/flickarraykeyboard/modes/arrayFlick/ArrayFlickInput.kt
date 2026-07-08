package com.crest247.flickarraykeyboard.modes.arrayFlick

import com.crest247.flickarraykeyboard.core.models.Clickable
import com.crest247.flickarraykeyboard.core.models.Flickable
import com.crest247.flickarraykeyboard.core.models.KeyboardAction
import com.crest247.flickarraykeyboard.core.models.LongPressable
import com.crest247.flickarraykeyboard.core.models.UpTriggerable
import com.crest247.flickarraykeyboard.modes.shared.array.ArrayAction

sealed interface ArrayFlickAction : ArrayAction {
    data class InputRadical(
        val displayStr: String,
        val lookupStr: String,
        val direction: Int,
        val longPressStr: String
    ) : ArrayFlickAction, Clickable, LongPressable {
        override val clickAction: KeyboardAction = InputRadicalClick(displayStr, lookupStr)
        override val longPressAction: KeyboardAction =
            InputRadicalLongPress(direction, longPressStr)
    }

    data class InputRadicalClick(
        val displayStr: String,
        val lookupStr: String,
    ) : ArrayFlickAction, Clickable

    data class InputRadicalLongPress(
        val direction: Int,
        val longPressStr: String
    ) : ArrayFlickAction, LongPressable

    data class PhysicalModifier(
        val direction: Int,
    ) : ArrayFlickAction, Flickable, UpTriggerable {
        override val flickAction: KeyboardAction = PhysicalModifierFlick(direction)
        override val upAction: KeyboardAction = PhysicalModifierUp
    }

    data class PhysicalModifierFlick(
        val direction: Int,
    ) : ArrayFlickAction, Flickable

    object PhysicalModifierUp : ArrayFlickAction, UpTriggerable

    data class DirectionalPad(
        val direction: Int
    ) : ArrayFlickAction, Clickable
}