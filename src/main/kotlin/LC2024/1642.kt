package LC2024

import java.util.*
import kotlin.math.max


class `1642` {
    fun networkBecomesIdle(edges: Array<IntArray>, patience: IntArray): Int {
        val adjacency = mutableMapOf<Int, MutableList<Int>>()
        edges.forEach { (from, to) ->
            val fromList = adjacency.getOrDefault(from, mutableListOf())
            fromList.add(to)
            adjacency[from] = fromList

            val toList = adjacency.getOrDefault(to, mutableListOf())
            toList.add(from)
            adjacency[to] = toList
        }

        val distances = MutableList<Int> (patience.size) { 0 }
        val visited = mutableSetOf(0)
        // BFS to determine round trip distance to all nodes
        val q = LinkedList<Int>()
        q.offer(0)
        var dist = 0
        while (q.isNotEmpty()) {

            val sz = q.size
            for (i in 0 until sz) {
                val current = q.poll()
                distances[current] = dist * 2
                adjacency.getOrDefault(current, mutableListOf()).forEach { dest ->
                    if (dest !in visited) {
                        visited.add(dest)
                        q.offer(dest)
                    }
                }
            }
            dist++


        }

        var max = 0

        // Go through each node and find its settlement time
        for (i in 1 until patience.size) {
            val nodePatience = patience[i]
            val originalReply = distances[i]
            if (nodePatience >= originalReply) {
                println("Node $i settles at ${originalReply }")
                max = max(max, originalReply )
            } else {
                val lastSent = originalReply - 1 - ((originalReply - 1) % nodePatience)
                val settlement = lastSent + originalReply

                println("Node $i settles at $settlement")
                max = max(max, settlement)
            }

        }

        return max + 1
    }



}

fun main() {
    val soln = `1642`()
    println(soln.networkBecomesIdle(
        arrayOf(
            intArrayOf(5,7),
            intArrayOf(15,18),
            intArrayOf(12,6),
            intArrayOf(5,1),
            intArrayOf(11,17),
            intArrayOf(3,9),
            intArrayOf(6,11),
            intArrayOf(14,7),
            intArrayOf(19,13),
            intArrayOf(13,3),
            intArrayOf(4,12),
            intArrayOf(9,15),
            intArrayOf(2,10),
            intArrayOf(18,4),
            intArrayOf(5,14),
            intArrayOf(17,5),
            intArrayOf(16,2),
            intArrayOf(7,1),
            intArrayOf(0,16),
            intArrayOf(10,19),
            intArrayOf(1,8),
        ),
        intArrayOf(0,2,1,1,1,2,2,2,2,1,1,1,2,1,1,1,1,2,1,1)
    ))

    println(soln.networkBecomesIdle(
        arrayOf(
            intArrayOf(0,1),
            intArrayOf(1,2),
        ),
        intArrayOf(0,2,1)
    ))

    println(soln.networkBecomesIdle(
        arrayOf(
            intArrayOf(0,1),
            intArrayOf(0,2),
            intArrayOf(1,2),
        ),
        intArrayOf(0,10,10)
    ))

}