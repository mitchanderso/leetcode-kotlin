package LC2024

class `1331` {
    fun arrayRankTransform(arr: IntArray): IntArray {
        if(arr.isEmpty()) return IntArray(0)
        val ranks = mutableMapOf<Int, Int>()
        val sorted = arr.sorted()
        var rank = 1
        ranks[sorted[0]] = rank
        for (i in 1 until sorted.size) {
            if (sorted[i] != sorted[i - 1]) rank++
            ranks[sorted[i]] = rank
        }

        val ans = arr.map { ranks[it]!! }
        return ans.toIntArray()
    }
}

fun main() {
    val soln = `1331`()
    println(soln.arrayRankTransform(intArrayOf(37,12,28,9,100,56,80,5,12)).toList())
}