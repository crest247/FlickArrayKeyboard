package com.crest247.flickarraykeyboard.modes.emoji

import com.crest247.flickarraykeyboard.core.models.Clickable
import com.crest247.flickarraykeyboard.core.models.KeyboardAction
import com.crest247.flickarraykeyboard.core.models.UpTriggerable

sealed interface EmojiAction : KeyboardAction {
    data class InputEmoji(val emoji: String) : EmojiAction, UpTriggerable
    data class SwitchCategory(val index: Int) : EmojiAction, Clickable
    object PrevPage : EmojiAction, UpTriggerable
    object NextPage : EmojiAction, UpTriggerable
}