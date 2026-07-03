package com.crest247.flickarraykeyboard.core.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.crest247.flickarraykeyboard.core.RepeatableAction
import com.crest247.flickarraykeyboard.core.TriggerOnDownAction
import com.crest247.flickarraykeyboard.core.models.FlickKeyData
import com.crest247.flickarraykeyboard.core.models.KeyData
import com.crest247.flickarraykeyboard.core.models.SpacerData
import com.crest247.flickarraykeyboard.core.models.TapKeyData
import com.crest247.flickarraykeyboard.core.models.VisibleKeyData
import com.crest247.flickarraykeyboard.core.theme.resolveColor

@Composable
fun StandardKeyboard(
    keyRows: List<List<KeyData>>,
    rowHeight: Dp,
    onKeyAction: (KeyData, flickDirection: Int?) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        keyRows.forEach { rowKeys ->
            Row(modifier = Modifier.keyboardRowStyle(rowHeight)) {
                rowKeys.forEach { keyData ->
                    when (keyData) {
                        is SpacerData -> {
                            Spacer(modifier = Modifier.weight(keyData.weight))
                        }

                        is VisibleKeyData<*> -> {
                            val bgColor = keyData.backgroundType.resolveColor()

                            when (keyData) {
                                is TapKeyData<*> -> {
                                    val isRepeatable = keyData.action is RepeatableAction
                                    val useDown = keyData.action is TriggerOnDownAction
                                    KeyButton(
                                        content = keyData.content,
                                        backgroundColor = bgColor,
                                        modifier = Modifier
                                            .weight(keyData.weight)
                                            .tapWithPreview(
                                                keyId = keyData.content,
                                                content = keyData.content,
                                                backgroundType = keyData.backgroundType,
                                                onClick = { onKeyAction(keyData, null) },
                                                onRepeat = if (isRepeatable) {
                                                    { onKeyAction(keyData, null) }
                                                } else null,
                                                onDown = if (useDown) {
                                                    { onKeyAction(keyData, null) }
                                                } else null
                                            )
                                    )
                                }

                                is FlickKeyData<*> -> {
                                    KeyButton(
                                        content = keyData.content,
                                        backgroundColor = bgColor,
                                        modifier = Modifier
                                            .weight(keyData.weight)
                                            .flickWithPreview(
                                                keyData.content,
                                                keyData.popupContents,
                                                keyData.backgroundType,
                                                onClick = { direction ->
                                                    onKeyAction(
                                                        keyData,
                                                        direction
                                                    )
                                                }
                                            )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}