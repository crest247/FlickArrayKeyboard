package com.crest247.flickarraykeyboard.modes.symbol

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.crest247.flickarraykeyboard.core.LocalKeyboardState
import com.crest247.flickarraykeyboard.core.engine.SystemAction
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
    val imeOptions = state.currentEditorInfo?.imeOptions

    val keyRows = remember(imeOptions, processor.currentCategoryIndex) {
        SymbolLayoutProvider.createKeys(imeOptions, processor.currentCategoryIndex)
    }

    StandardKeyboard(
        keyRows = keyRows,
        rowHeight = dimens.symbolKeyHeight
    ) { keyData, direction ->
        when (keyData) {
            is FlickKeyData<*> -> keyData.directionActions[direction]
            is VisibleKeyData<*> -> keyData.action
            else -> null
        }?.let { it as? KeyboardAction }?.let { action ->
            if (!processor.onAction(action))
                (action as? SystemAction)?.let { systemProcessor.onAction(it) }
        }
    }
}