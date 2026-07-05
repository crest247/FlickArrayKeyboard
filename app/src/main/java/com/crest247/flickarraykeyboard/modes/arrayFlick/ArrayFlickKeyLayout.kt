package com.crest247.flickarraykeyboard.modes.arrayFlick

import android.view.KeyEvent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.crest247.flickarraykeyboard.core.LocalKeyboardState
import com.crest247.flickarraykeyboard.core.engine.SystemAction
import com.crest247.flickarraykeyboard.core.models.FlickKeyData
import com.crest247.flickarraykeyboard.core.models.KeyboardAction
import com.crest247.flickarraykeyboard.core.models.LongPressAction
import com.crest247.flickarraykeyboard.core.models.VisibleKeyData
import com.crest247.flickarraykeyboard.core.theme.LocalKeyboardDimens
import com.crest247.flickarraykeyboard.core.ui.components.StandardKeyboard
import com.crest247.flickarraykeyboard.core.ui.preview.KeyboardPreviewWrapper
import com.crest247.flickarraykeyboard.core.ui.preview.ThemePreviews
import com.crest247.flickarraykeyboard.modes.shared.array.ArrayAction
import com.crest247.flickarraykeyboard.modes.shared.array.ArrayProcessor

@Composable
fun ArrayFlickKeyLayout(processor: ArrayProcessor) {
    val dimens = LocalKeyboardDimens.current
    val state = LocalKeyboardState.current
    val systemProcessor = state.systemProcessor
    val imeOptions = state.currentEditorInfo?.imeOptions

    var metaState by remember { mutableIntStateOf(0) }

    val keyRows = remember(imeOptions, metaState) {
        ArrayFlickLayoutProvider.createKeys(imeOptions, metaState)
    }
    StandardKeyboard(
        keyRows = keyRows,
        rowHeight = dimens.arrayFlickKeyHeight,
        onKeyLongPress = { keyData, direction ->
            val baseAction = when (keyData) {
                is FlickKeyData<*> -> keyData.directionActions[direction]
                else -> null
            }
            if (baseAction is LongPressAction) {
                baseAction.longPressAction?.let { longPressAction ->
                    processor.onAction(longPressAction)
                }
            }
        },
        onKeyFlick = { keyData, direction ->
            val baseAction = when (keyData) {
                is FlickKeyData<*> -> keyData.directionActions[0]
                else -> null
            }
            if (baseAction == ArrayFlickAction.PhysicalModifier) {
                val targetMeta = when (direction) {
                    1 -> KeyEvent.KEYCODE_SHIFT_LEFT
                    2 -> KeyEvent.KEYCODE_CTRL_LEFT
                    else -> 0
                }

                if (metaState != targetMeta) {
                    val now = System.currentTimeMillis()
                    state.currentInputConnection?.let { inputConnection ->
                        metaState.let { oldKey ->
                            val upEvent = KeyEvent(now, now, KeyEvent.ACTION_UP, oldKey, 0, 0)
                            inputConnection.sendKeyEvent(upEvent)
                        }
                        targetMeta.let { newKey ->
                            val downEvent = KeyEvent(now, now, KeyEvent.ACTION_DOWN, newKey, 0, 0)
                            inputConnection.sendKeyEvent(downEvent)
                        }
                    }
                    metaState = targetMeta
                }
            }
        },
        onKeyUp = { keyData ->
            val baseAction = when (keyData) {
                is FlickKeyData<*> -> keyData.directionActions[0]
                else -> null
            }
            if (baseAction == ArrayFlickAction.PhysicalModifier) {
                metaState.let { keyCode ->
                    state.currentInputConnection?.let { inputConnection ->
                        val now = System.currentTimeMillis()
                        val upEvent = KeyEvent(now, now, KeyEvent.ACTION_UP, keyCode, 0, 0)
                        inputConnection.sendKeyEvent(upEvent)
                    }
                }
                metaState = 0
            }
        }
    ) { keyData, direction ->
        val baseAction = when (keyData) {
            is FlickKeyData<*> -> keyData.directionActions[direction]
            is VisibleKeyData<*> -> keyData.action
            else -> null
        }
        if (baseAction is ArrayFlickAction.SendRawKey) {
            state.currentInputConnection?.let { inputConnection ->
                val now = System.currentTimeMillis()

                val downEvent =
                    KeyEvent(
                        now,
                        now,
                        KeyEvent.ACTION_DOWN,
                        baseAction.keyCode,
                        0,
                        baseAction.metaState
                    )
                val upEvent =
                    KeyEvent(
                        now,
                        now,
                        KeyEvent.ACTION_UP,
                        baseAction.keyCode,
                        0,
                        baseAction.metaState
                    )
                inputConnection.sendKeyEvent(downEvent)
                inputConnection.sendKeyEvent(upEvent)
            }
        } else {
            baseAction?.let {
                if (it is ArrayFlickAction.InputRadical)
                    ArrayAction.InputRadical(displayStr = it.displayStr, lookupStr = it.lookupStr)
                else it
            }.let { it as? KeyboardAction }?.let { action ->
                if (!processor.onAction(action))
                    (action as? SystemAction)?.let { systemProcessor.onAction(it) }
            }
        }
    }
}

@ThemePreviews
@Composable
fun PreviewKeyLayout() {
    KeyboardPreviewWrapper {
        ArrayFlickKeyLayout(processor = ArrayProcessor())
    }
}