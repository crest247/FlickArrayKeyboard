package com.crest247.flickarraykeyboard.modes.array30

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.crest247.flickarraykeyboard.core.theme.LocalKeyboardDimens

@Composable
fun Array30SuggestionLayout(processor: Array30Processor) {
    val dimens = LocalKeyboardDimens.current

    if (Array30Module.processor.candidates.isNotEmpty()) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimens.candidateHeight),
            contentPadding = PaddingValues(horizontal = dimens.candidateHorizontalPadding)
        ) {
            items(Array30Module.processor.candidates) { word ->
                Text(
                    text = word,
                    fontSize = dimens.candidateTextSize,
                    modifier = Modifier
                        .clickable { Array30Module.processor.commitCandidate(word) }
                        .padding(
                            horizontal = dimens.candidateHorizontalPadding,
                            vertical = dimens.candidateVerticalPadding
                        )
                )
            }
        }
    }
}