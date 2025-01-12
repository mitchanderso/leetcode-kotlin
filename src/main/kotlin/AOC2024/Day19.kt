package AOC2024


import java.io.File


class Day19 {
    private fun possible(tp: String, towels: List<String>, i: Int, workingPattern: String): Boolean {
        if (workingPattern == tp) return true
        if (i >= tp.length) return false

        var possible = false

        for (j in 0 until towels.size) {
            val towel = towels[j]
            if (possible) break
            // If adding that towel on to the end of the working pattern, creates a valid pattern, then go for it
            val newWorkingPattern = workingPattern + towel
            if (newWorkingPattern.length <= tp.length && newWorkingPattern == tp.substring(0, newWorkingPattern.length)) {
                possible = possible(tp, towels, i + towel.length, newWorkingPattern)
            }
        }

        return possible
    }

    val memo = mutableMapOf<String, MutableMap<Int, Int>>()

    private fun makeDesign(design: String, cache: MutableMap<String, Long> = mutableMapOf(), patterns: List<String>): Long =
        if (design.isEmpty()) 1
        else cache.getOrPut(design) {
            val x = patterns.filter { design.startsWith(it) }.sumOf {
                makeDesign(design.removePrefix(it), cache, patterns)
            }
            x
        }

    fun p1(towelsFile: File, towelPatternFile: File) : Long {
        val towels = towelsFile.parse()[0].split(",").map { it.trim() }
        val towelPatterns = towelPatternFile.parse()

        var ans = 0L
        towelPatterns.forEach { tp ->
            if (possible(tp, towels, 0, "")) {
                println("Pattern $tp is possible")
                ans++
            } else {
                println("Pattern $tp is not possible")
            }
        }
        return ans
    }


    fun File.parse(): List<String> {
        return readLines()
    }
}




fun main() {
    val soln = Day19()
    val towels = File(Day19::class.java.getResource("/AOC2024/day19/towels.txt").file)
    val towelPattern = File(Day19::class.java.getResource("/AOC2024/day19/towelPattern.txt").file)
//    println( soln.p1(towels, towelPattern))


}