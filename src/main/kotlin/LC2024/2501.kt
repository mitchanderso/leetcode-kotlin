package LC2024


import kotlin.math.max
import kotlin.math.pow

class `2501` {
    fun longestSquareStreak(nums: IntArray): Int {
        val set = nums.toSortedSet()
        var best = -1
        set.forEach { num ->
            var streak = 0
            var square = num.toDouble().pow(2).toInt()
            //println("Num $num has a square chain of")
            while (square in set) {
                //println("$square")
                streak++
                square = square.toDouble().pow(2).toInt()
            }

            if (streak > 0) streak++ else streak = -1
            //println("Total streak is $streak")
            best = max(best, streak)
        }

        return best
    }
}

fun main() {
    val soln = `2501`()
    println(soln.longestSquareStreak(intArrayOf(4,3,6,16,8,2)))
}