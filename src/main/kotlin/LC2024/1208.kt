package LC2024

import java.lang.Math.abs
import java.lang.Math.max


class `1208`() {
    fun equalSubstring(s: String, t: String, maxCost: Int): Int {
        var windowStart = 0
        var windowEnd = 0
        var windowScore = 0
        var max = 0

        s.mapIndexed { index, c -> abs(c - t[index]) }

        while (windowEnd < s.length) {
            windowScore += Math.abs(s[windowEnd] - t[windowEnd])
            if (windowScore > maxCost) {
                windowScore -=  Math.abs(s[windowStart] - t[windowStart])
                windowStart++
            }
            windowEnd++
            max = max((windowEnd - windowStart), max)
        }

        return max
    }

}


fun main() {
    val soln = `1208`()

    println(soln.equalSubstring("pxezla", "loewbi", 25))


}