package com.crest247.flickarraykeyboard.modes.arrayFlick

import androidx.compose.runtime.Composable
import com.crest247.flickarraykeyboard.core.models.KeyboardModule
import com.crest247.flickarraykeyboard.modes.shared.array.ArrayProcessor
import com.crest247.flickarraykeyboard.modes.shared.array.ArraySpellingLayout
import com.crest247.flickarraykeyboard.modes.shared.array.ArraySuggestionLayout

object ArrayFlickModule : KeyboardModule {
    override val processor: ArrayProcessor by lazy { ArrayProcessor() }

    @Composable
    override fun SpellingAreaLayout() {
        ArraySpellingLayout(processor = processor)
    }

    @Composable
    override fun SuggestionAreaLayout() {
        ArraySuggestionLayout(processor = processor)
    }

    @Composable
    override fun KeyAreaLayout() {
        ArrayFlickKeyLayout(processor = processor)
    }
}