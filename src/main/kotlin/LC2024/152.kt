package LC2024

import com.sun.source.tree.Tree
import java.lang.Math.max

class `152` {
    fun maxProduct(nums: IntArray): Int {
        if (nums.size == 1) return nums[0]
        var bestAt = nums[0]
        var bestAtNeg = nums[0]
        var max = bestAt
        var i = 1
        while (i < nums.size) {
            if (nums[i] > 0) {
                if (bestAt == 0) bestAt =  1
                if (bestAtNeg == 0) bestAtNeg = 1
                bestAt *= nums[i]
                bestAtNeg *= nums[i]
            }
            if (nums[i] < 0) {
                var countNeg = 0
                var firstVal = 1
                var run = 1
                while (i < nums.size && nums[i] < 0) {
                    if (countNeg == 0) firstVal = nums[i]
                    run *= nums[i]
                    countNeg++
                    i++
                }

                val firstRun = run / nums[i - 1]
                val secondRun = run / firstVal

                if (countNeg % 2 == 1) {
                    println("An uneven run of negatives, either the best value is multiplied by $firstRun or a new run is started at $secondRun")
                    val usingFirstRun = bestAt * firstRun
                    println("If we use the first run the value is $usingFirstRun")
                    bestAt = secondRun
                    println("Else keep going with ")
                }
                else {
                    bestAt *= run
                    println("Run was even, use the whole thing, new bestAt is $bestAt")
                }

            }
            if (nums[i] == 0) {
                bestAt = 0
                bestAtNeg = 0
            }

            max = max(max, bestAt)
            if (bestAtNeg > 0) {
                max = max(max, bestAtNeg)
            }
            i++
        }
        return max
    }

}



fun main() {
    val soln = `152`()

    println("${soln.maxProduct(intArrayOf(
        2,-5,-2,-4,3
    ))} == 24")

//    println("${soln.maxProduct(intArrayOf(
//        -2,0,-1
//    ))} == 0")

//    println("${soln.maxProduct(intArrayOf(
//        2,3,-2,4
//    ))} == 6")
//
//    println("${soln.maxProduct(intArrayOf(
//        2,3,-2,4,10
//    ))} == 40")
//
//    println("${soln.maxProduct(intArrayOf(
//        2,3,-2,4,10,-2
//    ))} == 960")



}