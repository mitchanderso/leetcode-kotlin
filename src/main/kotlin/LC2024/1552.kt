package LC2024

import kotlin.math.max



class `1552` {
    fun maxDistance(position: IntArray, m: Int): Int {
        position.sort()
        var left = 0
        var right = 1_000_000_000
        var best = -1
        while (left <= right) {
            val mid = (left + right) / 2
            if (position.isValid(mid, m)) {
                best = max(best, mid)
                left = mid + 1
            } else {
                right = mid -1
            }
        }
        return best
    }

    private fun IntArray.isValid(mid: Int, total: Int): Boolean {
        var lastBall = this[0]
        var ballsRemaining = total - 1
        for (i in 1 until this.size) {
            if (this[i] - lastBall >= mid) {
                ballsRemaining--
                lastBall = this[i]
            }
            if (ballsRemaining == 0) return true
        }

        return false
    }
}

fun main() {
    val soln = `1552`()
    println(soln.maxDistance(intArrayOf(1,2,3,4,5,6,7,8,9,10), 4))
}