package LC2025

class `1408` {
    fun stringMatching(words: Array<String>): List<String> {
        val ans = mutableSetOf<String>()
        words.forEach { word1 ->
            words.forEach { word2 ->
                if (word1 != word2 && word2.contains(word1)) ans.add(word1)
            }
        }
        return ans.toList()
    }
}

fun main() {
    val soln = `1408`()
    println(soln.stringMatching(
        arrayOf("mass", "as", "hero", "superhero")
    ))

    println(soln.stringMatching(
        arrayOf("leetcode","et","code")
    ))

    println(soln.stringMatching(
        arrayOf("blue","green","bu")
    ))
}