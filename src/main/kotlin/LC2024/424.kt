package LC2024

import kotlin.math.max

class `424` {
    fun characterReplacement(s: String, k: Int): Int {
        var counts = Array<Int> (26) { 0 }
        var common = s[0]
        var best = 0
        var end = 0
        var start = 0
        while (end < s.length) {

                // It could become the most common character
            counts[s[end] - 'A']++
            if (counts[s[end] - 'A'] > counts[common - 'A']) {
                common = s[end]
            }

            // Replace all the non common characters
            val len = end - start + 1
            val replacementsToMake = len - counts[common - 'A']

            if (replacementsToMake > k) {
                counts[s[start] - 'A']--
                start++
            }




            best = max(best, (end - start) + 1)
            end++
        }

        return best
    }
}

fun main() {
    val soln = `424`()
    println(soln.characterReplacement("AAABBAL", 2))
}