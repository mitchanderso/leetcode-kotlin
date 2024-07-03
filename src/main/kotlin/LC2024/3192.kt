package LC2024

class `3192` {
    fun minOperations(nums: IntArray): Int {
        var idx = 0
        var ans = 0
        while (idx < nums.size) {
            if ((ans % 2 == 0 && nums[idx] == 0) || (ans % 2 != 0 && nums[idx] == 1)) {
                ans++
            }
            idx++
        }
        return ans
    }
}

fun main() {
    val soln = `3192`()
    println(soln.minOperations(intArrayOf(1,0,0,0)))
    //println(soln.minOperations(intArrayOf(0,1,1,1)))
}