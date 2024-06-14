package LC2024

class `3180` {
    fun sortColors(nums: IntArray): Unit {
        for (i in 1 until nums.size) {
            var j = i
            while (j >= 1 && nums[j] < nums[j - 1]) {
                val temp = nums[j - 1]
                nums[j - 1] = nums[j]
                nums[j] = temp
                j--
            }
        }
        println(nums.toList())
    }
}

fun main() {
    val soln = `75`()
    println(soln.sortColors(intArrayOf(2,0,2,1,1,0)))
}