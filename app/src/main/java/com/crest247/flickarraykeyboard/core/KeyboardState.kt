package com.crest247.flickarraykeyboard.core

import android.text.InputType
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.crest247.flickarraykeyboard.core.engine.SystemProcessor
import com.crest247.flickarraykeyboard.modes.english.EnglishProcessor
import com.crest247.flickarraykeyboard.modes.english.EnglishVariant

enum class KeyboardMode {
    English
}

class KeyboardState {
    var currentInputConnection by mutableStateOf<InputConnection?>(null)
        private set

    var currentEditorInfo by mutableStateOf<EditorInfo?>(null)
        private set
    var currentMode by mutableStateOf(KeyboardMode.English)
        private set
    var actionToken by mutableStateOf(0L)
        private set

    val englishProcessor = EnglishProcessor()
    val systemProcessor = SystemProcessor(this)

    fun renewActionToken() {
        actionToken = System.currentTimeMillis()
    }

    fun updateConnection(inputConnection: InputConnection, editorInfo: EditorInfo) {
        this.currentInputConnection = inputConnection
        this.currentEditorInfo = editorInfo
        englishProcessor.updateConnection(inputConnection, editorInfo)

        val inputClass = editorInfo.inputType and InputType.TYPE_MASK_CLASS
        val inputVariation = editorInfo.inputType and InputType.TYPE_MASK_VARIATION

        if (inputClass == InputType.TYPE_CLASS_TEXT) {
            englishProcessor.variant = when (inputVariation) {
                InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS -> EnglishVariant.Email
                InputType.TYPE_TEXT_VARIATION_URI -> EnglishVariant.Url
                else -> EnglishVariant.Default
            }
        }
        // 如果是電話號碼輸入框
        else if (inputClass == InputType.TYPE_CLASS_PHONE) {
            // numberProcessor.variant = NumberVariant.Phone
            // 甚至你可以直接強制切換模式！
            // switchMode(KeyboardMode.Number)
        }
    }

    fun switchMode(mode: KeyboardMode) {
        currentMode = mode
    }
}

val LocalKeyboardState = compositionLocalOf<KeyboardState> {
    error("No KeyboardState provided")
}