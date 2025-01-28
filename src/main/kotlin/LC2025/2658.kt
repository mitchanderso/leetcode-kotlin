package LC2025

import java.util.*

class `2658` {

    val DIRECTIONS = mutableSetOf<Pair<Int, Int>>(
        0 to 1,
        0 to -1,
        1 to 0,
        -1 to 0
    )

    fun findMaxFish(grid: Array<IntArray>): Int {
        val possibleStartingLocations = mutableSetOf<Pair<Int, Int>>()
        val height = grid.size
        val width = grid[0].size
        for (y in 0 until height) {
            for (x in 0 until width) {
                if (grid[y][x] != 0) possibleStartingLocations.add(x to y)
            }
        }

        return possibleStartingLocations.maxOfOrNull {
            fish(it, grid)
        } ?: 0

    }

    fun fish(start: Pair<Int, Int>, grid: Array<IntArray>) : Int {
        val height = grid.size
        val width = grid[0].size
        val visited = mutableSetOf<Pair<Int, Int>>()
        val q = LinkedList<Pair<Int, Int>>()
        q.offer(start)
        visited.add(start)

        var fish = grid[start.second][start.first]

        while (q.isNotEmpty()) {
            val (cx, cy) = q.poll()

            DIRECTIONS.forEach { (dx, dy) ->
                val nx = dx + cx
                val ny = dy + cy
                if (nx >= 0 && nx < width
                    && ny >= 0 && ny < height
                    && nx to ny !in visited
                    && grid[ny][nx] > 0) {
                    visited.add(nx to ny)
                    q.offer(nx to ny)
                    fish += grid[ny][nx]
                }
            }
        }

        return fish
    }
}

fun main() {
    val soln = `2658`()
    println(
        soln.findMaxFish(
            arrayOf(
                intArrayOf(3,10,5,8)
            )
        )
    )

}