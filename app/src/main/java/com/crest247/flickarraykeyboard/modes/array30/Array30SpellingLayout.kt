package com.crest247.flickarraykeyboard.modes.array30

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.crest247.flickarraykeyboard.core.theme.LocalKeyboardColors
import com.crest247.flickarraykeyboard.core.theme.LocalKeyboardDimens
import com.crest247.flickarraykeyboard.modes.array30.Array30Module.processor

@Composable
fun Array30SpellingLayout(processor: Array30Processor) {
    val dimens = LocalKeyboardDimens.current
    val colors = LocalKeyboardColors.current

    if (processor.displayTokens.isNotEmpty()) {
        val displayText = processor.displayTokens.joinToString("")

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(dimens.radicalHeight)
                .wrapContentWidth()
                .clip(
                    RoundedCornerShape(
                        topStart = dimens.keyRadius,
                        topEnd = dimens.keyRadius,
                        bottomEnd = 0.dp,
                        bottomStart = 0.dp
                    )
                )
                .background(colors.radicalPanelBackground)
                .padding(horizontal = dimens.radicalPadding)
        ) {
            Text(
                text = displayText,
                fontSize = dimens.radicalTextSize,
                color = colors.radicalText
            )
        }
    }
}