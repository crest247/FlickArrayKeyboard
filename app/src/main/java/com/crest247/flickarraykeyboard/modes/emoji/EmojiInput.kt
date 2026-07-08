package com.crest247.flickarraykeyboard.modes.emoji

import com.crest247.flickarraykeyboard.core.models.Clickable
import com.crest247.flickarraykeyboard.core.models.KeyboardAction

sealed interface EmojiAction : KeyboardAction {
    data class InputEmoji(val emoji: String) : EmojiAction, Clickable
    data class SwitchCategory(val index: Int) : EmojiAction, Clickable
    object PrevPage : EmojiAction, Clickable
    object NextPage : EmojiAction, Clickable
}