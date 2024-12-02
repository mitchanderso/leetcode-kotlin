package LC2024

import kotlin.math.abs
import kotlin.math.min

class `1975` {
    fun maxMatrixSum(matrix: Array<IntArray>): Long {
        var negativeCount = 0
        var smallestNumber = Int.MAX_VALUE.toLong()
        var sum = 0L
        for (y in 0 until matrix.size) {
            for (x in 0 until matrix[0].size) {
                if (matrix[y][x] < 0) negativeCount++
                sum += abs(matrix[y][x])
                smallestNumber = min(abs(matrix[y][x]).toLong(), smallestNumber)
            }
        }

        if (negativeCount % 2 == 0) return sum
        else return sum - 2 * smallestNumber
    }
}

fun main() {
    val soln = `1975`()
    println(soln.maxMatrixSum(
        arrayOf(
            intArrayOf(1,2,3),
            intArrayOf(-1,-2,-3),
            intArrayOf(1,2,3),
        )
    ))
}

