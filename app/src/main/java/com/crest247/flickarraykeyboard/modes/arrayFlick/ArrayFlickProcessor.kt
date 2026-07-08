package com.crest247.flickarraykeyboard.modes.arrayFlick

import android.view.KeyEvent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import com.crest247.flickarraykeyboard.core.models.KeyboardAction
import com.crest247.flickarraykeyboard.modes.shared.array.ArrayAction
import com.crest247.flickarraykeyboard.modes.shared.array.ArrayProcessor

class ArrayFlickProcessor : ArrayProcessor() {
    var metaCode by mutableIntStateOf(0)

    override fun onAction(action: KeyboardAction): Boolean {
        return when (action) {
            is ArrayFlickAction.InputRadicalClick ->
                super.onAction(
                    ArrayAction.InputRadical(
                        displayStr = action.displayStr,
                        lookupStr = action.lookupStr
                    )
                )

            is ArrayFlickAction.InputRadicalLongPress ->
                if (action.direction == 0)
                    super.onAction(
                        ArrayAction.InputRadical(
                            displayStr = action.longPressStr,
                            lookupStr = action.longPressStr
                        )
                    )
                else
                    true

            is ArrayFlickAction.DirectionalPad -> {
                if (metaCode == KeyEvent.KEYCODE_CTRL_LEFT)
                    when (action.direction) {
                        1 -> sendRawKey(KeyEvent.KEYCODE_A, KeyEvent.META_CTRL_ON)
                        2 -> sendRawKey(KeyEvent.KEYCODE_V, KeyEvent.META_CTRL_ON)
                        3 -> sendRawKey(KeyEvent.KEYCODE_Y, KeyEvent.META_CTRL_ON)
                        4 -> sendRawKey(KeyEvent.KEYCODE_Z, KeyEvent.META_CTRL_ON)
                        5 -> sendRawKey(KeyEvent.KEYCODE_C, KeyEvent.META_CTRL_ON)
                    }
                else when (action.direction) {
                    1 -> sendRawKey(KeyEvent.KEYCODE_DPAD_UP, 0)
                    2 -> sendRawKey(KeyEvent.KEYCODE_DPAD_RIGHT, 0)
                    3 -> sendRawKey(KeyEvent.KEYCODE_DPAD_DOWN, 0)
                    4 -> sendRawKey(KeyEvent.KEYCODE_DPAD_LEFT, 0)
                }
                true
            }

            is ArrayFlickAction.PhysicalModifierFlick -> {
                val newMetaCode = when (action.direction) {
                    1 -> KeyEvent.KEYCODE_SHIFT_LEFT
                    2 -> KeyEvent.KEYCODE_CTRL_LEFT
                    else -> 0
                }
                if (metaCode != newMetaCode) {
                    val now = System.currentTimeMillis()
                    inputConnection?.sendKeyEvent(
                        KeyEvent(now, now, KeyEvent.ACTION_UP, metaCode, 0, 0)
                    )
                    inputConnection?.sendKeyEvent(
                        KeyEvent(now, now, KeyEvent.ACTION_DOWN, newMetaCode, 0, 0)
                    )
                    metaCode = newMetaCode
                }
                true
            }

            is ArrayFlickAction.PhysicalModifierUp -> {
                val now = System.currentTimeMillis()
                inputConnection?.sendKeyEvent(
                    KeyEvent(now, now, KeyEvent.ACTION_UP, metaCode, 0, 0)
                )
                metaCode = 0
                true
            }

            else -> super.onAction(action)
        }
    }


    fun sendRawKey(keyCode: Int, metaCode: Int = 0) {
        val now = System.currentTimeMillis()

        val downEvent =
            KeyEvent(
                now, now, KeyEvent.ACTION_DOWN, keyCode, 0, metaCode
            )
        val upEvent =
            KeyEvent(
                now, now, KeyEvent.ACTION_UP, keyCode, 0, metaCode
            )
        inputConnection?.sendKeyEvent(downEvent)
        inputConnection?.sendKeyEvent(upEvent)
    }
}