package LC2024

class `3223` {
    fun minimumLength(s: String): Int {
        var deleted = 0
        val locations = mutableMapOf<Char, Int>()
        s.forEachIndexed { index, c ->
            locations[c] = locations.getOrDefault(c ,0) + 1
            if (locations[c]!! == 3) {
                locations[c] = 1
                deleted+=2
            }
        }

        return s.length - deleted

    }
}

fun main() {
    val soln = `3223`()
    println(soln.minimumLength("abaacbcbb"))
}