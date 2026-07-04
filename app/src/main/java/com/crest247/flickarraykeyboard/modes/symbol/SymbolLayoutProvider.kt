package com.crest247.flickarraykeyboard.modes.symbol

import com.crest247.flickarraykeyboard.core.models.FuncType
import com.crest247.flickarraykeyboard.core.models.KeyBackgroundType
import com.crest247.flickarraykeyboard.core.models.KeyData
import com.crest247.flickarraykeyboard.core.models.SystemKeyFactory
import com.crest247.flickarraykeyboard.core.models.TapKeyData
import com.crest247.flickarraykeyboard.core.ui.components.KeyContent

object SymbolLayoutProvider {

    val categories = listOf("&", "⇒", "±", "½", "δ", "Δ")

    private val symbolGridData = mapOf(
        0 to listOf(
            listOf("!", "@", "#", "$", "%", "^", "&", "*", "(", ")"),
            listOf("`", "~", "?", ":", "\"", "|", "_", "+", "{", "}"),
            listOf(",", ".", "/", ";", "\'", "\\", "-", "=", "[", "]"),
            listOf("°", "ϵ", "€", "£", "¥", "•", "×", "÷", "<", ">")
        ),
        1 to listOf(
            listOf("←", "↑", "→", "↓", "↔", "↕", "⇄", "⇆", "⇅", "⇵"),
            listOf("↖", "↗", "↘", "↙", "⤡", "⤢", "⇉", "⇇", "⇈", "⇊"),
            listOf("⇐", "⇑", "⇒", "⇓", "⇔", "⇕", "↺", "↻", "⟲", "⟳"),
            listOf("⇖", "⇗", "⇘", "⇙", "↴", "↵", "↶", "↷", "⇋", "⇌")
        ),
        2 to listOf(
            listOf("∠", "±", "∓", "≤", "≥", "≠", "≒", "≟", "≅", "≈"),
            listOf("∵", "∴", "√", "∛", "∞", "∇", "∂", "∫", "∮", "～"),
            listOf("∧", "∨", "∈", "∉", "∩", "∪", "⊆", "⊇", "⊂", "⊃"),
            listOf("¬", "∀", "∃", "∄", "∅", "∝", "‰", "∥", "⊥", "≡"),
        ),
        3 to listOf(
            listOf("¹", "²", "³", "⁴", "⁵", "⁶", "⁷", "⁸", "⁹", "⁰"),
            listOf("₁", "₂", "₃", "₄", "₅", "₆", "₇", "₈", "₉", "₀"),
            listOf("⅟", "½", "⅓", "¼", "⅕", "⅙", "⅐", "⅛", "⅑", "⅒"),
            listOf("⁄", "⅔", "⅖", "¾", "⅗", "⅜", "⅘", "⅚", "⅝", "⅞"),
        ),
        4 to listOf(
            listOf("α", "β", "γ", "δ", "ε", "ζ", "η", "θ", "ι"),
            listOf("κ", "λ", "μ", "ν", "ξ", "ο", "π", "ρ"),
            listOf("σ", "τ", "υ", "φ", "χ", "ψ", "ω")
        ),
        5 to listOf(
            listOf("Α", "Β", "Γ", "Δ", "Ε", "Ζ", "Η", "Θ", "Ι"),
            listOf("Κ", "Λ", "Μ", "Ν", "Ξ", "Ο", "Π", "Ρ"),
            listOf("Σ", "Τ", "Υ", "Φ", "Χ", "Ψ", "Ω")
        )
    )

    fun createKeys(imeOptions: Int?, categoryIndex: Int): List<List<KeyData>> {
        val grid = symbolGridData[categoryIndex] ?: emptyList()
        return grid.map { row ->
            row.map { sym ->
                TapKeyData(
                    content = KeyContent.Text(sym),
                    action = SymbolAction.InputSymbol(sym),
                    weight = 1.0f,
                    backgroundType = KeyBackgroundType.NORMAL
                )
            }
        } + listOf(
            listOf(
                SystemKeyFactory.create(FuncType.LANGUAGE, 3.0f),
                SystemKeyFactory.create(FuncType.SPACE, 4.0f),
                SystemKeyFactory.create(FuncType.BACKSPACE, 1.5f),
                SystemKeyFactory.createEnterKey(imeOptions, 1.5f)
            )
        )
    }
}