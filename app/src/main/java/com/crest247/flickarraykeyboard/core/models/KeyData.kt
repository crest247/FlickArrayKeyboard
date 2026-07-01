package com.crest247.flickarraykeyboard.core.models

import android.view.inputmethod.EditorInfo
import android.view.inputmethod.EditorInfo.IME_MASK_ACTION
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.automirrored.outlined.Backspace
import androidx.compose.material.icons.automirrored.outlined.KeyboardReturn
import androidx.compose.material.icons.automirrored.outlined.KeyboardTab
import androidx.compose.material.icons.automirrored.outlined.NextPlan
import androidx.compose.material.icons.outlined.ArrowCircleRight
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Search
import com.crest247.flickarraykeyboard.core.ui.components.KeyContent

enum class FuncType(
    val isRepeatable: Boolean = false,
    val useDown: Boolean = false
) {
    ENTER, SPACE, LANGUAGE, TAB,
    BACKSPACE(isRepeatable = true),
    SHIFT(useDown = true),
}

enum class KeyBackgroundType {
    NORMAL,
    FUNCTIONAL
}

sealed interface KeyData {
    val weight: Float
}

data class SpacerData(override val weight: Float) : KeyData

sealed interface VisibleKeyData : KeyData {
    val backgroundType: KeyBackgroundType
}

data class TapKeyData<T>(
    val text: String,
    val action: T,
    override val weight: Float = 1.0f,
    override val backgroundType: KeyBackgroundType = KeyBackgroundType.NORMAL
) : VisibleKeyData

data class FlickKeyData<T>(
    val centerText: String,
    val popupContents: List<KeyContent?>,
    val directionActions: Map<Int, T>,
    override val weight: Float = 1.0f,
    override val backgroundType: KeyBackgroundType = KeyBackgroundType.NORMAL
) : VisibleKeyData

data class FuncKeyData(
    val type: FuncType,
    val content: KeyContent,
    override val weight: Float = 1.0f,
    override val backgroundType: KeyBackgroundType = KeyBackgroundType.FUNCTIONAL
) : VisibleKeyData {
    companion object {
        private val defaultContents = mapOf(
            FuncType.BACKSPACE to KeyContent.Icon(Icons.AutoMirrored.Outlined.Backspace),
            FuncType.SPACE to KeyContent.Text(" "),
            FuncType.ENTER to KeyContent.Icon(Icons.AutoMirrored.Outlined.KeyboardReturn),
            FuncType.SHIFT to KeyContent.Text("⇧"),
            FuncType.LANGUAGE to KeyContent.Icon(Icons.Outlined.Language),
            FuncType.TAB to KeyContent.Icon(Icons.AutoMirrored.Outlined.KeyboardTab)
        )

        fun create(type: FuncType, weight: Float = 1.0f): FuncKeyData {
            val content = defaultContents[type] ?: throw (IllegalArgumentException("Unknown type"))

            return FuncKeyData(
                type,
                content,
                weight,
                when (type) {
                    FuncType.SPACE -> KeyBackgroundType.NORMAL
                    else -> KeyBackgroundType.FUNCTIONAL
                }
            )
        }

        fun createEnterKey(imeOptions: Int?, weight: Float = 1.5f): FuncKeyData {
            val action = imeOptions?.and(IME_MASK_ACTION)
            val content = when (action) {
                EditorInfo.IME_ACTION_SEARCH -> KeyContent.Icon(Icons.Outlined.Search)
                EditorInfo.IME_ACTION_SEND -> KeyContent.Icon(Icons.AutoMirrored.Filled.Send)
                EditorInfo.IME_ACTION_DONE -> KeyContent.Icon(Icons.Outlined.Done)
                EditorInfo.IME_ACTION_GO -> KeyContent.Icon(Icons.Outlined.ArrowCircleRight)
                EditorInfo.IME_ACTION_NEXT -> KeyContent.Icon(Icons.AutoMirrored.Outlined.NextPlan)
                else -> KeyContent.Icon(Icons.AutoMirrored.Outlined.KeyboardReturn)
            }

            return FuncKeyData(
                type = FuncType.ENTER,
                content = content,
                weight = weight,
                backgroundType = KeyBackgroundType.FUNCTIONAL
            )
        }
    }
}
