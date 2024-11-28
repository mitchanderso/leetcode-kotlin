package LC2024

import java.util.PriorityQueue

class `2290` {
    fun minimumObstacles(grid: Array<IntArray>): Int {
        val directions = listOf(
            -1 to 0,
            1 to 0,
            0 to -1,
            0 to 1
        )

        val pq = PriorityQueue<Pair<Pair<Int, Int>, Int>>( compareBy { it.second })

        pq.offer((0 to 0) to 0)

        val best = Array (grid.size) {
            IntArray (grid[0].size) { Int.MAX_VALUE }
        }

        while (pq.isNotEmpty()) {
            val (pos, removals) = pq.poll()

            directions.forEach { (xdisp, ydisp) ->
                val newx = pos.first + xdisp
                val newy = pos.second + ydisp



                if (newy >= 0 && newy < grid.size && newx >= 0 && newx < grid[0].size) {
                    val isRemovalSquare = grid[newy][newx]
                    val totalRemovalValue = removals + isRemovalSquare
                    if (totalRemovalValue < best[newy][newx]) {
                        best[newy][newx] = totalRemovalValue
                        pq.offer( (newx to newy) to totalRemovalValue)
                    }

                }
            }

        }

        return best[grid.size - 1][grid[0].size - 1]


    }
}

fun main() {
    val soln = `2290`()
//    println(soln.minimumObstacles(
//        arrayOf(
//            intArrayOf(0,1,1),
//            intArrayOf(1,1,0),
//            intArrayOf(1,1,0)
//        )
//    ))

    println(soln.minimumObstacles(
        arrayOf(
            intArrayOf(0,1,0,0,0),
            intArrayOf(0,1,0,1,0),
            intArrayOf(0,0,0,1,0)
        )
    ))
}