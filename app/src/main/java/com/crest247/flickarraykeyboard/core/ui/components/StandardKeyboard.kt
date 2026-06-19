package com.crest247.flickarraykeyboard.core.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.crest247.flickarraykeyboard.core.models.CharKeyData
import com.crest247.flickarraykeyboard.core.models.FuncKeyData
import com.crest247.flickarraykeyboard.core.models.KeyData
import com.crest247.flickarraykeyboard.core.models.SpacerData
import com.crest247.flickarraykeyboard.core.models.VisibleKeyData
import com.crest247.flickarraykeyboard.core.theme.resolveColor

@Composable
fun StandardKeyboard(
    keyRows: List<List<KeyData>>,
    onKeyAction: (KeyData, flickDirection: Int?) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        keyRows.forEach { rowKeys ->
            Row(modifier = Modifier.keyboardRowStyle()) {
                rowKeys.forEach { keyData ->
                    when (keyData) {
                        is SpacerData -> {
                            Spacer(modifier = Modifier.weight(keyData.weight))
                        }

                        is VisibleKeyData -> {
                            val bgColor = keyData.backgroundType.resolveColor()

                            when (keyData) {
                                is CharKeyData -> {
                                    KeyButton(
                                        content = KeyContent.Text(keyData.text),
                                        backgroundColor = bgColor,
                                        modifier = Modifier
                                            .weight(keyData.weight)
                                            .tapWithPreview(
                                                keyId = keyData.text,
                                                content = KeyContent.Text(keyData.text),
                                                backgroundType = keyData.backgroundType,
                                                onClick = { onKeyAction(keyData, null) }
                                            )
                                    )
                                }

                                is FuncKeyData -> {
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
                                                onRepeat = if (keyData.type.isRepeatable) { { onKeyAction(keyData, null) } } else null,
                                                onDown = if (keyData.type.useDown) { { onKeyAction(keyData, null) } } else null,
                                            )
                                    )
                                }

                                else -> {}
                            }
                        }
                    }
                }
            }
        }
    }
}