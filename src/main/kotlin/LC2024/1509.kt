package LC2024

import kotlin.math.min

class `1509` {
    fun minDifference(nums: IntArray): Int {
        if (nums.size <= 4) return 0
        val sorted = nums.sorted()
        val fromEnd = sorted[sorted.size - 4] - sorted[0]
        val fromStart = sorted.last() - sorted[3]
        val twoStartOneEnd = sorted[sorted.size - 2] - sorted[2]
        val oneStartTwoEnd = sorted[sorted.size - 3] - sorted[1]
        return listOf(fromEnd, fromStart, twoStartOneEnd, oneStartTwoEnd).min()
    }
}

fun main() {
    val soln = `1509`()
    println(soln.minDifference(intArrayOf(82,81,95,75,20)))
    println(soln.minDifference(intArrayOf(6,6,0,1,1,4,6)))
}