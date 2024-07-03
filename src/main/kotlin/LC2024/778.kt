package LC2024

import java.util.*
import kotlin.math.max

class `778` {

    data class Node(val x: Int, val y: Int, val cost: Int)

    val directions = listOf(
            -1 to 0,
            1 to 0,
            0 to -1,
            0 to 1
        )

    fun swimInWater(grid: Array<IntArray>): Int {
        val visited = mutableSetOf<Pair<Int, Int>>()
        val pq = PriorityQueue<Node>(compareBy { it.cost })
        pq.offer(Node(0,0,0))
        visited.add(0 to 0)
        var maxOnPath = -1
        while (pq.isNotEmpty()) {
            val (x,y,cost) = pq.poll()
            maxOnPath = max(maxOnPath, grid[y][x])
            if (x == grid[0].size - 1 && y == grid.size - 1) {
                println("Found a path, max is $maxOnPath")
                return maxOnPath
            }
            directions.forEach { (xdisp, ydisp) ->
                val newx = x + xdisp
                val newy = y + ydisp
                if (newx >= 0 && newx < grid[0].size && newy >=0 && newy < grid.size && newx to newy !in visited) {
                    pq.offer(Node(newx, newy, grid[newy][newx]))
                    visited.add(newx to newy)
                }
            }
        }

        return -1
    }
}

fun main() {
    val soln = `778`()
    println(soln.swimInWater(
      arrayOf(
          intArrayOf(0,1,2,3,4),
          intArrayOf(24,23,22,21,5),
          intArrayOf(12,13,14,15,16),
          intArrayOf(11,17,18,19,20),
          intArrayOf(10,9,8,7,6),
      )
    ))
}