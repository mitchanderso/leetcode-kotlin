package LC2024

import kotlin.math.abs
import kotlin.math.min

class `539` {
    fun findMinDifference(timePoints: List<String>): Int {
        val times = sortedSetOf<Int>()
        timePoints.forEach { tp ->
            val asMinutes = "${tp[0]}${tp[1]}".toInt() * 60 + "${tp[3]}${tp[4]}".toInt()
            if (!times.add(asMinutes)) return 0
        }

        var previous = times.first
        var minDiff = Int.MAX_VALUE
        times.drop(1).forEach { current ->
            minDiff = min(minDiff, abs(current - previous))
            previous = current

        }

        // Going forwards from the last to the first
        minDiff = min(minDiff, times.first() + 1440 - times.last())
        return minDiff

    }
}

fun main() {
    val soln = `539`()
    println(soln.findMinDifference(listOf(
        "23:59","00:00"
    )))
}