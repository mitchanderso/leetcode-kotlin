package LC2024

class `3330` {
    fun possibleStringCount(word: String): Int {
        var answer = 1
        for (i in 1 until word.length) {
            if (word[i] == word[i - 1]) answer++
        }
        return answer
    }
}

fun main() {
    val soln = `3330`()
    println(soln.possibleStringCount("abbcccc"))
}