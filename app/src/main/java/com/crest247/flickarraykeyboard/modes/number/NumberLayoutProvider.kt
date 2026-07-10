package com.crest247.flickarraykeyboard.modes.number

import android.view.inputmethod.EditorInfo
import com.crest247.flickarraykeyboard.core.models.FlickKeyData
import com.crest247.flickarraykeyboard.core.models.FuncType
import com.crest247.flickarraykeyboard.core.models.KeyData
import com.crest247.flickarraykeyboard.core.models.SystemKeyFactory
import com.crest247.flickarraykeyboard.core.models.TapKeyData
import com.crest247.flickarraykeyboard.core.ui.components.KeyContent
import com.crest247.flickarraykeyboard.modes.shared.array.ArrayAction

object NumberLayoutProvider {
    fun createKeys(editorInfo: EditorInfo?): List<List<KeyData>> {
        fun numKey(text: String): TapKeyData<NumberAction> {
            return TapKeyData(
                text = text,
                action = NumberAction.InputChar(text)
            )
        }

        fun flickKey(centerText: String, popupTexts: List<String>): FlickKeyData<*> {
            return FlickKeyData(
                KeyContent.Text(centerText),
                List(popupTexts.size + 1) { index ->
                    if (index == 0) KeyContent.Text("")
                    else KeyContent.Text(popupTexts[index - 1])
                },
                List(popupTexts.size + 1) { index ->
                    if (index == 0) 0 to null
                    else index to NumberAction.InputChar(popupTexts[index - 1])
                }.toMap()
            )
        }

        return listOf(
            listOf(
                SystemKeyFactory.create(FuncType.TAB, 1.0f),
                numKey("1"),
                numKey("2"),
                numKey("3"),
                SystemKeyFactory.create(FuncType.BACKSPACE, 1.0f),
            ),
            listOf(
                flickKey(
                    ".",
                    listOf(".", ",", ":", "_", "!", "?", "°")
                ),
                numKey("4"),
                numKey("5"),
                numKey("6"),
                flickKey(
                    "(",
                    listOf("(", ")", "]", "[", ">", "<", "{", "}")
                ),
            ),
            listOf(
                SystemKeyFactory.create(FuncType.SPACE, 1.0f),
                numKey("7"),
                numKey("8"),
                numKey("9"),
                SystemKeyFactory.create(FuncType.SPACE, 1.0f),
            ),
            listOf(
                SystemKeyFactory.create(FuncType.LANGUAGE, 1.0f),
                flickKey(
                    "*",
                    listOf("*", "+", "-", "/", "=", "×", "÷")
                ),
                numKey("0"),
                flickKey(
                    "#",
                    listOf("#", "$", "%", "|", "^", "~", "&")
                ),
                SystemKeyFactory.createEnterKey(editorInfo, 1.0f)
            )
        )
    }
}