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
import com.crest247.flickarraykeyboard.core.engine.SystemAction
import com.crest247.flickarraykeyboard.core.ui.components.KeyContent

object SystemKeyFactory {
    private val defaultContents = mapOf(
        FuncType.BACKSPACE to KeyContent.Icon(Icons.AutoMirrored.Outlined.Backspace),
        FuncType.SPACE to KeyContent.Text(" "),
        FuncType.ENTER to KeyContent.Icon(Icons.AutoMirrored.Outlined.KeyboardReturn),
        FuncType.SHIFT to KeyContent.Text("⇧"),
        FuncType.LANGUAGE to KeyContent.Icon(Icons.Outlined.Language),
        FuncType.TAB to KeyContent.Icon(Icons.AutoMirrored.Outlined.KeyboardTab)
    )

    fun create(type: FuncType, weight: Float = 1.0f, action: Any? = null): VisibleKeyData<*> {
        val content = defaultContents[type] ?: throw (IllegalArgumentException("Unknown type"))

        return when(type)
        {
            FuncType.SPACE -> TapKeyData(
                content,
                action ?: SystemAction.Space,
                weight,
                 KeyBackgroundType.NORMAL
            )

            FuncType.BACKSPACE -> TapKeyData(
                content,
                action ?: SystemAction.Backspace,
                weight,
                KeyBackgroundType.FUNCTIONAL
            )

            FuncType.ENTER -> TapKeyData(
                content,
                action ?: SystemAction.Enter,
                weight,
                KeyBackgroundType.FUNCTIONAL
            )

            FuncType.LANGUAGE -> FlickKeyData(
                KeyContent.Icon(Icons.Outlined.Language),
                listOf(
                    KeyContent.Text(""),
                    KeyContent.Text("行"),
                    KeyContent.Text("A"),
                    KeyContent.Text("1"),
                    KeyContent.Text("行ᶠ")
                ),
                mapOf(
                    0 to null,
                    1 to SystemAction.SwitchModule(0),
                    2 to SystemAction.SwitchModule(1),
                    3 to SystemAction.SwitchModule(2),
                    4 to SystemAction.SwitchModule(3)
                ),
                weight,
                KeyBackgroundType.FUNCTIONAL
            )

            else -> TapKeyData(
                content,
                action,
                weight,
                KeyBackgroundType.FUNCTIONAL
            )
        }
    }

    fun createEnterKey(
        imeOptions: Int?,
        weight: Float = 1.5f,
        action: Any? = null
    ): TapKeyData<*> {
        val content = imeOptions?.and(IME_MASK_ACTION).let {
            when (it) {
                EditorInfo.IME_ACTION_SEARCH -> KeyContent.Icon(Icons.Outlined.Search)
                EditorInfo.IME_ACTION_SEND -> KeyContent.Icon(Icons.AutoMirrored.Filled.Send)
                EditorInfo.IME_ACTION_DONE -> KeyContent.Icon(Icons.Outlined.Done)
                EditorInfo.IME_ACTION_GO -> KeyContent.Icon(Icons.Outlined.ArrowCircleRight)
                EditorInfo.IME_ACTION_NEXT -> KeyContent.Icon(Icons.AutoMirrored.Outlined.NextPlan)
                else -> KeyContent.Icon(Icons.AutoMirrored.Outlined.KeyboardReturn)
            }
        }

        return TapKeyData(
            content = content,
            action = action ?: SystemAction.Enter,
            weight = weight,
            backgroundType = KeyBackgroundType.FUNCTIONAL
        )
    }
}