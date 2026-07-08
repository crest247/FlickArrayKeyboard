package com.crest247.flickarraykeyboard.modes.array30

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.crest247.flickarraykeyboard.core.models.KeyboardAction
import com.crest247.flickarraykeyboard.core.LocalKeyboardState
import com.crest247.flickarraykeyboard.core.engine.SystemAction
import com.crest247.flickarraykeyboard.core.executeDefault
import com.crest247.flickarraykeyboard.core.models.FlickKeyData
import com.crest247.flickarraykeyboard.core.models.KeyboardKeyEvent
import com.crest247.flickarraykeyboard.core.models.VisibleKeyData
import com.crest247.flickarraykeyboard.core.theme.LocalKeyboardDimens
import com.crest247.flickarraykeyboard.core.ui.components.StandardKeyboard
import com.crest247.flickarraykeyboard.core.ui.preview.KeyboardPreviewWrapper
import com.crest247.flickarraykeyboard.core.ui.preview.ThemePreviews
import com.crest247.flickarraykeyboard.modes.shared.array.ArrayProcessor
import kotlin.collections.get

@Composable
fun Array30KeyLayout(processor: ArrayProcessor) {
    val dimens = LocalKeyboardDimens.current
    val state = LocalKeyboardState.current
    val systemProcessor = state.systemProcessor
    val imeOptions = state.currentEditorInfo?.imeOptions

    val keyRows = remember(imeOptions) {
        Array30LayoutProvider.createKeys(imeOptions)
    }
    StandardKeyboard(
        keyRows = keyRows,
        rowHeight = dimens.array30KeyHeight
    ) { keyEvent ->
        processor.executeDefault(keyEvent, systemProcessor)
    }
}

@ThemePreviews
@Composable
fun PreviewKeyLayout() {
    KeyboardPreviewWrapper {
        Array30KeyLayout(processor = ArrayProcessor())
    }
}