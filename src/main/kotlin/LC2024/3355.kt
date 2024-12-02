package LC2024

class `3355` {
    fun isZeroArray(nums: IntArray, queries: Array<IntArray>): Boolean {
        val counts = IntArray(nums.size) { 0 }
        queries.forEach { (l, r) ->
            for (i in l .. r) {
                counts[i]++
            }
        }

        for (i in 0 until nums.size) {
            if (nums[i] - counts[i] > 0) return false
        }

        return true
    }
}

fun main() {
    val soln = `3355`()
    println(soln.isZeroArray(intArrayOf(1,0,1), arrayOf(
        intArrayOf(0,2)
    )))
    println(soln.isZeroArray(intArrayOf(4,3,2,1), arrayOf(
        intArrayOf(1,3),
        intArrayOf(0,2),
    )))
}