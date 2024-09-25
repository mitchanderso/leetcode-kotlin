package LC2024

import kotlin.math.min

class `2707` {
    fun minExtraChar(s: String, dictionary: Array<String>): Int {
        // To memoize
        val memo = mutableMapOf<String, Int>()
        return breakUp(s, dictionary.toSet(), 0, memo)
    }

    fun breakUp(s: String, dict: Set<String>, pos: Int, memo: MutableMap<String, Int>) : Int {
        if (pos >= s.length) return 0


        var best = s.length

        // For all possible split locations
        for (i in pos until s.length) {
            val left = s.substring(pos .. i)
            val right = s.substring(i + 1)



            val leftOversInLeft = if (left in dict) 0 else left.length

            val leftOversInRight = if (right in dict) {
                0
            }
            else if (memo.containsKey(right)) {
                memo[right]!!
            } else {
                val leftover = breakUp(s, dict, i + 1, memo)
                memo[right] = leftover
                leftover
            }

            best = min(best, leftOversInLeft + leftOversInRight)

        }

        return best
    }
}

fun main() {
    val soln = `2707`()
    println(soln.minExtraChar("leetscode", arrayOf("leet", "code", "leetcode")))
}