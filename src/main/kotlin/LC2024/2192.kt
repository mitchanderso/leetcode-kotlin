package LC2024

import java.util.*

class `2192` {
    val settledAncestors = mutableMapOf<Int, List<Int>>()

    fun getAncestors(n: Int, edges: Array<IntArray>): List<List<Int>> {
        val adj = mutableMapOf<Int, MutableList<Int>>()
        val ancestry = mutableMapOf<Int, MutableList<Int>>()



        edges.forEach { (from, to) ->
            adj[from] = adj.getOrDefault(from, mutableListOf()).also { it.add(to) }
            ancestry[to] = ancestry.getOrDefault(to, mutableListOf()).also { it.add(from) }
        }

        for (i in 0 until n) {
            bfs(i, ancestry)
        }


        return settledAncestors.map {
            it.value
        }
    }

    fun bfs(start: Int, ancestry: MutableMap<Int, MutableList<Int>>) {
        val localSettled = mutableListOf<Int>()
        val bfsQ = LinkedList<Int>().also { it.offer(start) }
        val visited = mutableSetOf<Int>().also { it.add(start) }
        while (bfsQ.isNotEmpty()) {
            val current = bfsQ.poll()
            val ancestors = ancestry.getOrDefault(current, emptyList())
            ancestors.forEach {
                if (it !in visited) {
                    bfsQ.offer(it)
                    localSettled.add(it)
                    visited.add(it)
                }
            }
        }
        settledAncestors[start] = localSettled.sorted()
    }
}

fun main() {
    val soln = `2192`()
    println(soln.getAncestors(
        8,
        arrayOf(
            intArrayOf(0,3),
            intArrayOf(0,4),
            intArrayOf(1,3),
            intArrayOf(2,4),
            intArrayOf(2,7),
            intArrayOf(3,5),
            intArrayOf(3,6),
            intArrayOf(3,7),
            intArrayOf(4,6),
        )
    ))
}