package LC2024

import kotlin.math.sqrt

class `633` {
    fun judgeSquareSum(c: Int): Boolean {
        var right = sqrt(c.toDouble()).toLong()
        var left = 0L
        while (left <= right) {
            val ll = left * left
            var rr = right * right
            if (rr + ll == c.toLong()) return true
            if (rr + ll > c) {
                right--
            } else if (rr + ll < c) {
                left++
            }
        }

        return false
    }
}

fun main() {
    val soln = `633`()
    println(soln.judgeSquareSum(2147483600))
}