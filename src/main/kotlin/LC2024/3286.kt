package LC2024

import java.util.*

class `3286` {
    data class location(var health: Int, val pos: Pair<Int, Int>)
    val adj = listOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)
    fun findSafeWalk(grid: List<List<Int>>, health: Int): Boolean {
        val m = grid.size
        val n = grid[0].size
        // dijkstras
        val pq = PriorityQueue<location>(compareBy { it.health })
        val remainingHealth = mutableMapOf<Pair<Int, Int>, Int>()
        remainingHealth[0 to 0] = health - grid[0][0]

        pq.offer(location(health - grid[0][0], 0 to 0))
        // We want to get to m -1, n - 1

        while (pq.isNotEmpty()) {
            val current = pq.poll()

            // For all adjacent nodes
            adj.forEach { (xDisp, yDisp) ->
                val newX = current.pos.first + xDisp
                val newY = current.pos.second + yDisp

                if (newX >= 0 && newX < n && newY >= 0 && newY < m) {
                    // Reachable
                    val possibleRemainingHealth = remainingHealth[current.pos.first to current.pos.second]!! - if (grid[newY][newX] == 1) 1 else 0

                    if (possibleRemainingHealth >= 1 && possibleRemainingHealth > remainingHealth.getOrDefault(newX to newY, Int.MIN_VALUE)) {
                        remainingHealth[newX to newY] = possibleRemainingHealth
                        pq.offer(location(possibleRemainingHealth, newX to newY))
                    }

                }
            }
        }

        return remainingHealth.getOrDefault(n - 1 to m - 1, - 1) >= 1
    }
}

fun main() {
    val soln = `3286`()
    println(soln.findSafeWalk(
        listOf(
            listOf(0,1,0,0,0),
            listOf(0,1,0,1,0),
            listOf(0,0,0,1,0),
        ),
        1
    ))
}