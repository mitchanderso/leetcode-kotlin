package LC2024

import java.util.*

class `1334` {

    fun findTheCity(n: Int, edges: Array<IntArray>, distanceThreshold: Int): Int {
        val graph = mutableMapOf<Int, MutableList<Pair<Int, Int>>>()
        edges.forEach { (to, from, weight) ->
            graph[from] = graph.getOrDefault(from, mutableListOf()).also { it.add(to to weight) }
            graph[to] = graph.getOrDefault(to, mutableListOf()).also { it.add(from to weight) }
        }

        var minSoFar = -1 to Int.MAX_VALUE

        for(node in 0 until n) {
            val visited = mutableSetOf<Int>()
            val distances = mutableMapOf<Int, Int>()
            val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
            pq.offer(node to 0)
            var reachableWithinThreshold = 0
            while (pq.isNotEmpty()) {
                val (current, currentDistance) = pq.poll()
                if (current !in visited) {
                    reachableWithinThreshold++
                    visited.add(current)
                } else continue



                graph[current]?.forEach { (neighbor, distanceToNeighbor) ->
                    if (neighbor !in visited) {
                        val distUsingCurrent = currentDistance + distanceToNeighbor
                        if (distUsingCurrent < distances.getOrDefault(neighbor, Int.MAX_VALUE) && distUsingCurrent <= distanceThreshold) {
                            distances[neighbor] = distUsingCurrent
                            pq.offer(neighbor to distances[neighbor]!!)
                        }
                    }
                }
            }
            if (reachableWithinThreshold < minSoFar.second || (reachableWithinThreshold == minSoFar.second && node > minSoFar.first)) {
                minSoFar = node to reachableWithinThreshold
            }

        }

        return minSoFar.first
    }
}

fun main() {
    val soln = `1334`()
    println(soln.findTheCity(5,
        arrayOf(
            intArrayOf(0,1,2),
            intArrayOf(0,4,8),
            intArrayOf(1,2,3),
            intArrayOf(1,4,2),
            intArrayOf(2,3,1),
            intArrayOf(3,4,1),
        ),
        2
        ))
}