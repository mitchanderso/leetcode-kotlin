package LC2024


class `3158`() {
    fun duplicateNumbersXOR(nums: IntArray): Int {
        var ans : Int? = null
        val seen = mutableSetOf<Int>()
        for (i in 0 until nums.size) {
            if (!seen.add(nums[i])) {
                if (ans == null) ans = nums[i]
                else ans = ans xor nums[i]
            }
        }
        return ans ?: 0
    }

}


fun main() {
    val soln = `3158`()
}