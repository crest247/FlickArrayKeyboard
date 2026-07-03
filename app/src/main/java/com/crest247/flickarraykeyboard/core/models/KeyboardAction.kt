package com.crest247.flickarraykeyboard.core.models

interface KeyboardAction
interface RepeatableAction : KeyboardAction
interface TriggerOnDownAction : KeyboardAction
interface LongPressAction : KeyboardAction {
    val longPressAction: KeyboardAction?
}