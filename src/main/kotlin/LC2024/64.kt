package LC2024

import kotlin.math.min


class `64` {
    fun minPathSum(grid: Array<IntArray>): Int {
        val dp = MutableList<MutableList<Int>>(grid.size) { MutableList<Int>(grid[0].size) { - 1 } }
        dp[0][0] = grid[0][0]

        for (row in 0 until grid.size) {
            for (col in 0 until grid[0].size) {
                // Check left or above
                if (col > 0 && row > 0) {
                    dp[row][col] = grid[row][col] + min(dp[row - 1][col], dp[row][col - 1])
                } else if (col > 0) {
                    dp[row][col] = grid[row][col] + dp[row][col - 1]
                } else if (row > 0) {
                    dp[row][col] = grid[row][col] + dp[row - 1][col]
                }
            }
        }

        return dp[grid.size - 1][grid[0].size - 1]
    }



}

fun main() {
    val soln = `64`()
    println(
        soln.minPathSum(
            arrayOf(
                intArrayOf(1,2,3),
                intArrayOf(4,5,6)
            )
        )
    )
}