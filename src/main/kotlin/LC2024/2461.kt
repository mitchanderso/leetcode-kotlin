package LC2024

import kotlin.math.max

class `2461` {
    fun maximumSubarraySum(nums: IntArray, k: Int): Long {
        var maxSum = 0L
        var runningSum = 0L
        val counts = mutableMapOf<Int, Int>()
        for (i in 0 until k) {
            runningSum += nums[i]
            counts[nums[i]] = counts.getOrDefault(nums[i], 0) + 1
        }

        if (counts.size == k) maxSum = runningSum

        for (end in k until nums.size) {
            // Remove the first value
            val left = end - k
            runningSum -= nums[left]
            counts[nums[left]] = counts.getOrDefault(nums[left], 1) - 1
            if (counts[nums[left]] == 0) counts.remove(nums[left])

            // Add the next value
            runningSum += nums[end]
            counts[nums[end]] = counts.getOrDefault(nums[end], 0) + 1

            if (counts.size == k) {
                maxSum = max(runningSum, maxSum)
            }
        }

        return maxSum

    }
}

fun main() {
    val soln = `2461`()
    println(soln.maximumSubarraySum(intArrayOf(1,2,2), 2))
}