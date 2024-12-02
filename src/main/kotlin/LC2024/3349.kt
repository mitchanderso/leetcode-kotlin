package LC2024

class `3349` {
    fun hasIncreasingSubarrays(nums: List<Int>, k: Int): Boolean {
        var startOfRun = 0
        while (startOfRun + 2 * k <= nums.size) {
            var hasRunOne = true
            for (i in startOfRun until startOfRun + k) {
                if (i != startOfRun && nums[i] <= nums[i - 1]) {
                    hasRunOne = false
                    break
                }
            }

            var hasRunTwo = true
            for (j in startOfRun + k until startOfRun + 2 * k) {
                if (j != startOfRun + k && nums[j] <= nums[j - 1]) {
                    hasRunTwo = false
                    break
                }
            }

            if (hasRunOne && hasRunTwo) return true
            startOfRun++

        }

        return false
    }

}

fun main() {
    val soln = `3349`()
    println(soln.hasIncreasingSubarrays(listOf(-15,19), 1))
}