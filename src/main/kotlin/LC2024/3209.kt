package LC2024

class `3209` {
    fun countSubarrays(nums: IntArray, k: Int): Long {
        var ans = 0L
        for (l in 0 until nums.size) {
            var anded = nums[l]
            for (r in l + 1 until nums.size) {
                if (anded == k) ans++
                anded = anded and nums[r]
                if (anded < k) break
            }
            if (anded == k) ans++
        }

        return ans
    }
}

fun main() {
    val soln = `3209`()

    println(soln.countSubarrays(
        intArrayOf(1,1,1), 1
    )) // 6
    println(soln.countSubarrays(
        intArrayOf(1,1,2), 1
    )) // 3
    println(soln.countSubarrays(
        intArrayOf(1,2,3), 2
    )) // 2
}