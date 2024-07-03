package LC2024

class `3179` {
    fun valueAfterKSeconds(n: Int, k: Int): Int {
        val MODULO = 1_000_000_000 + 7
        val arr = IntArray (n) { 1 }
        repeat(k) {
            for (i in 1 until n) {
                arr[i] = (arr[i] + arr[i - 1]) % MODULO
            }
        }
        return arr[n - 1]
    }
}

fun main() {
    val soln = `3179`()
    println(soln.valueAfterKSeconds(5, 3))
}