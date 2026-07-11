package com.crest247.flickarraykeyboard.core

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.crest247.flickarraykeyboard.core.engine.SystemProcessor
import com.crest247.flickarraykeyboard.core.models.KeyboardModule
import com.crest247.flickarraykeyboard.modes.array30.Array30Module
import com.crest247.flickarraykeyboard.modes.arrayFlick.ArrayFlickModule
import com.crest247.flickarraykeyboard.modes.emoji.EmojiModule
import com.crest247.flickarraykeyboard.modes.english.EnglishModule
import com.crest247.flickarraykeyboard.modes.number.NumberModule
import com.crest247.flickarraykeyboard.modes.symbol.SymbolModule

class KeyboardState {
    val availableModules: List<KeyboardModule> = listOf(
        ArrayFlickModule,
        Array30Module,
        EnglishModule,
        SymbolModule,
        EmojiModule,
        NumberModule
    )
    var currentModule by mutableStateOf(availableModules.first()); private set
    var currentInputConnection by mutableStateOf<InputConnection?>(null); private set
    var currentEditorInfo by mutableStateOf<EditorInfo?>(null); private set
    val systemProcessor = SystemProcessor(this)
    var isPhysicalKeyboardActive by mutableStateOf(false)
    private val consumedPhysicalKeyCodes = mutableSetOf<Int>()

    fun updateConnection(inputConnection: InputConnection, editorInfo: EditorInfo) {
        this.currentInputConnection = inputConnection
        this.currentEditorInfo = editorInfo
        currentModule.processor.updateConnection(inputConnection, editorInfo)
    }

    fun switchModule(module: KeyboardModule) {
        currentModule = module

        currentInputConnection?.let { inputConnection ->
            currentEditorInfo?.let { editorInfo ->
                currentModule.processor.updateConnection(inputConnection, editorInfo)
            }
        }
    }

    fun onHardwareKeyDown(event: KeyEvent): Boolean {
        val keyCode = event.keyCode
        isPhysicalKeyboardActive = keyCode != KeyEvent.KEYCODE_BACK
        val handled = currentModule.processor.onHardwareKeyDown(event) ||
                systemProcessor.onHardwareKeyDown(event)
        if (handled)
            consumedPhysicalKeyCodes.add(keyCode)
        return handled
    }

    fun onHardwareKeyUp(event: KeyEvent): Boolean {
        val keyCode = event.keyCode
        val wasConsumedOnDown = consumedPhysicalKeyCodes.remove(keyCode)
        val handledByProcessor = currentModule.processor.onHardwareKeyUp(event) ||
                systemProcessor.onHardwareKeyUp(event)
        return wasConsumedOnDown || handledByProcessor
    }
}

val LocalKeyboardState = compositionLocalOf<KeyboardState> {
    error("No KeyboardState provided")
}