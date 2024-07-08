package LC2024

import kotlin.math.max

class `1446` {
    fun maxPower(s: String): Int {
        var max = 1
        var local = 1
        for (i in 1 until s.length) {
            if (s[i-1] == s[i]) local++
            else local = 1
            max = max(max, local)
        }
        return max
    }
}

fun main() {
    val soln = `1446`()
    println(soln.maxPower("cc"))
}