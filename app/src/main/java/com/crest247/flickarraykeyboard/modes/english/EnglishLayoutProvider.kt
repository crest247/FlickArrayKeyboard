package com.crest247.flickarraykeyboard.modes.english

import com.crest247.flickarraykeyboard.core.models.CharKeyData
import com.crest247.flickarraykeyboard.core.models.FuncKeyData
import com.crest247.flickarraykeyboard.core.models.FuncType
import com.crest247.flickarraykeyboard.core.models.KeyData
import com.crest247.flickarraykeyboard.core.models.SpacerData

enum class EnglishVariant { Default, Email, Url }

object EnglishLayoutProvider {

    fun createKeys(shiftState: ShiftState, variant: EnglishVariant, imeOptions: Int?): List<List<KeyData>> {
        val isUpper = shiftState != ShiftState.LOWERCASE
        fun char(lower: String, upper: String) = CharKeyData(if (isUpper) upper else lower, 1.0f)

        val variantKey = when (variant) {
            EnglishVariant.Email -> char("@", "@")
            EnglishVariant.Url -> char("/", "/")
            EnglishVariant.Default -> char(",", "<")
        }

        return listOf(
            listOf(
                char("1", "!"), char("2", "@"), char("3", "#"),
                char("4", "$"), char("5", "%"), char("6", "^"),
                char("7", "&"), char("8", "*"), char("9", "("),
                char("0", ")")
            ),
            listOf(
                char("q", "Q"), char("w", "W"), char("e", "E"),
                char("r", "R"), char("t", "T"), char("y", "Y"),
                char("u", "U"), char("i", "I"), char("o", "O"),
                char("p", "P")
            ),
            listOf(
                SpacerData(0.5f),
                char("a", "A"), char("s", "S"), char("d", "D"),
                char("f", "F"), char("g", "G"), char("h", "H"),
                char("j", "J"), char("k", "K"), char("l", "L"),
                SpacerData(0.5f)
            ),
            listOf(
                FuncKeyData.create(FuncType.SHIFT, 1.5f),
                char("z", "Z"), char("x", "X"), char("c", "C"),
                char("v", "V"), char("b", "B"), char("n", "N"),
                char("m", "M"),
                FuncKeyData.create(FuncType.BACKSPACE, 1.5f)
            ),
            listOf(
                FuncKeyData.create(FuncType.LANGUAGE, 1.5f),
                variantKey,
                FuncKeyData.create(FuncType.SPACE, 5.0f),
                char(".", ">"),
                FuncKeyData.createEnterKey(imeOptions, 1.5f)
            )
        )
    }
}