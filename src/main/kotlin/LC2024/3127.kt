package LC2024

class `3127` {
    fun canMakeSquare(grid: Array<CharArray>): Boolean {
        grid.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { colIndex, square ->
                // If it is the top left
                var adjacentWhite = 0
                var adjacentBlack = 0

                if (rowIndex + 1 < 3 ) {
                    when (grid[rowIndex + 1][colIndex]) {
                        'W' -> adjacentWhite++
                        'B' -> adjacentBlack++
                    }
                }
                if (rowIndex + 1 < 3 && colIndex + 1 < 3) {
                    when (grid[rowIndex + 1][colIndex + 1]) {
                        'W' -> adjacentWhite++
                        'B' -> adjacentBlack++
                    }
                }
                if (colIndex + 1 < 3) {
                    when (grid[rowIndex][colIndex + 1]) {
                        'W' -> adjacentWhite++
                        'B' -> adjacentBlack++
                    }
                }

                val totalAdjacent = adjacentBlack + adjacentWhite

                if (totalAdjacent == 3 && square == 'W' && adjacentBlack == 1 || square == 'B' && adjacentWhite == 3 || square == 'W' && adjacentWhite == 3) return true
                if (totalAdjacent == 3 && square == 'B' && adjacentWhite == 1 || square == 'W' && adjacentBlack == 3 || square == 'B' && adjacentBlack == 3) return true

            }
        }

        return false
    }




}



fun main() {
    val soln = `3127`()




}