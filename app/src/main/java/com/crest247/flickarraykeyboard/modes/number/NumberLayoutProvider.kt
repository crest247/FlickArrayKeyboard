package com.crest247.flickarraykeyboard.modes.number

import com.crest247.flickarraykeyboard.core.models.CharKeyData
import com.crest247.flickarraykeyboard.core.models.FlickKeyData
import com.crest247.flickarraykeyboard.core.models.FuncKeyData
import com.crest247.flickarraykeyboard.core.models.FuncType
import com.crest247.flickarraykeyboard.core.models.KeyData
import com.crest247.flickarraykeyboard.core.ui.components.KeyContent

object NumberLayoutProvider {

    fun createKeys(imeOptions: Int?): List<List<KeyData>> {
        return listOf(
            listOf(
                FuncKeyData.create(FuncType.TAB, 1.0f),
                CharKeyData("1", 1.0f),
                CharKeyData("2", 1.0f),
                CharKeyData("3", 1.0f),
                FuncKeyData.create(FuncType.BACKSPACE, 1.0f),
            ),
            listOf(
                FlickKeyData(
                    ".",
                    listOf(
                        KeyContent.Text("."),
                        KeyContent.Text(","),
                        KeyContent.Text(":"),
                        KeyContent.Text("_"),
                        KeyContent.Text("!"),
                        KeyContent.Text("?")
                    ),
                    1.0f
                ),
                CharKeyData("4", 1.0f),
                CharKeyData("5", 1.0f),
                CharKeyData("6", 1.0f),
                FlickKeyData(
                    "(",
                    listOf(
                        KeyContent.Text("("),
                        KeyContent.Text(")"),
                        KeyContent.Text("["),
                        KeyContent.Text("]"),
                        KeyContent.Text(">"),
                        KeyContent.Text("<"),
                        KeyContent.Text("{"),
                        KeyContent.Text("}"),
                    ),
                    1.0f
                ),
            ),
            listOf(
                FuncKeyData.create(FuncType.SPACE, 1.0f),
                CharKeyData("7", 1.0f),
                CharKeyData("8", 1.0f),
                CharKeyData("9", 1.0f),
                FuncKeyData.create(FuncType.SPACE, 1.0f),
            ),
            listOf(
                FuncKeyData.create(FuncType.LANGUAGE, 1.0f),
                FlickKeyData(
                    "*",
                    listOf(
                        KeyContent.Text("*"),
                        KeyContent.Text("+"),
                        KeyContent.Text("-"),
                        KeyContent.Text("/"),
                        KeyContent.Text("="),
                        KeyContent.Text("×"),
                        KeyContent.Text("÷")
                    ),
                    1.0f
                ),
                CharKeyData("0", 1.0f),
                FlickKeyData(
                    "#",
                    listOf(
                        KeyContent.Text("#"),
                        KeyContent.Text("$"),
                        KeyContent.Text("%"),
                        KeyContent.Text("^"),
                        KeyContent.Text("~"),
                        KeyContent.Text("&")
                    ),
                    1.0f
                ),
                FuncKeyData.create(FuncType.ENTER, 1.0f)
            )
        )
    }
}