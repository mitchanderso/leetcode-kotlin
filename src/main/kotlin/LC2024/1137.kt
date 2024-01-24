package LC2024

class `1137` {
    val cache = mutableMapOf<Int, Int>(0 to 0, 1 to  1, 2 to 1)

    fun tribonacci(n: Int): Int {
        if (cache.containsKey(n)) return cache[n]!!
        else {
            cache[n] = tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3)
            return cache[n]!!
        }

    }


}

fun main() {
    val soln = `1137`()
    println(soln.tribonacci(25))
}