package LC2024

import kotlin.math.min

class `3190` {
    fun minimumOperations(nums: IntArray): Int {
        return nums.fold (0 ) { acc, curr ->
            acc + min((curr % 3), 3 - (curr % 3))
        }
    }
}

fun main() {
    val soln = `3190`()
    println(soln.minimumOperations(intArrayOf(1,2,3,4)))
}