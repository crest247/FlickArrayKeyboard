package com.crest247.flickarraykeyboard.modes.array30

import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.crest247.flickarraykeyboard.core.InputProcessor
import com.crest247.flickarraykeyboard.core.KeyboardAction
import com.crest247.flickarraykeyboard.modes.shared.array.ArrayAction
import com.crest247.flickarraykeyboard.modes.shared.array.ArrayDecoder

class Array30Processor : InputProcessor {
    private var inputConnection: InputConnection? = null
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
            is ArrayAction.Input -> {
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
                    inputConnection?.deleteSurroundingText(1, 0)
                }
                true
            }

            is ArrayAction.Space -> {
                if (candidates.isNotEmpty()) commitCandidate(candidates.first())
                else if (displayTokens.isEmpty()) {
                    inputConnection?.commitText(" ", 1)
                }
                true
            }

            is ArrayAction.Enter -> {
                if (candidates.isNotEmpty()) commitCandidate(candidates.first())
                else if (displayTokens.isEmpty()) {
                    val actionId = editorInfo?.imeOptions?.and(EditorInfo.IME_MASK_ACTION)
                    if (actionId == EditorInfo.IME_ACTION_NONE || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                        inputConnection?.commitText("\n", 1)
                    } else {
                        actionId?.let { inputConnection?.performEditorAction(it) }
                    }
                }
                true
            }
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