package LC2024

class `2672` {
    fun colorTheArray(n: Int, queries: Array<IntArray>): IntArray {
        val colors = IntArray (n) { 0 }
        val ans = IntArray (queries.size) { 0 }
        var pairs = 0
        queries.forEachIndexed { qindex, (index, color) ->
            val previousColor = colors[index]
            val pleft = if (previousColor == 0 || index - 1 < 0) -1 else colors[index - 1]
            val pright = if (previousColor == 0 || index + 1 >= n) -1 else colors[index + 1]

            if (previousColor == pleft) {
                pairs--
            }
            if (previousColor == pright) {
                pairs--
            }

            val left = if (index - 1 < 0) -1 else colors[index - 1]
            val right = if ( index + 1 >= n) -1 else colors[index + 1]

            colors[index] = color
            if (left == color) pairs++
            if (right == color) pairs++
            ans[qindex] = pairs
            println()
        }
        return ans
    }
}

fun main() {
    val soln = `2672`()
    println(soln.colorTheArray(4,
        arrayOf(
            intArrayOf(0,2),
            intArrayOf(1,2),
            intArrayOf(3,1),
            intArrayOf(1,1),
            intArrayOf(2,1),
        )
        ).toList())
}