package LC2024

import kotlin.math.max

class `3290` {
    fun maxScore(a: IntArray, b: IntArray): Long {
        // We can only have 4 positions of a
        // For each position of a, we can have b.size options of B
        val memo = Array<Array<Long>> (4) { Array<Long> (b.size) { -1 } }
        return dfs(0, 0, a, b, memo)
    }

    fun dfs(ai: Int, bi: Int, a: IntArray, b: IntArray, memo: Array<Array<Long>>) : Long {
        if (ai >= a.size) return 0
        if (bi >= b.size) return (-100e9).toLong()

        if (memo[ai][bi] != -1L) return memo[ai][bi]

        // Take the current values
        val take = dfs(ai + 1, bi + 1, a, b, memo) + (a[ai].toLong() * b[bi].toLong())
        val dontTake = dfs(ai, bi + 1, a, b, memo)

        memo[ai][bi] = max(take, dontTake)

        return memo[ai][bi]
    }
}

fun main() {
    val soln = `3290`()
    println(soln.maxScore(intArrayOf(100000,100000,100000,100000), intArrayOf(-100000,-100000,-100000,-100000)))
}