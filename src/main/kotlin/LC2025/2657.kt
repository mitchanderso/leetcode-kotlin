package LC2025

class `2657` {
    fun findThePrefixCommonArray(A: IntArray, B: IntArray): IntArray {
        val seenA = mutableSetOf<Int>()
        val seenB = mutableSetOf<Int>()
        val ans = mutableListOf<Int>()
        for (i in 0 until A.size) {
            seenA.add(A[i])
            seenB.add(B[i])
            ans.add(
                (seenA intersect seenB).size
            )
        }

        return ans.toIntArray()
    }
}

fun main() {
    val soln = `2657`()
    println(soln.findThePrefixCommonArray(intArrayOf(1,3,2,4), intArrayOf(3,1,2,4)).toList())
}