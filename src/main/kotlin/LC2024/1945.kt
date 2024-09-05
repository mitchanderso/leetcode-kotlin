package LC2024

class `1945` {
    fun getLucky(s: String, k: Int): Int {
        var numeric = s.map { it - 'a' + 1 }.joinToString("")

        var sum = -1
        repeat( k ) {
            sum = numeric.map { it.digitToInt() }.sum()
            numeric = sum.toString()
        }
        return sum
    }
}

fun main() {
    val soln = `1945`()
    println(soln.getLucky("leetcode", 2))
}