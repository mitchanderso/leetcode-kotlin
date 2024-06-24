package LC2024

import kotlin.math.min

class `3194` {
    fun minimumAverage(nums: IntArray): Double {
        nums.sort()
        var left = 0
        var right = nums.size - 1
        var minAverage = Double.MAX_VALUE
        while (left <= right) {
            minAverage = min(
                (nums[left] + nums[right]) / 2.0,
                minAverage
            )
            left++
            right--
        }

        return minAverage
    }
}

fun main() {
    val soln = `3194`()
    println(soln.minimumAverage(intArrayOf(7,8,3,4,15,13,4,1)))
}