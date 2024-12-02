package LC2024

import kotlin.math.min

class `3097` {
    fun minimumSubarrayLength(nums: IntArray, k: Int): Int {
        val binaryRepresentations = nums.map { Integer.toBinaryString(it).padStart(30, '0') }
        var windowStart = 0
        //var windowEnd = 0

        var windowOrSum = 0
        var windowCounts = Array<Int> (30) { 0 }
        var answer = Int.MAX_VALUE

        for (windowEnd in 0 until nums.size) {
            // Always make the window one bigger
            windowOrSum = windowOrSum or nums[windowEnd]
            updateBinaryCountArray(windowCounts, binaryRepresentations[windowEnd], true)


            // Check if it is a valid window, if it is, we can make it smaller
            while (windowStart <= windowEnd && windowOrSum >= k) {
                answer = min(answer, windowEnd - windowStart + 1)

                updateBinaryCountArray(windowCounts, binaryRepresentations[windowStart], false)
                windowOrSum = windowCounts.map { if(it != 0) 1 else 0 }.joinToString("").toInt(2)
                windowStart++
            }


        }



        return if (answer == Int.MAX_VALUE) -1 else answer
    }

    fun updateBinaryCountArray(array: Array<Int>, binaryNum: String, add: Boolean) {
        binaryNum.forEachIndexed { index, c ->
            if (c == '1')  {
                if (add) array[index]++ else array[index]--
            }
        }
    }


}

fun main() {
    val soln = `3097`()
    println(soln.minimumSubarrayLength(intArrayOf(2,1,400), 10))
}