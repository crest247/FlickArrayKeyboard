package com.crest247.flickarraykeyboard.modes.shared.array

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.crest247.flickarraykeyboard.core.InputProcessor
import com.crest247.flickarraykeyboard.core.extension.sendDownUpKeyEvents
import com.crest247.flickarraykeyboard.core.models.KeyboardAction

open class ArrayProcessor : InputProcessor {
    protected var inputConnection: InputConnection? = null
    var editorInfo: EditorInfo? = null; private set
    val displayTokens = mutableStateListOf<String>()
    private val lookupTokens = mutableStateListOf<String>()
    var candidates by mutableStateOf<List<String>>(emptyList()); private set

    override fun updateConnection(inputConnection: InputConnection, editorInfo: EditorInfo) {
        this.inputConnection = inputConnection
        this.editorInfo = editorInfo
    }

    override fun onAction(action: KeyboardAction): Boolean {
        return if (action !is ArrayAction) false
        else when (action) {
            is ArrayAction.InputRadical -> {
                displayTokens.add(action.displayStr)
                lookupTokens.add(action.lookupStr)
                updateCandidates()
                true
            }

            is ArrayAction.Backspace -> {
                if (displayTokens.isNotEmpty()) {
                    displayTokens.removeAt(displayTokens.lastIndex)
                    lookupTokens.removeAt(lookupTokens.lastIndex)
                    if (displayTokens.isEmpty()) {
                        candidates = emptyList()
                    } else {
                        updateCandidates()
                    }
                } else {
                    inputConnection?.sendDownUpKeyEvents(KeyEvent.KEYCODE_DEL)
                }
                true
            }

            is ArrayAction.Space -> {
                if (candidates.isNotEmpty()) commitCandidate(candidates.first())
                else if (displayTokens.isEmpty()) {
                    inputConnection?.sendDownUpKeyEvents(KeyEvent.KEYCODE_SPACE)
                }
                true
            }

            is ArrayAction.Enter -> {
                if (candidates.isNotEmpty()) commitCandidate(candidates.first())
                else if (displayTokens.isEmpty()) {
                    val options = editorInfo?.imeOptions ?: 0
                    val noEnterAction = (options and EditorInfo.IME_FLAG_NO_ENTER_ACTION) != 0
                    val actionId =
                        if (noEnterAction) EditorInfo.IME_ACTION_NONE else options and EditorInfo.IME_MASK_ACTION
                    if (actionId != EditorInfo.IME_ACTION_NONE)
                        inputConnection?.performEditorAction(actionId)
                    else
                        inputConnection?.commitText("\n", 1)
                }
                true
            }

            else -> false
        }
    }

    private fun updateCandidates() {
        val lookupString = lookupTokens.joinToString("")
        candidates = ArrayDecoder.decode(lookupString)
    }

    fun commitCandidate(word: String) {
        inputConnection?.commitText(word, 1)
        clearState()
    }

    private fun clearState() {
        displayTokens.clear()
        lookupTokens.clear()
        candidates = emptyList()
    }
}