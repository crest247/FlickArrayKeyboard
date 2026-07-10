package com.crest247.flickarraykeyboard.modes.shared.array

import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.crest247.flickarraykeyboard.core.InputProcessor
import com.crest247.flickarraykeyboard.core.engine.SystemAction
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

    override fun onAction(action: KeyboardAction): KeyboardAction? {
        if (action !is ArrayAction) return action

        when (action) {
            is ArrayAction.InputRadical -> {
                displayTokens.add(action.displayStr)
                lookupTokens.add(action.lookupStr)
                updateCandidates()
            }

            is ArrayAction.Backspace -> {
                if (displayTokens.isNotEmpty()) {
                    displayTokens.removeAt(displayTokens.lastIndex)
                    lookupTokens.removeAt(lookupTokens.lastIndex)
                    if (displayTokens.isEmpty())
                        candidates = emptyList()
                    else
                        updateCandidates()
                } else
                    return SystemAction.Backspace
            }

            is ArrayAction.Space -> {
                if (candidates.isNotEmpty()) commitCandidate(candidates.first())
                else if (displayTokens.isEmpty()) return SystemAction.Space
            }

            is ArrayAction.Enter -> {
                if (candidates.isNotEmpty()) commitCandidate(candidates.first())
                else if (displayTokens.isEmpty()) return SystemAction.Enter
            }
        }
        return null
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