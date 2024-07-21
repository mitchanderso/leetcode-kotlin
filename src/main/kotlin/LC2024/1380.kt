package LC2024

import kotlin.math.max
import kotlin.math.min

class `1380` {
    fun luckyNumbers (matrix: Array<IntArray>): List<Int> {
        val minRow = Array (matrix.size) { Int.MAX_VALUE }
        val maxCol = Array (matrix[0].size) { Int.MIN_VALUE }
        for (row in 0 until matrix.size) {
            for (col in 0 until matrix[0].size) {
                minRow[row] = min(minRow[row], matrix[row][col])
                maxCol[col] = max(maxCol[col], matrix[row][col])
            }
        }

        val ans = mutableListOf<Int>()

        for (row in 0 until matrix.size) {
            for (col in 0 until matrix[0].size) {
                if (matrix[row][col] == minRow[row] && matrix[row][col] == maxCol[col]) ans.add(matrix[row][col])

            }
        }

        return ans
    }
}

fun main() {
    val soln = `1380`()
    println(soln.luckyNumbers(
        arrayOf(
            intArrayOf(1,10,4,2),
            intArrayOf(9,3,8,7),
            intArrayOf(15,16,17,12),
        )
    ))
}