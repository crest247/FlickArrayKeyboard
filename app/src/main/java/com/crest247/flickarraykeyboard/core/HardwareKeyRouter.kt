package com.crest247.flickarraykeyboard.core

import android.view.KeyEvent
import com.crest247.flickarraykeyboard.modes.array30.Array30Module
import com.crest247.flickarraykeyboard.modes.arrayFlick.ArrayFlickModule
import com.crest247.flickarraykeyboard.modes.english.EnglishModule

class HardwareKeyRouter(
    private val keyboardState: KeyboardState,
) {
    val systemProcessor = keyboardState.systemProcessor
    private val consumedKeyCodes = mutableSetOf<Int>()

    fun dispatchKeyDown(event: KeyEvent): Boolean {
        when(keyboardState.currentModule)
        {
            EnglishModule, Array30Module -> {}
            ArrayFlickModule -> keyboardState.switchModule(Array30Module)
            else -> keyboardState.switchModule(EnglishModule)
        }
        val keyCode = event.keyCode
        keyboardState.isPhysicalKeyboardActive = keyCode != KeyEvent.KEYCODE_BACK
        val handled = keyboardState.currentModule.processor.onHardwareKeyDown(event) ||
                systemProcessor.onHardwareKeyDown(event)
        if (handled)
            consumedKeyCodes.add(keyCode)
        return handled
    }

    fun dispatchKeyUp(event: KeyEvent): Boolean {
        val keyCode = event.keyCode
        val wasConsumedOnDown = consumedKeyCodes.remove(keyCode)
        val handledByProcessor = keyboardState.currentModule.processor.onHardwareKeyUp(event) ||
                systemProcessor.onHardwareKeyUp(event)
        return wasConsumedOnDown || handledByProcessor
    }
}