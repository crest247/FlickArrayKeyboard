package com.crest247.flickarraykeyboard.modes.shared.array

import android.content.Context
import com.crest247.flickarraykeyboard.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.floor

object ArrayDecoder {
    private var keySumTable: List<String> = emptyList()
    private var valueSumTable: List<String> = emptyList()

    var isLoaded = false
        private set

    suspend fun loadData(context: Context) {
        withContext(Dispatchers.IO) {
            val keys = context.resources.openRawResource(R.raw.array30_key_sum).bufferedReader()
                .readLines()
            val values = context.resources.openRawResource(R.raw.array30_value_sum).bufferedReader()
                .readLines()
            withContext(Dispatchers.Main) {
                keySumTable = keys
                valueSumTable = values
                isLoaded = true
            }
        }
    }

    fun decode(lookupString: String): List<String> {
        if (!isLoaded || keySumTable.isEmpty() || valueSumTable.isEmpty() || lookupString.isEmpty()) return emptyList()

        if (lookupString.length > 5 || (lookupString.length == 5 && lookupString[4] != 'i'))
            return emptyList()
        if (lookupString.length == 2 && lookupString[0] == 'w' && lookupString[1].code in 48..57)
            return getValueList(150 + lookupString[1].code - 48)
        val sum = calcRadicalsSum(lookupString)
        val hashValue = calcHashValue(sum)
        return getKeyValue(hashValue, sum)
    }

    private fun getRadicalCode(radical: Char): Int {
        val code = radical.code
        return when {
            code in 97..122 -> code - 92
            radical == ',' || radical == '.' || radical == '/' -> code - 43
            radical == ';' || radical == ':' -> 2
            code in 65..90 -> code - 60
            radical == '<' || radical == '>' || radical == '?' -> code - 59
            else -> 0
        }
    }

    private fun calcRadicalsSum(radicals: String): Int {
        var factor = 1
        var sum = 0
        for (radical in radicals) {
            val code = getRadicalCode(radical)
            if (code == 0) return 0
            sum += code * factor
            factor *= 30
        }
        if (radicals.length == 5) sum -= 9720000
        return sum
    }

    private fun calcHashValue(radicalsSum: Int): Int {
        return floor((radicalsSum * 0.6180339887) % 1 * 32767).toInt()
    }

    private fun getKeyValue(lineNumber: Int, radicalsSum: Int): List<String> {
        if (lineNumber !in keySumTable.indices) return emptyList()
        val lineData = keySumTable[lineNumber].split('\t')
        if (lineData.size == 1) {
            return if (lineData[0] == "") emptyList() else lineData[0].split(' ')
        }
        for ((index, data) in lineData.withIndex()) {
            if (data.toIntOrNull() == radicalsSum) {
                return getValueList(lineNumber, index)
            }
        }
        return emptyList()
    }

    private fun getValueList(lineNumber: Int, index: Int = 0): List<String> {
        val data = valueSumTable[lineNumber].split('\t').getOrNull(index) ?: return emptyList()
        return data.split(' ')
    }

    private val arrayAlphabet = listOf("pqwertyuio", ";asdfghjkl", "/zxcvbnm,.")
    private fun arrayToAlphabet(arrayRadicals: String): String {
        if (arrayRadicals.length % 2 == 1) return ""
        val sb = StringBuilder()
        for (i in 0 until (arrayRadicals.length / 2)) {
            val charCode = arrayRadicals[i * 2].code - 48
            if (charCode !in 0..9) continue
            when (arrayRadicals[i * 2 + 1]) {
                '^' -> sb.append(arrayAlphabet[0][charCode])
                '-' -> sb.append(arrayAlphabet[1][charCode])
                'v' -> sb.append(arrayAlphabet[2][charCode])
            }
        }
        return sb.toString()
    }
}
