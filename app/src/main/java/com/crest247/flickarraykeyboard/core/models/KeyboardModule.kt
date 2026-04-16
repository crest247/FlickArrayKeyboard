package com.crest247.flickarraykeyboard.core.models

import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.LayoutCoordinates
import com.crest247.flickarraykeyboard.core.InputProcessor

interface KeyboardModule {
    val processor: InputProcessor<*>
    @Composable
    fun SpellingAreaLayout(onRadicalLayoutChanged: (LayoutCoordinates?) -> Unit)
    @Composable
    fun KeyAreaLayout()
    @Composable
    fun SuggestionAreaLayout()
}