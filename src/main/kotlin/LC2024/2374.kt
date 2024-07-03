package LC2024

class `2374` {
    fun edgeScore(edges: IntArray): Int {
        val edgesArray = LongArray ( edges.size ) { 0 }
        edges.forEachIndexed { from, pointsTo ->
            edgesArray[pointsTo] = edgesArray[pointsTo] + from.toLong()
        }
        return edgesArray.indices.maxBy { edgesArray[it] }
    }
}

fun main() {
    val soln = `2374`()
    println(soln.edgeScore(
        intArrayOf(3,3,3,0)
    ))
}