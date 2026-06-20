package com.crest247.flickarraykeyboard.modes.array30

import androidx.compose.runtime.Composable
import com.crest247.flickarraykeyboard.core.models.KeyboardModule

object Array30Module : KeyboardModule {
    override val processor: Array30Processor by lazy { Array30Processor() }

    @Composable
    override fun SpellingAreaLayout() {
        Array30SpellingLayout(processor = processor)
    }

    @Composable
    override fun SuggestionAreaLayout() {
        Array30SuggestionLayout(processor = processor)
    }

    @Composable
    override fun KeyAreaLayout() {
        Array30KeyLayout(processor = processor)
    }
}