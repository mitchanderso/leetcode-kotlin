package LC2025

import kotlin.math.max

class `1800` {
    fun maxAscendingSum(nums: IntArray): Int {
        var sum = nums[0]
        var max = nums[0]
        for (i in 1 until nums.size) {
            if (nums[i] > nums[i - 1]) {
                sum += nums[i]
            } else {
                sum = nums[i]
            }
            max = max(max, sum)
        }
        return max
    }
}

fun main() {
    val soln = `1800`()
    println(soln.maxAscendingSum(
        intArrayOf(10,20,30,5,10,50)
    ))
    println(soln.maxAscendingSum(
        intArrayOf(10,20,30,40,50)
    ))
    println(soln.maxAscendingSum(
        intArrayOf(12,17,15,13,10,11,12)
    ))


}