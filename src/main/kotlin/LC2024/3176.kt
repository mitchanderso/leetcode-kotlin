package LC2024

import kotlin.math.max

class `3176` {

    fun maximumLength(nums: IntArray, k: Int): Int {
        val dp = Array (505) {
            Array(505) {
                IntArray (26) { -1}
            }
        }
        return dfs(0, 500, nums.size, nums, k, dp)
    }

    fun dfs(ind: Int, prev: Int, n: Int, nums: IntArray, k: Int, dp: Array<Array<IntArray>>) : Int {
        if (ind == n) {
            return 0
        }

        if (prev != 500 && dp[ind][prev][k] != -1) {
            return dp[ind][prev][k]
        }

        var max = 0

        if (prev == 500 || (nums[prev] == nums[ind])) {
            max = max(max, 1 + dfs(ind + 1, ind, n, nums,  k, dp)) // Take
            max = max(max,  dfs(ind + 1, prev, n, nums,  k, dp)) // Dont take

        } else if (nums[prev] != nums[ind]) {
            if (k > 0) {
                max = max(max, 1 + dfs(ind + 1, ind, n, nums,  k - 1, dp))  // Take and reduce
            }
            max = max(max,  dfs(ind + 1, prev, n, nums,  k, dp))  // Dont take
        }

        dp[ind][prev][k] = max
        return dp[ind][prev][k]
    }
}

fun main() {
    val soln = `3176`()
    println(soln.maximumLength(intArrayOf(48, 49, 49, 50, 50), 2)) // 4
//    println(soln.maximumLength(intArrayOf(49,50,50,48,48), 1)) // 4
//    println(soln.maximumLength(intArrayOf(49,50,50,48), 1)) // 3
//    println(soln.maximumLength(intArrayOf(49,50,50,48,48,48), 1)) // 5
}