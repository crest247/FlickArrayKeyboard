package com.crest247.flickarraykeyboard.modes.symbol

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.crest247.flickarraykeyboard.core.LocalKeyboardState
import com.crest247.flickarraykeyboard.core.engine.SystemAction
import com.crest247.flickarraykeyboard.core.executeDefault
import com.crest247.flickarraykeyboard.core.models.FlickKeyData
import com.crest247.flickarraykeyboard.core.models.KeyboardAction
import com.crest247.flickarraykeyboard.core.models.VisibleKeyData
import com.crest247.flickarraykeyboard.core.theme.LocalKeyboardDimens
import com.crest247.flickarraykeyboard.core.ui.components.StandardKeyboard

@Composable
fun SymbolKeyLayout(processor: SymbolProcessor) {
    val dimens = LocalKeyboardDimens.current
    val state = LocalKeyboardState.current
    val systemProcessor = state.systemProcessor
    val editorInfo = state.currentEditorInfo

    val keyRows = remember(editorInfo, processor.currentCategoryIndex) {
        SymbolLayoutProvider.createKeys(editorInfo, processor.currentCategoryIndex)
    }

    StandardKeyboard(
        keyRows = keyRows,
        rowHeight = dimens.symbolKeyHeight
    ) { keyEvent ->
        processor.executeDefault(keyEvent, systemProcessor)
    }
}