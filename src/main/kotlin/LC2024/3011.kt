package LC2024

class `3011` {
    fun canSortArray(nums: IntArray): Boolean {
        // Try to move the smallest number to the front every time
        // If all numbers before it have an equal number, then we can move it

        // The sorted array will tell us the final position
        val sorted = nums.sorted()

        var positionInSorted = 0
        while (positionInSorted < sorted.size) {
            val toBeSorted = mutableListOf<Pair<Int, Int>>()
            for (i in 0 until nums.size) {
                if (nums[i] == sorted[positionInSorted]) {
                    toBeSorted.add(nums[i] to i)
                }
            }
            toBeSorted.forEach { (num, idx) ->
                var movingIdx = idx
                while (movingIdx > 0 && num <= nums[movingIdx - 1]) {
                    if (nums[movingIdx - 1].countOneBits() != num.countOneBits()) {
                        return false
                    }
                    val temp = nums[movingIdx - 1]
                    nums[movingIdx - 1] = num
                    nums[movingIdx] = temp
                    movingIdx--
                }
            }
            positionInSorted += toBeSorted.size
        }
        // Find all the indices of the numbers that need to be in the earliest position


        return true
    }
}

fun main() {
    val soln = `3011`()
    println(soln.canSortArray(intArrayOf(8,4,2,30,15)))
    println(soln.canSortArray(intArrayOf(1,2,3,4,5)))
    println(soln.canSortArray(intArrayOf(3,16,8,4,2)))
}