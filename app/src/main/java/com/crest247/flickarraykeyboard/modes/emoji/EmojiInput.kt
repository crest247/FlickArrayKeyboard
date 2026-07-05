package com.crest247.flickarraykeyboard.modes.emoji

import com.crest247.flickarraykeyboard.core.models.KeyboardAction

sealed interface EmojiAction : KeyboardAction {
    data class InputEmoji(val emoji: String) : EmojiAction
    data class SwitchCategory(val index: Int) : EmojiAction
    object PrevPage : EmojiAction
    object NextPage : EmojiAction
}