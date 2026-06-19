package com.crest247.flickarraykeyboard.core.models

import androidx.compose.runtime.Composable
import com.crest247.flickarraykeyboard.core.InputProcessor

interface KeyboardModule {
    val processor: InputProcessor<*>

    @Composable
    fun SpellingAreaLayout()

    @Composable
    fun KeyAreaLayout()

    @Composable
    fun SuggestionAreaLayout()
}