package LC2024

import java.util.*

class `2368` {
    fun reachableNodes(n: Int, edges: Array<IntArray>, restricted: IntArray): Int {
        val adjacency = mutableMapOf<Int, MutableList<Int>>()
        val restrictedSet = restricted.toSet()

        edges.forEach { (from, to) ->
            val existingFrom = adjacency.getOrDefault(from, mutableListOf())
            existingFrom.add(to)
            adjacency[from] = existingFrom

            val existingTo = adjacency.getOrDefault(to, mutableListOf())
            existingTo.add(from)
            adjacency[to] = existingTo
        }

        var visited = 0
        val visitedNodes = mutableSetOf<Int>()
        val q = LinkedList<Int>()
        q.offer(0)
        visitedNodes.add(0)
        while (q.isNotEmpty()) {
            val first = q.poll()
            visited++
            val neighbors = adjacency.getOrDefault(first, mutableListOf())
            neighbors.forEach { neighbor ->
                if (neighbor !in restrictedSet && neighbor !in visitedNodes) {
                    q.offer(neighbor)
                    visitedNodes.add(neighbor)
                }
            }
        }
        return visited
    }


}



fun main() {
    val soln = `2368`()
    println(soln.reachableNodes(7,
        arrayOf(
            intArrayOf(0,1),
            intArrayOf(1,2),
            intArrayOf(3,1),
            intArrayOf(4,0),
            intArrayOf(0,5),
            intArrayOf(5,6),
        ),
        intArrayOf(4,5)
        ))




}