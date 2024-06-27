package LC2024

class `1791` {
    fun findCenter(edges: Array<IntArray>): Int {
        val indegree = Array (edges.size + 2) { 0 }
        edges.forEach { (u, v) ->
            indegree[v]++
        }

        var maxInDegree = 0
        var maxInDegreeNode = -1

        indegree.forEachIndexed { node, indegreeCount ->
            if (indegreeCount > maxInDegree) {
                maxInDegree = indegreeCount
                maxInDegreeNode = node
            }
        }

        return maxInDegreeNode
    }
}

fun main() {
}