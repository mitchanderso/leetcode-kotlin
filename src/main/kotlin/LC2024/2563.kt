package LC2024

class `2563` {
    fun countFairPairs(nums: IntArray, lower: Int, upper: Int): Long {
        val sorted = nums.sorted()

        var ans = 0L

        for (i in 0 until  sorted.size) {


            val validLower = lower - sorted[i]
            val validUpper = upper - sorted[i]

            val firstValidUpper = binarySearch(sorted, i + 1, sorted.size - 1, validUpper + 1)
            val firstValidLower = binarySearch(sorted, i + 1, sorted.size - 1, validLower)

                ans += firstValidUpper - firstValidLower


        }
        return ans
    }

    fun binarySearch(sortedNums: List<Int>, low: Int, high: Int, target: Int) : Int {
        var mLow = low
        var mHigh = high
        while (mLow <= mHigh) {
            val middle = (mLow + mHigh) / 2
            if (sortedNums[middle] >= target) mHigh = middle - 1
            else mLow = middle + 1
        }

        return mLow
    }
}

fun main() {
    val soln = `2563`()
    println(soln.countFairPairs(
        intArrayOf(0,1,7,4,4,5),
        3, 6
    ))

//    println(soln.countFairPairs(
//        intArrayOf(1,7,9,2,5),
//        11, 11
//    ))
}