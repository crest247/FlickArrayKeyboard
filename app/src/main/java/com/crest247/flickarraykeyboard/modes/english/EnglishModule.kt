package com.crest247.flickarraykeyboard.modes.english

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.layout.LayoutCoordinates
import com.crest247.flickarraykeyboard.core.models.KeyboardModule

object EnglishModule : KeyboardModule {
    override val processor: EnglishProcessor by lazy { EnglishProcessor() }

    @Composable
    override fun SpellingAreaLayout(onRadicalLayoutChanged: (LayoutCoordinates?) -> Unit) {
        DisposableEffect(Unit) {
            onDispose { onRadicalLayoutChanged(null) }
        }
    }

    @Composable
    override fun KeyAreaLayout() {
        EnglishKeyLayout(processor = processor)
    }

    @Composable
    override fun SuggestionAreaLayout() {
    }
}