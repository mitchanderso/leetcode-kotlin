package LC2024

class `1051` {
    fun heightChecker(heights: IntArray): Int {
        val sorted = heights.sorted()
        var tally = 0
        for (i in 0 until heights.size) {
            if (heights[i] != sorted[i]) tally++
        }
        return tally
    }
}

fun main() {
    val soln = `1051`()
    println(soln.heightChecker(intArrayOf(1,1,4,2,1,3)))
}