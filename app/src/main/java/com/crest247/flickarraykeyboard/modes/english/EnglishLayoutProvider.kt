package com.crest247.flickarraykeyboard.modes.english

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Language
import com.crest247.flickarraykeyboard.core.engine.SystemAction
import com.crest247.flickarraykeyboard.core.models.FlickKeyData
import com.crest247.flickarraykeyboard.core.models.FuncKeyData
import com.crest247.flickarraykeyboard.core.models.FuncType
import com.crest247.flickarraykeyboard.core.models.KeyBackgroundType
import com.crest247.flickarraykeyboard.core.models.KeyData
import com.crest247.flickarraykeyboard.core.models.SpacerData
import com.crest247.flickarraykeyboard.core.models.TapKeyData
import com.crest247.flickarraykeyboard.core.ui.components.KeyContent

enum class EnglishVariant { Default, Email, Url }

object EnglishLayoutProvider {

    fun createKeys(
        shiftState: ShiftState,
        variant: EnglishVariant,
        imeOptions: Int?
    ): List<List<KeyData>> {
        val isUpper = shiftState != ShiftState.LOWERCASE
        fun charKey(lower: String, upper: String): TapKeyData<EnglishAction> {
            val text = if (isUpper) upper else lower
            return TapKeyData(
                text = text,
                action = EnglishAction.InputChar(text)
            )
        }

        val variantKey = when (variant) {
            EnglishVariant.Email -> charKey("@", "@")
            EnglishVariant.Url -> charKey("/", "/")
            EnglishVariant.Default -> charKey(",", "<")
        }

        return listOf(
            listOf(
                charKey("1", "!"), charKey("2", "@"), charKey("3", "#"),
                charKey("4", "$"), charKey("5", "%"), charKey("6", "^"),
                charKey("7", "&"), charKey("8", "*"), charKey("9", "("),
                charKey("0", ")")
            ),
            listOf(
                charKey("q", "Q"), charKey("w", "W"), charKey("e", "E"),
                charKey("r", "R"), charKey("t", "T"), charKey("y", "Y"),
                charKey("u", "U"), charKey("i", "I"), charKey("o", "O"),
                charKey("p", "P")
            ),
            listOf(
                SpacerData(0.5f),
                charKey("a", "A"), charKey("s", "S"), charKey("d", "D"),
                charKey("f", "F"), charKey("g", "G"), charKey("h", "H"),
                charKey("j", "J"), charKey("k", "K"), charKey("l", "L"),
                SpacerData(0.5f)
            ),
            listOf(
                FuncKeyData.create(FuncType.SHIFT, 1.5f, EnglishAction.ToggleShift),
                charKey("z", "Z"), charKey("x", "X"), charKey("c", "C"),
                charKey("v", "V"), charKey("b", "B"), charKey("n", "N"),
                charKey("m", "M"),
                FuncKeyData.create(FuncType.BACKSPACE, 1.5f)
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
                variantKey,
                FuncKeyData.create(FuncType.SPACE, 5.0f),
                charKey(".", ">"),
                FuncKeyData.createEnterKey(imeOptions, 1.5f)
            )
        )
    }
}