package com.crest247.flickarraykeyboard.modes.array30

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.crest247.flickarraykeyboard.core.theme.LocalKeyboardDimens
import com.crest247.flickarraykeyboard.modes.array30.Array30Module.processor

@Composable
fun Array30SpellingLayout(processor: Array30Processor) {
    val dimens = LocalKeyboardDimens.current

    if (processor.displayTokens.isNotEmpty()) {
        val displayText = processor.displayTokens.joinToString("")
        Text(
            text = displayText,
            fontSize = dimens.radicalTextSize,
            modifier = Modifier
                .height(dimens.radicalHeight)
                .padding(horizontal = dimens.keyPadding)
        )
    }
}