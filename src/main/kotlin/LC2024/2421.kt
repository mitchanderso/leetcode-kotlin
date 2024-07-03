package LC2024

import java.util.*

class `2421` {

    fun numberOfGoodPaths(vals: IntArray, edges: Array<IntArray>): Int {
        val adjacency = mutableMapOf<Int, MutableList<Int>>()

        edges.forEach { (from, to) ->
            val existingFrom = adjacency.getOrDefault(from, mutableListOf())
            existingFrom.add(to)
            adjacency[from] = existingFrom

            val existingTo = adjacency.getOrDefault(to, mutableListOf())
            existingTo.add(from)
            adjacency[to] = existingTo
        }

        val valsWithIndex = vals.mapIndexed { index, i -> i to index }.groupBy { it.first }.filter { it.value.size > 1 }

        var paths = 0
        valsWithIndex.forEach { (k, v) ->
            for (i in 0 until v.size) {
                for (j in i + 1 until v.size) {
                    if (i != j) {
                        println("Checking for a path between ${v[i].second} to ${v[j].second}")
                        val visited = mutableSetOf<Int>()
                        val q = LinkedList<Int>()
                        val dest = v[j].second
                        q.offer(v[i].second)
                        visited.add(v[i].second)
                        val startValue = v[i].first
                        while (q.isNotEmpty()) {
                            val current = q.poll() // Node Index
                            if (current == dest) {
                                println("Found a path from ${v[i].second} to ${v[j].second}")
                                paths++
                            }
                            adjacency.getOrDefault(current, mutableListOf()).forEach {neighbor ->
                                if (vals[neighbor] <= startValue && neighbor !in visited) {
                                    q.offer(neighbor)
                                    visited.add(neighbor)
                                }
                            }
                        }
                    }
                }
            }
        }

        return paths + vals.size

    }



}



fun main() {
    val soln = `2421`()
    println(soln.numberOfGoodPaths(
        intArrayOf(1,3,2,1,3),
        arrayOf(
            intArrayOf(0,1),
            intArrayOf(0,2),
            intArrayOf(2,3),
            intArrayOf(2,4),
        )

    ))

    println(soln.numberOfGoodPaths(
        intArrayOf(1,1,2,2,3),
        arrayOf(
            intArrayOf(0,1),
            intArrayOf(1,2),
            intArrayOf(2,3),
            intArrayOf(2,4),
        )

    ))



}