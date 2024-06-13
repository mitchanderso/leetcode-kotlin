package LC2024

import java.util.*


class `1696`() {
    fun maxResult(nums: IntArray, k: Int): Int {
        val pq = PriorityQueue<Pair<Int, Int>>(compareByDescending { it.first })
        val dp = IntArray(nums.size) { 0 }
        dp[0] = nums[0]
        pq.offer(dp[0] to 0)
        for (i in 1 until nums.size) {
            while (pq.peek().second < i - k) pq.poll()
            dp[i] = pq.peek().first + nums[i]
            pq.offer(dp[i] to i)
        }
        return dp[nums.size - 1]
    }


}


fun main() {
    val soln = `1696`()
    println(soln.maxResult(
        intArrayOf(1,-1,-2,4,-7,3),
        k = 2
    ))
}