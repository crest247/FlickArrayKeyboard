package com.crest247.flickarraykeyboard.modes.number

import com.crest247.flickarraykeyboard.core.models.FlickKeyData
import com.crest247.flickarraykeyboard.core.models.FuncKeyData
import com.crest247.flickarraykeyboard.core.models.FuncType
import com.crest247.flickarraykeyboard.core.models.KeyData
import com.crest247.flickarraykeyboard.core.models.TapKeyData
import com.crest247.flickarraykeyboard.core.ui.components.KeyContent

object NumberLayoutProvider {
    fun createKeys(imeOptions: Int?): List<List<KeyData>> {
        fun numKey(text: String): TapKeyData<NumberAction> {
            return TapKeyData(
                text = text,
                action = NumberAction.InputChar(text)
            )
        }

        fun flickKey(centerText: String, popupTexts: List<String>): FlickKeyData<NumberAction> {
            return FlickKeyData(
                centerText,
                popupTexts.map { text ->
                    KeyContent.Text(text)
                },
                popupTexts.mapIndexed { index, text ->
                    index to NumberAction.InputChar(text)
                }.toMap()
            )
        }

        return listOf(
            listOf(
                FuncKeyData.create(FuncType.TAB, 1.0f),
                numKey("1"),
                numKey("2"),
                numKey("3"),
                FuncKeyData.create(FuncType.BACKSPACE, 1.0f),
            ),
            listOf(
                flickKey(
                    ".",
                    listOf(".", ",", ":", "_", "!", "?")
                ),
                numKey("4"),
                numKey("5"),
                numKey("6"),
                flickKey(
                    "(",
                    listOf("(", ")", "[", "]", ">", "<", "{", "}")
                ),
            ),
            listOf(
                FuncKeyData.create(FuncType.SPACE, 1.0f),
                numKey("7"),
                numKey("8"),
                numKey("9"),
                FuncKeyData.create(FuncType.SPACE, 1.0f),
            ),
            listOf(
                FuncKeyData.create(FuncType.LANGUAGE, 1.0f),
                flickKey(
                    "*",
                    listOf("*", "+", "-", "/", "=", "×", "÷")
                ),
                numKey("0"),
                flickKey(
                    "#",
                    listOf("#", "$", "%", "^", "~", "&")
                ),
                FuncKeyData.create(FuncType.ENTER, 1.0f)
            )
        )
    }
}