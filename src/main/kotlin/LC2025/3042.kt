package LC2025

class `3042` {
    fun countPrefixSuffixPairs(words: Array<String>): Int {
        var ans = 0
        for (i in 0 until words.size) {
            for (j in i + 1 until words.size) {
                if (isPrefixAndSuffix(words[i], words[j])) {
                    ans++
                }
            }
        }
        return ans
    }

    fun isPrefixAndSuffix(word1: String, word2: String) : Boolean {
        if (word1.length > word2.length) return false
        for (i in 0 until word1.length) {
            if (word1[i] != word2[i] || word1[word1.length - 1 - i] != word2[word2.length - 1 - i]) {
                return false
            }
        }
        return true
    }
}

fun main() {
    val soln = `3042`()
    println(soln.countPrefixSuffixPairs(
        arrayOf("abab", "ab")
    ))

}