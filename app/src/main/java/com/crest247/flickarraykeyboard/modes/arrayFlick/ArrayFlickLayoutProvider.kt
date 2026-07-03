package com.crest247.flickarraykeyboard.modes.arrayFlick

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Language
import com.crest247.flickarraykeyboard.core.engine.SystemAction
import com.crest247.flickarraykeyboard.core.models.FlickKeyData
import com.crest247.flickarraykeyboard.core.models.FuncType
import com.crest247.flickarraykeyboard.core.models.KeyBackgroundType
import com.crest247.flickarraykeyboard.core.models.KeyData
import com.crest247.flickarraykeyboard.core.models.SystemKeyFactory
import com.crest247.flickarraykeyboard.core.ui.components.KeyContent
import com.crest247.flickarraykeyboard.modes.shared.array.ArrayAction

object ArrayFlickLayoutProvider {
    fun createKeys(imeOptions: Int?): List<List<KeyData>> {
        fun radicalKey(
            centerText: String,
            displayTexts: List<String>,
            lookupTexts: List<String>
        ): FlickKeyData<*> {
            return FlickKeyData(
                KeyContent.Text(centerText),
                List(displayTexts.size) { KeyContent.Text(displayTexts[it]) },
                List(displayTexts.size) {
                    it to ArrayAction.InputRadical(displayTexts[it], lookupTexts[it])
                }.toMap()
            )
        }
        return listOf(
            listOf(
                SystemKeyFactory.create(FuncType.TAB, 1.0f),
                radicalKey("1", listOf("1-", "1^", "1v"), listOf("a", "q", "z")),
                radicalKey("2", listOf("2-", "2^", "2v"), listOf("s", "w", "x")),
                radicalKey("3", listOf("3-", "3^", "3v"), listOf("d", "e", "c")),
                SystemKeyFactory.create(FuncType.BACKSPACE, 1.0f, ArrayAction.Backspace),
            ),
            listOf(
                SystemKeyFactory.create(FuncType.SPACE, 1.0f, ArrayAction.Space),
                radicalKey("4", listOf("4-", "4^", "4v"), listOf("f", "r", "v")),
                radicalKey("5", listOf("5-", "5^", "5v"), listOf("g", "t", "b")),
                radicalKey("6", listOf("6-", "6^", "6v"), listOf("h", "y", "n")),
                SystemKeyFactory.create(FuncType.SPACE, 1.0f, ArrayAction.Space),
            ),
            listOf(
                SystemKeyFactory.create(FuncType.SPACE, 1.0f, ArrayAction.Space),
                radicalKey("7", listOf("7-", "7^", "7v"), listOf("j", "u", "m")),
                radicalKey("8", listOf("8-", "8^", "8v"), listOf("k", "i", ",")),
                radicalKey("9", listOf("9-", "9^", "9v"), listOf("l", "o", ".")),
                SystemKeyFactory.create(FuncType.SPACE, 1.0f, ArrayAction.Space),
            ),
            listOf(
                FlickKeyData(
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
                    1.0f,
                    KeyBackgroundType.FUNCTIONAL
                ),
                SystemKeyFactory.create(FuncType.SPACE, 1.0f, ArrayAction.Space),
                radicalKey("0", listOf("0-", "0^", "0v"), listOf(";", "p", "/")),
                SystemKeyFactory.create(FuncType.SPACE, 1.0f, ArrayAction.Space),
                SystemKeyFactory.createEnterKey(imeOptions, 1.0f, ArrayAction.Enter)
            ),
        )
    }
}