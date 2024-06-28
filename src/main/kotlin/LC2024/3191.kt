package LC2024

class `3191` {
    fun minOperations(nums: IntArray): Int {
        var idx = 0
        var ans = 0
        while (idx < nums.size) {
            if (nums[idx] == 0) {
                ans++
                for (i in idx until idx + 3) {
                    if (i >= nums.size) {
                        return -1
                    }
                    if (nums[i] == 0) nums[i] = 1
                    else if (nums[i] == 1) nums[i] = 0
                }
            }
            idx++
        }
        return ans
    }
}

fun main() {
    val soln = `3191`()
    println(soln.minOperations(intArrayOf(0,1,1,1,0,0)))
    println(soln.minOperations(intArrayOf(0,1,1,1)))
}