package LC2024

class `70` {
    fun climbStairs(n: Int): Int {
        return climbStairsRecursive(n, mutableMapOf())
    }

    fun climbStairsRecursive(n: Int, memo: MutableMap<Int, Int>) : Int {
        if (n == 2) return 2
        if (n == 1) return 1
        if (memo.containsKey(n)) return memo[n]!!
        memo[n] = climbStairsRecursive(n - 1, memo) + climbStairsRecursive(n - 2, memo)
        return memo[n]!!
    }
}

fun main() {
    val soln = `70`()
    println(soln.climbStairs(4))
}