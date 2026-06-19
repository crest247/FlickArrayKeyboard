package com.crest247.flickarraykeyboard.modes.english

import androidx.compose.runtime.Composable
import com.crest247.flickarraykeyboard.core.models.KeyboardModule

object EnglishModule : KeyboardModule {
    override val processor: EnglishProcessor by lazy { EnglishProcessor() }

    @Composable
    override fun SpellingAreaLayout() {
    }

    @Composable
    override fun KeyAreaLayout() {
        EnglishKeyLayout(processor = processor)
    }

    @Composable
    override fun SuggestionAreaLayout() {
    }
}