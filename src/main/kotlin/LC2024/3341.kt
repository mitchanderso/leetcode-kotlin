package LC2024

import java.util.*
import kotlin.math.max

class `3341` {
    data class Node (val x: Int, val y: Int, val time: Int)
    fun minTimeToReach(moveTime: Array<IntArray>): Int {
        val width = moveTime[0].size
        val height = moveTime.size
        val bestTimeToReach = Array(height) { Array<Int>(width) { Int.MAX_VALUE} }
        bestTimeToReach[0][0] = 0

        val pq = PriorityQueue<Node>(compareBy { it.time })

        pq.offer(Node(0,0,0))
        val directions = listOf(
            -1 to 0,
            1 to 0,
            0 to 1,
            0 to -1
        )
        while (pq.isNotEmpty()) {
            val current = pq.poll()

            // Check the surrounding ones
            directions.forEach { (xDisp, yDisp) ->
                val newX = current.x + xDisp
                val newY = current.y + yDisp

                if (newX >= 0 && newX < width && newY >= 0 && newY < height) {
                    // Time taken
                    val timeToNeighbor = max(current.time, moveTime[newY][newX]) + 1
                    if (timeToNeighbor < bestTimeToReach[newY][newX]) {
                        bestTimeToReach[newY][newX] = timeToNeighbor
                        pq.offer(Node(newX, newY, timeToNeighbor))
                    }
                }
            }
        }

        return bestTimeToReach[height - 1][width - 1]

    }
}

fun main() {
    val soln = `3341`()
    println(soln.minTimeToReach(
        arrayOf(
            intArrayOf(0,99,1),
            intArrayOf(2,3,4),
            intArrayOf(1,7,10),

        )
    )
    )
}