package LC2024

import kotlin.math.max


class `3168` {

    fun minimumChairs(s: String): Int {
        var seated = 0
        var max = 0

        s.forEach { action ->
            when (action) {
                'E' -> seated++
                else -> seated--
            }
            max = max(seated, max)
        }

        return max
    }
}




fun main() {
    val soln = `3168`()
    println(soln.minimumChairs("ELELEEL"))
}