package LC2024

import java.util.*

class `1568` {

    val adjacent = listOf<Pair<Int, Int>>(0 to 1, 0 to -1, 1 to 0, -1 to 0)

    fun minDays(grid: Array<IntArray>): Int {
        var landCount = 0
        var land = 0 to 0
        var lands = mutableListOf<Pair<Int, Int>>()
        for (y in 0 until grid.size) {
            for (x in 0 until grid[0].size) {
                if (grid[y][x] == 1) {
                    landCount++
                    land = y to x
                    lands.add(y to x)
                }
            }
        }

        // If there is no land at all, no need to do anything
        if (landCount == 0) return 0
        if (landCount == 1) return 1

        // Check to see if there is already more than one island, if so, do nothing
        val reached = bfs(grid, land.first, land.second)
        if (reached != landCount) return 0

        for (i in 0 until lands.size) {
            // Remove the current land
            grid[lands[i].first][lands[i].second] = 0

            // Use any other land
            val randomLand = if (i == 0) 1 else 0
            val foundLand = bfs(grid, lands[randomLand].first, lands[randomLand].second)

            // If we can still reach all of them (n - 1) lands, then there is no disconnect
            // If we cannot reach n - 1 then we have an answer
            if (foundLand < landCount - 1) {
                return 1
            }

            // Add it back
            grid[lands[i].first][lands[i].second] = 1
        }

        return 2
    }

    fun bfs(grid: Array<IntArray>, yy: Int, xx: Int) : Int{
        val q = LinkedList<Pair<Int, Int>>()
        q.offer(yy to xx)
        val visited = mutableSetOf<Pair<Int, Int>>()
        visited.add(yy to xx)

        while (q.isNotEmpty()) {
            val (y, x) = q.poll()

            // Check adjacent
            adjacent.forEach { (xDisp, yDisp) ->
                val newX = x + xDisp
                val newY = y + yDisp
                if (newX >= 0 && newY >= 0 && newX < grid[0].size && newY < grid.size && grid[newY][newX] == 1 && newY to newX !in visited) {
                    q.offer(newY to newX)
                    visited.add(newY to newX)
                }
            }
        }

        return visited.size
    }
}

fun main() {
    val soln = `1568`()
    println(soln.minDays(
        arrayOf(
            intArrayOf(0,1),
            intArrayOf(1,1)
        )
    ))
}