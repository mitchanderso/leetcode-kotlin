package LC2024

import kotlin.math.abs
import kotlin.math.max

class `1749` {

    fun maxAbsoluteSum(nums: IntArray): Int {
        var pos = IntArray(nums.size) { 0 }
        var neg = IntArray(nums.size) { 0 }

        neg[0] = if ( nums[0] <= 0 ) nums[0] else 0
        pos[0] = if ( nums[0] > 0 ) nums[0] else 0
        var ans = max(nums[0], abs(nums[0]))
        for (i in 1 until nums.size) {
            if (nums[i] >= 0) {
                pos[i] = pos[i - 1] + nums[i]
                if (neg[i - 1] + nums[i] < 0) {
                    neg[i] = neg[i - 1] + nums[i]
                }
            } else {
                neg[i] = neg[i - 1] + nums[i]
                if (pos[i - 1] + nums[i] >= 0) {
                    pos[i] = pos[i - 1] + nums[i]
                }
            }
            val localMax = max(pos[i], abs(neg[i]))
            ans = max(ans, localMax)
        }
        return ans
    }


}

fun main() {
    val soln = `1749`()
    println(soln.maxAbsoluteSum(intArrayOf(2,-5,1,-4,3,-2)))
}