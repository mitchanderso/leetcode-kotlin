package LC2024

import kotlin.math.min

class `409` {

    // abccccdd
    // 1 a, b
    // 2 d
    // 4 c
    fun longestPalindrome(s: String): Int {
        val count = s.associateWith { ch -> s.count { it == ch } }
        var oddCount = 0
        count.forEach { char, count ->
            if (count % 2 != 0) oddCount++
        }

        return s.length - oddCount + if (oddCount > 0) 1 else 0

        // All of the odd ones can be turned even
    }

    fun commonChars(words: Array<String>): List<String> {
        val answer = mutableListOf<String>()
        val countOfWords = words.map { word -> word.associateWith { ch -> word.count { it == ch } } }
        countOfWords[0].forEach { (ch, count) ->
            var min = count
            countOfWords.forEach {
                min = min(min, it.getOrDefault(ch, 0))
            }

            for (i in 0 until min) answer.add(ch.toString())

        }
        return answer
    }



}



fun main() {
    val soln = `409`()
    println(soln.commonChars(
        arrayOf(
            "cool",
            "lock",
            "cook"
        )
    ))
}