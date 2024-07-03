package LC2024

import kotlin.collections.set

class `3185` {
    fun countCompleteDayPairs(hours: IntArray): Long {
        var ans = 0L
        val moduloCounts = mutableMapOf<Int, Int>()
        hours.forEach { hour ->
            val remainder = hour % 24
            val complement = (24 - remainder) % 24
            ans += moduloCounts.getOrDefault(complement, 0)
            moduloCounts[remainder] = moduloCounts.getOrDefault(remainder, 0) + 1
        }
        return ans
    }
}

fun main() {

    val soln = `3185`()
    println(soln.countCompleteDayPairs(intArrayOf(72,48,24,3)))
    println(soln.countCompleteDayPairs(intArrayOf(13,11)))
}