package LC2024

import java.util.Stack
import kotlin.math.ceil
import kotlin.math.max


class `1463`() {
    fun cherryPickup(grid: Array<IntArray>): Int {
        val dp = MutableList (grid.size) { row -> MutableList (grid[0].size) { MutableList(grid[0].size) { -1 } } }

        val width = grid[0].size - 1

        grid.forEachIndexed { rowIdx, row ->
            if (rowIdx == 0) {
                dp[0][0][width] =  grid[0][width] + grid[0][0]
            } else {
                row.forEachIndexed { colIdx, colVal ->
                    for (col2 in colIdx + 1 .. width) {
                        val adj = getAllAdj(grid, rowIdx, colIdx, col2, dp)
                        val mx = adj.maxOrNull() ?: -1
                        dp[rowIdx][colIdx][col2] = mx
                    }
                }
            }
        }

        var ans = -1
        for (r1 in 0 .. width) {
            for (r2 in r1 + 1 .. width) {
                ans = max(ans, dp[dp.size - 1][r1][r2])
            }
        }
        return ans
    }

    val displacements = listOf(
        -1 to -1,
        -1 to 0,
        -1 to 1,
        1 to -1,
        1 to 0,
        1 to 1,
        0 to -1,
        0 to 0,
        0 to 1
    )

    fun getAllAdj(grid: Array<IntArray>, row: Int, col: Int, col2: Int, dp: MutableList<MutableList<MutableList<Int>>>) : List<Int> {
        val width = grid[0].size
        val ans = mutableListOf<Int>()
        displacements.forEach { (d1, d2) ->
            if (col + d1 >= 0 && col + d1 < width && col2 + d2 >= 0 && col2 + d2 < width && dp[row - 1][col + d1][col2 + d2] != -1) {
                val oldValue = dp[row - 1][col + d1][col2 + d2]
                ans.add(oldValue + grid[row][col] + if (col2 != col) grid[row][col2] else 0)
            }
        }
        return ans
    }




}


fun main() {
    val soln = `1463`()
    println(soln.cherryPickup(
        arrayOf(
            intArrayOf(3,1,1),
            intArrayOf(2,5,1),
            intArrayOf(1,5,5),
            intArrayOf(2,1,1),
        )
    ))





}

fun majorityElement(nums: IntArray): Int {
    val counts = mutableMapOf<Int,Int>()
    for (i in 0 until nums.size) {
        val updated = counts.getOrDefault(nums[i], 0) + 1

        if (updated > nums.size / 2) return nums[i]
        counts[nums[i]] = updated

    }
    return -1
}