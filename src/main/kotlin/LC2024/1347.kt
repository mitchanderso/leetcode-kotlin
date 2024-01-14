package LC2024


class `1347` {
    fun minSteps(s: String, t: String): Int {
        var differences = 0
        val tAsList = t.toMutableList()
        s.forEach { sChar ->
            if (!tAsList.contains(sChar)) differences++
            else tAsList.remove(tAsList.first { it == sChar })
        }
        return differences
    }
}

fun main() {
    val soln = `1347`()
    println(soln.minSteps("leetcode", "practice"))
    println(soln.minSteps("anagram", "mangaar"))

}