package LC2024


class `3152`() {
    fun isArraySpecial(nums: IntArray, queries: Array<IntArray>): BooleanArray {
        val ans = BooleanArray(queries.size)
        var prefixSum = mutableListOf<Int>()
        prefixSum.add(0)
        var counter = 0
        for (i in 1 until nums.size) {
            if (nums[i] % 2 == nums[i - 1] % 2) counter++

            prefixSum.add(counter)
        }

        queries.forEachIndexed { index, query ->
            val ans1 = prefixSum[query[0]] == prefixSum[query[1]]
            ans[index] = ans1
        }
        return ans
    }

}


fun main() {
    val soln = `3152`().isArraySpecial(
        intArrayOf(4,3,1,6),
        arrayOf(
            intArrayOf(0,2),
            intArrayOf(2,3),

        )
    )
    println(soln.toList())


}