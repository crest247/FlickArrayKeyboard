package com.crest247.flickarraykeyboard.core.ui.areas

import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.LayoutCoordinates
import com.crest247.flickarraykeyboard.core.KeyboardState

@Composable
fun SpellingAreaRouter(
    state: KeyboardState,
    onRadicalLayoutChanged: (LayoutCoordinates?) -> Unit
) {
//    when (state.currentMode) {
//        KeyboardMode.Chinese -> {
//            // 從中文 Processor 讀取專屬狀態
//            val text = state.chineseProcessor.composingText
//
//            if (text.isNotEmpty()) {
//                // 有字根：畫出畫面，並回報座標攔截觸控
//                ChineseSpellingArea(
//                    text = text,
//                    modifier = Modifier
//                        .wrapContentWidth()
//                        .onGloballyPositioned { onRadicalLayoutChanged(it) }
//                )
//            } else {
//                // 沒有字根：清空座標
//                DisposableEffect(Unit) { onDispose { onRadicalLayoutChanged(null) } }
//            }
//        }
//        else -> {
//            // 其他語言 (如英文、符號)：一律沒有字根列，清空座標
//            DisposableEffect(Unit) { onDispose { onRadicalLayoutChanged(null) } }
//        }
//    }
}