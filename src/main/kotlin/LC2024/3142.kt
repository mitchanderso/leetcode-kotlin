package LC2024


class `3142`() {
    fun satisfiesConditions(grid: Array<IntArray>): Boolean {
        grid.forEachIndexed { rowIdx, row ->
            row.forEachIndexed { colIdx, colVal ->
                if (rowIdx < grid.size - 1 && grid[rowIdx][colIdx] != grid[rowIdx + 1][colIdx]) return false
                if ( colIdx < grid[0].size - 1 && grid[rowIdx][colIdx] == grid[rowIdx][colIdx + 1]) return false
            }

        }
        return true
    }

}


fun main() {
    println(`3142`().satisfiesConditions(
        arrayOf(
            intArrayOf(1),
            intArrayOf(2),
            intArrayOf(3),
        )
    ))

}