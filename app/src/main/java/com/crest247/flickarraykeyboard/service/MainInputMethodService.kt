package com.crest247.flickarraykeyboard.service

import android.graphics.Rect
import android.graphics.Region
import android.view.inputmethod.EditorInfo
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.crest247.flickarraykeyboard.core.KeyboardState
import com.crest247.flickarraykeyboard.core.LocalKeyboardState
import com.crest247.flickarraykeyboard.core.theme.KeyboardTheme
import com.crest247.flickarraykeyboard.core.ui.areas.MainKeyboardContainer
import com.crest247.flickarraykeyboard.core.ui.components.LocalPreviewHandler
import com.crest247.flickarraykeyboard.core.ui.components.PreviewHandler
import com.crest247.flickarraykeyboard.modes.shared.array.ArrayDecoder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainInputMethodService : ComposedInputMethodService() {
    private lateinit var keyboardState: KeyboardState
    private lateinit var composeView: ComposeView
    private var keyboardBounds: Rect? = null
    private var radicalBounds: Rect? = null

    init {
        CoroutineScope(Dispatchers.Main).launch {
            ArrayDecoder.loadData(this@MainInputMethodService)
        }
    }

    override fun onCreate() {
        super.onCreate()
        keyboardState = KeyboardState()
    }

    override fun onCreateInputComposeView(): AbstractComposeView {
        composeView = ComposeView(this).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        }
        composeView.setContent {
            val previewHandler = remember { PreviewHandler() }
            CompositionLocalProvider(
                LocalPreviewHandler provides previewHandler,
                LocalKeyboardState provides keyboardState
            ) {
                KeyboardTheme {
                    MainKeyboardContainer(
                        state = keyboardState,
                        onKeyboardLayoutChanged = { updateKeyboardLayout(it) },
                        onRadicalLayoutChanged = { updateRadicalLayout(it) }
                    )
                }
            }
        }

        return composeView
    }

    override fun onStartInputView(info: EditorInfo, restarting: Boolean) {
        super.onStartInputView(info, restarting)

        val inputConnection = currentInputConnection

        if (inputConnection != null) {
            keyboardState.updateConnection(inputConnection, info)
        }
    }

    private fun updateKeyboardLayout(coordinates: LayoutCoordinates) {
        val position = coordinates.positionInWindow()
        val size = coordinates.size
        val newBounds = Rect(
            position.x.toInt(),
            position.y.toInt(),
            (position.x + size.width).toInt(),
            (position.y + size.height).toInt()
        )
        if (keyboardBounds != newBounds) {
            keyboardBounds = newBounds
            window?.window?.decorView?.requestLayout()
        }
    }

    private fun updateRadicalLayout(coordinates: LayoutCoordinates?) {
        val newBounds = coordinates?.let {
            val position = it.positionInWindow()
            val size = it.size
            Rect(
                position.x.toInt(),
                position.y.toInt(),
                (position.x + size.width).toInt(),
                (position.y + size.height).toInt()
            )
        }
        if (radicalBounds != newBounds) {
            radicalBounds = newBounds
            window?.window?.decorView?.requestLayout()
        }
    }

    override fun onComputeInsets(outInsets: Insets) {
        super.onComputeInsets(outInsets)
        val bounds = keyboardBounds ?: return

        outInsets.touchableInsets = Insets.TOUCHABLE_INSETS_REGION
        outInsets.touchableRegion.set(bounds)

        radicalBounds?.let {
            outInsets.touchableRegion.op(it, Region.Op.UNION)
        }

        outInsets.contentTopInsets = bounds.top
        outInsets.visibleTopInsets = bounds.top
    }

    override fun onDestroy() {
        super.onDestroy()
        if(this::composeView.isInitialized) {
            composeView.disposeComposition()
        }
    }
}
