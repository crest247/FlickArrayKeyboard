package com.crest247.flickarraykeyboard.modes.arrayFlick

import android.view.KeyEvent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ReadMore
import androidx.compose.material.icons.automirrored.outlined.Redo
import androidx.compose.material.icons.automirrored.outlined.Undo
import androidx.compose.material.icons.outlined.ContentCopy
import androidx.compose.material.icons.outlined.ContentPaste
import androidx.compose.material.icons.outlined.ControlCamera
import androidx.compose.material.icons.outlined.KeyboardControlKey
import androidx.compose.material.icons.outlined.NoteAlt
import androidx.compose.material.icons.outlined.SelectAll
import com.crest247.flickarraykeyboard.core.models.FlickKeyData
import com.crest247.flickarraykeyboard.core.models.FuncType
import com.crest247.flickarraykeyboard.core.models.KeyBackgroundType
import com.crest247.flickarraykeyboard.core.models.KeyData
import com.crest247.flickarraykeyboard.core.models.SystemKeyFactory
import com.crest247.flickarraykeyboard.core.ui.components.KeyContent
import com.crest247.flickarraykeyboard.core.ui.components.KeyContent.Icon
import com.crest247.flickarraykeyboard.modes.shared.array.ArrayAction

object ArrayFlickLayoutProvider {
    fun createKeys(
        imeOptions: Int?,
        metaState: Int
    ): List<List<KeyData>> {
        fun radicalKey(
            centerText: String,
            displayTexts: List<String>,
            lookupTexts: List<String>
        ): FlickKeyData<*> {
            return FlickKeyData(
                KeyContent.Text(centerText),
                List(displayTexts.size) { KeyContent.Text(displayTexts[it]) },
                List(displayTexts.size) {
                    it to ArrayFlickAction.InputRadical(
                        displayTexts[it], lookupTexts[it],
                        ArrayAction.InputRadical(centerText, centerText)
                    )
                }.toMap()
            )
        }

        val modifierKey = FlickKeyData(
            content = Icon(Icons.AutoMirrored.Outlined.ReadMore),
            popupContents = listOf(
                null,
                KeyContent.Text("⇧"),
                Icon(Icons.Outlined.KeyboardControlKey)
            ),
            directionActions = mapOf(0 to ArrayFlickAction.PhysicalModifier),
            weight = 1.0f,
            backgroundType = KeyBackgroundType.FUNCTIONAL
        )

        val dpadKey = if (metaState == KeyEvent.KEYCODE_CTRL_LEFT) {
            FlickKeyData(
                content = Icon(Icons.Outlined.NoteAlt),
                popupContents = listOf(
                    null,
                    Icon(Icons.Outlined.SelectAll),
                    Icon(Icons.Outlined.ContentPaste),
                    Icon(Icons.AutoMirrored.Outlined.Redo),
                    Icon(Icons.AutoMirrored.Outlined.Undo),
                    Icon(Icons.Outlined.ContentCopy)
                ),
                directionActions = mapOf(
                    0 to ArrayFlickAction.DirectionalPad,
                    1 to ArrayFlickAction.SendRawKey(KeyEvent.KEYCODE_A, KeyEvent.META_CTRL_ON),
                    2 to ArrayFlickAction.SendRawKey(KeyEvent.KEYCODE_V, KeyEvent.META_CTRL_ON),
                    3 to ArrayFlickAction.SendRawKey(KeyEvent.KEYCODE_Y, KeyEvent.META_CTRL_ON),
                    4 to ArrayFlickAction.SendRawKey(KeyEvent.KEYCODE_Z, KeyEvent.META_CTRL_ON),
                    5 to ArrayFlickAction.SendRawKey(KeyEvent.KEYCODE_C, KeyEvent.META_CTRL_ON)
                ),
                weight = 1.0f,
                backgroundType = KeyBackgroundType.FUNCTIONAL
            )
        } else {
            FlickKeyData(
                content = Icon(Icons.Outlined.ControlCamera),
                popupContents = listOf(
                    null,
                    KeyContent.Text("↑"),
                    KeyContent.Text("→"),
                    KeyContent.Text("↓"),
                    KeyContent.Text("←")
                ),
                directionActions = mapOf(
                    0 to ArrayFlickAction.DirectionalPad,
                    1 to ArrayFlickAction.SendRawKey(KeyEvent.KEYCODE_DPAD_UP, 0),
                    2 to ArrayFlickAction.SendRawKey(KeyEvent.KEYCODE_DPAD_RIGHT, 0),
                    3 to ArrayFlickAction.SendRawKey(KeyEvent.KEYCODE_DPAD_DOWN, 0),
                    4 to ArrayFlickAction.SendRawKey(KeyEvent.KEYCODE_DPAD_LEFT, 0)
                ),
                weight = 1.0f,
                backgroundType = KeyBackgroundType.FUNCTIONAL
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
                SystemKeyFactory.create(FuncType.LANGUAGE, 1.0f),
                modifierKey,
                radicalKey("0", listOf("0-", "0^", "0v"), listOf(";", "p", "/")),
                dpadKey,
                SystemKeyFactory.createEnterKey(imeOptions, 1.0f, ArrayAction.Enter)
            ),
        )
    }
}