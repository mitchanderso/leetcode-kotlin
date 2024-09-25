package LC2024

class `3289` {
    fun getSneakyNumbers(nums: IntArray): IntArray {
        val ans = mutableListOf<Int>()
        val set = mutableSetOf<Int>()
        for (i in 0 until nums.size) {
            if (!set.add(nums[i])) ans.add(nums[i])
        }

        return ans.toIntArray()
    }
}

fun main() {
    val soln = `3289`()
    println(soln.getSneakyNumbers(intArrayOf(0,3,2,1,3,2)).toList())
}