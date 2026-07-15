package com.crest247.flickarraykeyboard.core.ui.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.crest247.flickarraykeyboard.core.state.KeyboardState
import com.crest247.flickarraykeyboard.core.state.LocalKeyboardState
import com.crest247.flickarraykeyboard.core.theme.KeyboardTheme
import com.crest247.flickarraykeyboard.core.theme.LocalKeyboardColors
import com.crest247.flickarraykeyboard.core.ui.components.LocalPreviewHandler
import com.crest247.flickarraykeyboard.core.ui.components.PreviewHandler

@Composable
fun KeyboardPreviewWrapper(
    content: @Composable () -> Unit
) {
    val fakeState = remember { KeyboardState() }
    val fakePreviewHandler = remember { PreviewHandler() }

    KeyboardTheme {
        CompositionLocalProvider(
            LocalKeyboardState provides fakeState,
            LocalPreviewHandler provides fakePreviewHandler
        ) {
            Box(
                modifier = Modifier.background(LocalKeyboardColors.current.background)
            ) {
                content()
            }
        }
    }
}