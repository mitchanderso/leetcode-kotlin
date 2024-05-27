package LC2024

import java.lang.Math.max
import java.lang.Math.min

class `334` {
    fun increasingTriplet(nums: IntArray): Boolean {
        val len = nums.size
        val minBefore = IntArray(len) { 0 }
        val maxAfter = IntArray(len) { 0 }

        minBefore[0] = nums[0]
        maxAfter[len - 1] = nums[len - 1]

        for (i in 1 until len) {
            val j = nums.size - i - 1
            minBefore[i] = min(minBefore[i - 1], nums[i])
            maxAfter[j] = max(maxAfter[j + 1], nums[j])
        }


        for (i in 1 until len - 1 ) {
            val left = minBefore[i - 1]
            val right = maxAfter[i + 1]
            if (left < nums[i] && nums[i] < right) return true
        }

        return false
    }




}



fun main() {
    val soln = `334`()
    println(soln.increasingTriplet(intArrayOf(1,1,1,1,1,3,7)))




}