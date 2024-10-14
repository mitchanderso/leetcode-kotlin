package LC2024

class `3305` {
    fun countOfSubstrings(word: String, k: Int): Int {
        var vowelcount = 0
        var consonants = 0
        var vowelset = mutableMapOf<Char, Int>()

        var vowels = setOf('a', 'e', 'i', 'o', 'u')
        var ans = 0
        var windowStart = 0
        var windowEnd = 0

        while (windowEnd < word.length) {
            // Add in to the window

            if (word[windowEnd] in vowels) {
                vowelcount++
                vowelset[word[windowEnd]] = vowelset.getOrDefault(word[windowEnd], 0) + 1
            } else {
                consonants++
            }

            while (consonants > k) {
                // When the window is invalid, contract it
                val start = word[windowStart]

                if (start in vowels) {
                    vowelset[start] = vowelset[start]!! - 1
                    if (vowelset[start] == 0) vowelset.remove(start)
                } else {
                    consonants--
                }

                windowStart++
            }

            if (vowelset.size == 5 && consonants == k) {
                // Whe we find a working window, expand it
                ans++

                // Search forward
                var leftCopy = windowStart
                val vowelSetCopy = HashMap(vowelset)
                var consonantCopy = consonants

                while (vowelSetCopy.size == 5 && consonantCopy == k) {
                    val start = word[leftCopy]

                    if (start in vowels) {
                        vowelSetCopy[start] = vowelSetCopy[start]!! - 1
                        if (vowelSetCopy[start] == 0) vowelSetCopy.remove(start)
                    } else {
                        consonantCopy--
                    }

                    leftCopy++
                    if (consonantCopy == k && vowelSetCopy.size == 5) ans++
                }
            }

            windowEnd++
        }

        return ans

    }
}

fun main() {
    val soln = `3305`()
    println(soln.countOfSubstrings("aaeuoiouee", 0))
    //println(soln.countOfSubstrings("ieaouqqieaouqq", 1))
}