package LC2024

class `3128` {
    fun numberOfRightTriangles(grid: Array<IntArray>): Long {
        var ans = 0L
        val height = grid.size
        val width = grid.first().size
        val columnOnes = Array<Int> (width) { 0 }
        val rowOnes = Array<Int> (height) { 0 }
        for (r in 0 until height) {
            for (c in 0 until width) {
                if (grid[r][c] == 1) {
                    rowOnes[r]++
                    columnOnes[c]++
                }
            }
        }

        for (r in 0 until height) {
            for (c in 0 until width) {
                if (grid[r][c] == 1) {
                    ans += ((rowOnes[r] - 1) * (columnOnes[c] - 1))
                }
            }
        }


        return ans
    }




}



fun main() {
    val soln = `3127`()




}