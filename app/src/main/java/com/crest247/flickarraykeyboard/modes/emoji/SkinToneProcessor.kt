package com.crest247.flickarraykeyboard.modes.emoji

import com.crest247.flickarraykeyboard.modes.emoji.EmojiData.humanPrefixMap
import com.crest247.flickarraykeyboard.modes.emoji.EmojiData.humanTypeMap

object SkinToneProcessor {
    private const val SKIN_LIGHT = "\uD83C\uDFFB"
    private const val SKIN_MEDIUM_LIGHT = "\uD83C\uDFFC"
    private const val SKIN_MEDIUM = "\uD83C\uDFFD"
    private const val SKIN_MEDIUM_DARK = "\uD83C\uDFFE"
    private const val SKIN_DARK = "\uD83C\uDFFF"
    private const val VS16 = 0xFE0F

    private val SKIN_VARIANTS = listOf(
        SKIN_LIGHT,
        SKIN_MEDIUM_LIGHT,
        SKIN_MEDIUM,
        SKIN_MEDIUM_DARK,
        SKIN_DARK
    )

    fun hasSkinToneVariants(emoji: String): Boolean {
        return humanTypeMap.contains(emoji)
    }

    private fun applySkinTone(baseEmoji: String, skinTone: String): String {
        val length = baseEmoji.length
        val sb = StringBuilder(length + 2)
        sb.append(baseEmoji)

        val firstCodePoint = Character.codePointAt(baseEmoji, 0)
        val firstCharCount = Character.charCount(firstCodePoint)

        if (length > firstCharCount) {
            val secondCodePoint = Character.codePointAt(baseEmoji, firstCharCount)
            if (secondCodePoint == VS16) {
                sb.replace(firstCharCount, firstCharCount + 1, skinTone)
                return sb.toString()
            }
        }
        sb.insert(firstCharCount, skinTone)
        return sb.toString()
    }

    private fun getCodePointCount(text: String): Int {
        return text.codePointCount(0, text.length)
    }

    private fun getSecondCodePointString(text: String): String? {
        val count = getCodePointCount(text)
        if (count < 2) return null
        val offset = text.offsetByCodePoints(0, 1)
        val codePoint = Character.codePointAt(text, offset)
        return String(Character.toChars(codePoint))
    }

    fun generateVariants(emoji: String): List<String> {
        val type = humanTypeMap[emoji]

        if (type == "One") {
            return SKIN_VARIANTS.map { skinTone ->
                applySkinTone(emoji, skinTone)
            }
        }

        if (type == "Same") {
            val directKey = emoji + SKIN_LIGHT
            val canAppendDirectly = humanPrefixMap.containsKey(directKey)

            return SKIN_VARIANTS.map { skinTone ->
                if (canAppendDirectly) {
                    emoji + skinTone
                } else {
                    applySkinTone(emoji, skinTone) + skinTone
                }
            }
        }

        val emojiPrefix = humanPrefixMap[emoji]!!
        val countPrefix = getCodePointCount(emojiPrefix)
        val countEmoji = getCodePointCount(emoji)

        if (countPrefix + 1 == countEmoji) {
            return SKIN_VARIANTS.map { skinTone ->
                emojiPrefix + skinTone
            }
        } else {
            val secondChar = getSecondCodePointString(emoji)
            return SKIN_VARIANTS.map { skinTone ->
                if (secondChar == skinTone) emoji else emojiPrefix + skinTone
            }
        }
    }
}