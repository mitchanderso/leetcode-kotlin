package LC2024

class `2924` {
    fun findChampion(n: Int, edges: Array<IntArray>): Int {
        if (n == 1) return 0
        // Find the in-degree of each node, the one that has the lowest wins, if equal, -1
        val indegree = IntArray(n) { 0 }

        edges.forEach { (from, to) ->
            indegree[to]++
        }

        val assoc = indegree
            .mapIndexed { index, i -> i to index }
            .sortedBy { it.first }

        return if (assoc[0].first != assoc[1].first) assoc[0].second
        else -1

    }
}

fun main() {
    val soln = `2924`()
    println(soln.findChampion(
        3,
        arrayOf(
            intArrayOf(2,0),
            intArrayOf(2,1),
        )
    ))
}