package LC2024

import java.util.*


class `76` {

    fun minWindow(s: String, t: String): String {
        var windowStart = 0
        var countInWindow = 0
        var bestLength = Int.MAX_VALUE
        var bestStart = 0
        var bestEnd = s.length - 1
        val mapCounts = t.associate { it to t.count { c -> c == it } }.toMutableMap()
        val mapCountsOriginal = t.associate { it to t.count { c -> c == it } }.toMutableMap()
        for (windowEnd in 0 until s.length) {
            if (mapCounts.containsKey(s[windowEnd])) {
                 // Issue somewhere here, not counting doubles
                mapCounts[s[windowEnd]] = mapCounts[s[windowEnd]]!! - 1
                if (mapCounts[s[windowEnd]]!! >= 0) countInWindow++
            }

            while (countInWindow == t.length) {
                val thisLength = (windowEnd - windowStart) + 1
                if (thisLength < bestLength) {
                    bestStart = windowStart
                    bestEnd = windowEnd + 1
                    bestLength = thisLength
                }

                if (mapCounts.containsKey(s[windowStart])) {
                    mapCounts[s[windowStart]] = mapCounts[s[windowStart]]!! + 1
                    if (mapCounts[s[windowStart]]!! > 0) countInWindow--
                }
                windowStart++
            }
        }

        return if ( bestLength == Int.MAX_VALUE ) "" else s.substring(bestStart, bestEnd)
    }


}

fun main() {
    val soln = `76`()
    println(soln.minWindow("aa", "aa"))
    println(soln.minWindow("ADOBECODEBANC", "ABC"))
    println(soln.minWindow("bba", "ab"))
}