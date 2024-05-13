package LC2024

import java.util.Stack


class `2971`() {
    fun largestPerimeter(nums: IntArray): Long {
        if (nums.size < 3) return -1
        val prefixSum = LongArray(nums.size) { 0 }
        val sortedNums = nums.sorted()

        for (i in 1 until sortedNums.size) {
            prefixSum[i] = prefixSum[i - 1] + sortedNums[i - 1]
        }

        if (sortedNums.size == 3) {
            if (sortedNums[2] < prefixSum[2]) return (prefixSum[2] + sortedNums[2]).toLong() else return -1
        }

        var validAns = -1L
        for (j in 0 until prefixSum.size) {
            if (sortedNums[j] < prefixSum[j]) {
                validAns = (prefixSum[j] + sortedNums[j]).toLong()
            }
        }

        return validAns
    }


}


fun main() {
    val soln = `2971`()



//    println(soln.largestPerimeter(intArrayOf(1,12,1,2,5,50,3))) // 12
//    println(soln.largestPerimeter(intArrayOf(5,5,5))) // 15
//    println(soln.largestPerimeter(intArrayOf(5,5,50))) // -1
//    println(soln.largestPerimeter(intArrayOf(1,5,1,5))) // 12
//    println(soln.largestPerimeter(intArrayOf(1,5,1,7))) // -1
    println(soln.largestPerimeter(intArrayOf(2,3,4,12,50))) // -1
}