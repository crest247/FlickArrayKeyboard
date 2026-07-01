package com.crest247.flickarraykeyboard.modes.array30

import com.crest247.flickarraykeyboard.core.models.FuncKeyData
import com.crest247.flickarraykeyboard.core.models.FuncType
import com.crest247.flickarraykeyboard.core.models.KeyData
import com.crest247.flickarraykeyboard.core.models.TapKeyData
import com.crest247.flickarraykeyboard.modes.shared.array.ArrayAction

object Array30LayoutProvider {
    fun createKeys(imeOptions: Int?): List<List<KeyData>> {
        return listOf(
            listOf(
                TapKeyData<ArrayAction>(
                    text = "1",
                    action = ArrayAction.Input(displayStr = "1", lookupStr = "1")
                ),
                TapKeyData<ArrayAction>(
                    text = "2",
                    action = ArrayAction.Input(displayStr = "2", lookupStr = "2")
                ),
                TapKeyData<ArrayAction>(
                    text = "3",
                    action = ArrayAction.Input(displayStr = "3", lookupStr = "3")
                ),
                TapKeyData<ArrayAction>(
                    text = "4",
                    action = ArrayAction.Input(displayStr = "4", lookupStr = "4")
                ),
                TapKeyData<ArrayAction>(
                    text = "5",
                    action = ArrayAction.Input(displayStr = "5", lookupStr = "5")
                ),
                TapKeyData<ArrayAction>(
                    text = "6",
                    action = ArrayAction.Input(displayStr = "6", lookupStr = "6")
                ),
                TapKeyData<ArrayAction>(
                    text = "7",
                    action = ArrayAction.Input(displayStr = "7", lookupStr = "7")
                ),
                TapKeyData<ArrayAction>(
                    text = "8",
                    action = ArrayAction.Input(displayStr = "8", lookupStr = "8")
                ),
                TapKeyData<ArrayAction>(
                    text = "9",
                    action = ArrayAction.Input(displayStr = "9", lookupStr = "9")
                ),
                TapKeyData<ArrayAction>(
                    text = "0",
                    action = ArrayAction.Input(displayStr = "0", lookupStr = "0")
                )
            ),
            listOf(
                TapKeyData<ArrayAction>(
                    text = "1^",
                    action = ArrayAction.Input(displayStr = "1^", lookupStr = "q")
                ),
                TapKeyData<ArrayAction>(
                    text = "2^",
                    action = ArrayAction.Input(displayStr = "2^", lookupStr = "w")
                ),
                TapKeyData<ArrayAction>(
                    text = "3^",
                    action = ArrayAction.Input(displayStr = "3^", lookupStr = "e")
                ),
                TapKeyData<ArrayAction>(
                    text = "4^",
                    action = ArrayAction.Input(displayStr = "4^", lookupStr = "r")
                ),
                TapKeyData<ArrayAction>(
                    text = "5^",
                    action = ArrayAction.Input(displayStr = "5^", lookupStr = "t")
                ),
                TapKeyData<ArrayAction>(
                    text = "6^",
                    action = ArrayAction.Input(displayStr = "6^", lookupStr = "y")
                ),
                TapKeyData<ArrayAction>(
                    text = "7^",
                    action = ArrayAction.Input(displayStr = "7^", lookupStr = "u")
                ),
                TapKeyData<ArrayAction>(
                    text = "8^",
                    action = ArrayAction.Input(displayStr = "8^", lookupStr = "i")
                ),
                TapKeyData<ArrayAction>(
                    text = "9^",
                    action = ArrayAction.Input(displayStr = "9^", lookupStr = "o")
                ),
                TapKeyData<ArrayAction>(
                    text = "0^",
                    action = ArrayAction.Input(displayStr = "0^", lookupStr = "p")
                )
            ),
            listOf(
                TapKeyData<ArrayAction>(
                    text = "1-",
                    action = ArrayAction.Input(displayStr = "1-", lookupStr = "a")
                ),
                TapKeyData<ArrayAction>(
                    text = "2-",
                    action = ArrayAction.Input(displayStr = "2-", lookupStr = "s")
                ),
                TapKeyData<ArrayAction>(
                    text = "3-",
                    action = ArrayAction.Input(displayStr = "3-", lookupStr = "d")
                ),
                TapKeyData<ArrayAction>(
                    text = "4-",
                    action = ArrayAction.Input(displayStr = "4-", lookupStr = "f")
                ),
                TapKeyData<ArrayAction>(
                    text = "5-",
                    action = ArrayAction.Input(displayStr = "5-", lookupStr = "g")
                ),
                TapKeyData<ArrayAction>(
                    text = "6-",
                    action = ArrayAction.Input(displayStr = "6-", lookupStr = "h")
                ),
                TapKeyData<ArrayAction>(
                    text = "7-",
                    action = ArrayAction.Input(displayStr = "7-", lookupStr = "j")
                ),
                TapKeyData<ArrayAction>(
                    text = "8-",
                    action = ArrayAction.Input(displayStr = "8-", lookupStr = "k")
                ),
                TapKeyData<ArrayAction>(
                    text = "9-",
                    action = ArrayAction.Input(displayStr = "9-", lookupStr = "l")
                ),
                TapKeyData<ArrayAction>(
                    text = "0-",
                    action = ArrayAction.Input(displayStr = "0-", lookupStr = ";")
                )
            ),
            listOf(
                TapKeyData<ArrayAction>(
                    text = "1v",
                    action = ArrayAction.Input(displayStr = "1v", lookupStr = "z")
                ),
                TapKeyData<ArrayAction>(
                    text = "2v",
                    action = ArrayAction.Input(displayStr = "2v", lookupStr = "x")
                ),
                TapKeyData<ArrayAction>(
                    text = "3v",
                    action = ArrayAction.Input(displayStr = "3v", lookupStr = "c")
                ),
                TapKeyData<ArrayAction>(
                    text = "4v",
                    action = ArrayAction.Input(displayStr = "4v", lookupStr = "v")
                ),
                TapKeyData<ArrayAction>(
                    text = "5v",
                    action = ArrayAction.Input(displayStr = "5v", lookupStr = "b")
                ),
                TapKeyData<ArrayAction>(
                    text = "6v",
                    action = ArrayAction.Input(displayStr = "6v", lookupStr = "n")
                ),
                TapKeyData<ArrayAction>(
                    text = "7v",
                    action = ArrayAction.Input(displayStr = "7v", lookupStr = "m")
                ),
                TapKeyData<ArrayAction>(
                    text = "8v",
                    action = ArrayAction.Input(displayStr = "8v", lookupStr = ",")
                ),
                TapKeyData<ArrayAction>(
                    text = "9v",
                    action = ArrayAction.Input(displayStr = "9v", lookupStr = ".")
                ),
                TapKeyData<ArrayAction>(
                    text = "0v",
                    action = ArrayAction.Input(displayStr = "0v", lookupStr = "/")
                )
            ),
            listOf(
                FuncKeyData.create(FuncType.LANGUAGE, 3.0f),
                FuncKeyData.create(FuncType.SPACE, 4.0f),
                FuncKeyData.create(FuncType.BACKSPACE, 1.5f),
                FuncKeyData.createEnterKey(imeOptions, 1.5f)
            )
        )
    }
}