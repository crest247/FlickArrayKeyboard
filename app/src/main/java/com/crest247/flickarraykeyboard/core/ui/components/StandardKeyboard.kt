package com.crest247.flickarraykeyboard.core.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.crest247.flickarraykeyboard.core.models.FlickKeyData
import com.crest247.flickarraykeyboard.core.models.KeyData
import com.crest247.flickarraykeyboard.core.models.RepeatableAction
import com.crest247.flickarraykeyboard.core.models.SpacerData
import com.crest247.flickarraykeyboard.core.models.TapKeyData
import com.crest247.flickarraykeyboard.core.models.TriggerOnDownAction
import com.crest247.flickarraykeyboard.core.models.VisibleKeyData
import com.crest247.flickarraykeyboard.core.theme.resolveColor

@Composable
fun StandardKeyboard(
    keyRows: List<List<KeyData>>,
    rowHeight: Dp,
    onKeyLongPress: ((KeyData, Int?) -> Unit)? = null,
    onKeyFlick: ((KeyData, Int) -> Unit)? = null,
    onKeyUp: ((KeyData) -> Unit)? = null,
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
                    key(keyData) {
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
                                                    } else null,
                                                    onLongPress = {
                                                        onKeyLongPress?.invoke(keyData, 0)
                                                    },
                                                    onUp = {
                                                        onKeyUp?.invoke(keyData)
                                                    }
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
                                                    },
                                                    onLongPress = {
                                                        onKeyLongPress?.invoke(keyData, 0)
                                                    },
                                                    onFlick = { direction ->
                                                        onKeyFlick?.invoke(keyData, direction)
                                                    },
                                                    onUp = {
                                                        onKeyUp?.invoke(keyData)
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
}