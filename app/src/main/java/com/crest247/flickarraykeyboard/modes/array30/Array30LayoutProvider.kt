package com.crest247.flickarraykeyboard.modes.array30

import com.crest247.flickarraykeyboard.core.models.FuncKeyData
import com.crest247.flickarraykeyboard.core.models.FuncType
import com.crest247.flickarraykeyboard.core.models.KeyData
import com.crest247.flickarraykeyboard.core.models.RadicalKeyData
import com.crest247.flickarraykeyboard.modes.shared.array.ArrayAction

object Array30LayoutProvider {
    fun createKeys(imeOptions: Int?): List<List<KeyData>> {
        return listOf(
            listOf(
                RadicalKeyData(
                    text = "1",
                    action = ArrayAction.Input(displayStr = "1", lookupStr = "1")
                ),
                RadicalKeyData(
                    text = "2",
                    action = ArrayAction.Input(displayStr = "2", lookupStr = "2")
                ),
                RadicalKeyData(
                    text = "3",
                    action = ArrayAction.Input(displayStr = "3", lookupStr = "3")
                ),
                RadicalKeyData(
                    text = "4",
                    action = ArrayAction.Input(displayStr = "4", lookupStr = "4")
                ),
                RadicalKeyData(
                    text = "5",
                    action = ArrayAction.Input(displayStr = "5", lookupStr = "5")
                ),
                RadicalKeyData(
                    text = "6",
                    action = ArrayAction.Input(displayStr = "6", lookupStr = "6")
                ),
                RadicalKeyData(
                    text = "7",
                    action = ArrayAction.Input(displayStr = "7", lookupStr = "7")
                ),
                RadicalKeyData(
                    text = "8",
                    action = ArrayAction.Input(displayStr = "8", lookupStr = "8")
                ),
                RadicalKeyData(
                    text = "9",
                    action = ArrayAction.Input(displayStr = "9", lookupStr = "9")
                ),
                RadicalKeyData(
                    text = "0",
                    action = ArrayAction.Input(displayStr = "0", lookupStr = "0")
                )
            ),
            listOf(
                RadicalKeyData(
                    text = "1^",
                    action = ArrayAction.Input(displayStr = "1^", lookupStr = "q")
                ),
                RadicalKeyData(
                    text = "2^",
                    action = ArrayAction.Input(displayStr = "2^", lookupStr = "w")
                ),
                RadicalKeyData(
                    text = "3^",
                    action = ArrayAction.Input(displayStr = "3^", lookupStr = "e")
                ),
                RadicalKeyData(
                    text = "4^",
                    action = ArrayAction.Input(displayStr = "4^", lookupStr = "r")
                ),
                RadicalKeyData(
                    text = "5^",
                    action = ArrayAction.Input(displayStr = "5^", lookupStr = "t")
                ),
                RadicalKeyData(
                    text = "6^",
                    action = ArrayAction.Input(displayStr = "6^", lookupStr = "y")
                ),
                RadicalKeyData(
                    text = "7^",
                    action = ArrayAction.Input(displayStr = "7^", lookupStr = "u")
                ),
                RadicalKeyData(
                    text = "8^",
                    action = ArrayAction.Input(displayStr = "8^", lookupStr = "i")
                ),
                RadicalKeyData(
                    text = "9^",
                    action = ArrayAction.Input(displayStr = "9^", lookupStr = "o")
                ),
                RadicalKeyData(
                    text = "0^",
                    action = ArrayAction.Input(displayStr = "0^", lookupStr = "p")
                )
            ),
            listOf(
                RadicalKeyData(
                    text = "1-",
                    action = ArrayAction.Input(displayStr = "1-", lookupStr = "a")
                ),
                RadicalKeyData(
                    text = "2-",
                    action = ArrayAction.Input(displayStr = "2-", lookupStr = "s")
                ),
                RadicalKeyData(
                    text = "3-",
                    action = ArrayAction.Input(displayStr = "3-", lookupStr = "d")
                ),
                RadicalKeyData(
                    text = "4-",
                    action = ArrayAction.Input(displayStr = "4-", lookupStr = "f")
                ),
                RadicalKeyData(
                    text = "5-",
                    action = ArrayAction.Input(displayStr = "5-", lookupStr = "g")
                ),
                RadicalKeyData(
                    text = "6-",
                    action = ArrayAction.Input(displayStr = "6-", lookupStr = "h")
                ),
                RadicalKeyData(
                    text = "7-",
                    action = ArrayAction.Input(displayStr = "7-", lookupStr = "j")
                ),
                RadicalKeyData(
                    text = "8-",
                    action = ArrayAction.Input(displayStr = "8-", lookupStr = "k")
                ),
                RadicalKeyData(
                    text = "9-",
                    action = ArrayAction.Input(displayStr = "9-", lookupStr = "l")
                ),
                RadicalKeyData(
                    text = "0-",
                    action = ArrayAction.Input(displayStr = "0-", lookupStr = ";")
                )
            ),
            listOf(
                RadicalKeyData(
                    text = "1v",
                    action = ArrayAction.Input(displayStr = "1v", lookupStr = "z")
                ),
                RadicalKeyData(
                    text = "2v",
                    action = ArrayAction.Input(displayStr = "2v", lookupStr = "x")
                ),
                RadicalKeyData(
                    text = "3v",
                    action = ArrayAction.Input(displayStr = "3v", lookupStr = "c")
                ),
                RadicalKeyData(
                    text = "4v",
                    action = ArrayAction.Input(displayStr = "4v", lookupStr = "v")
                ),
                RadicalKeyData(
                    text = "5v",
                    action = ArrayAction.Input(displayStr = "5v", lookupStr = "b")
                ),
                RadicalKeyData(
                    text = "6v",
                    action = ArrayAction.Input(displayStr = "6v", lookupStr = "n")
                ),
                RadicalKeyData(
                    text = "7v",
                    action = ArrayAction.Input(displayStr = "7v", lookupStr = "m")
                ),
                RadicalKeyData(
                    text = "8v",
                    action = ArrayAction.Input(displayStr = "8v", lookupStr = ",")
                ),
                RadicalKeyData(
                    text = "9v",
                    action = ArrayAction.Input(displayStr = "9v", lookupStr = ".")
                ),
                RadicalKeyData(
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