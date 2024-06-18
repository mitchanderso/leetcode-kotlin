package LC2024

import kotlin.collections.set
import kotlin.math.max

class `3186` {
    private fun dfs(power:List<Int>, pos: Int, counts: Map<Int, Int>, memo: MutableMap<Int, Long>): Long {
        if (pos >= power.size) return 0

        // Cache
        if (memo.containsKey(pos)) return memo[pos]!!

        var next = pos + 1
        while (next < power.size && power[next] - power[pos] <= 2) next++
        val take = (counts[power[pos]]!!.toLong() * power[pos].toLong()) + dfs(power, next, counts, memo)
        val dontTake = dfs(power, pos + 1, counts, memo)

        memo[pos] = max(take, dontTake)
        return memo[pos]!!
    }

    fun maximumTotalDamage(power: IntArray): Long {
        val counts = mutableMapOf<Int, Int>()
        power.forEach { pwr -> counts[pwr] = counts.getOrDefault(pwr, 0) + 1 }


        val memo = mutableMapOf<Int, Long>()
        return dfs(power.toSortedSet().toList(), 0, counts, memo)
    }
}

fun main() {
    println(Int.MAX_VALUE)
    println(1000000000)
    val soln = `3186`()
    println(soln.maximumTotalDamage(intArrayOf(1,1,3,4)))
}