import java.io.File
import java.lang.Math.*

fun main() {
    val challenge = Day11()
    val file = File(Day11::class.java.getResource("/day11/input.txt").file)
    challenge.p1(file)

}

class Day11 {

    fun manhattan(a: Pair<Int,Int>, b: Pair<Int, Int>) : Int {
        return abs( a.first - b.first) + abs(a.second - b.second)
    }

    fun p1(file: File) {

        var ans = 0L
        val map = mutableListOf<String>()
        val emptyRows = mutableListOf<Int>()
        val emptyCols = mutableListOf<Int>() // Add one after these ones
        val galaxies = mutableListOf<Pair<Int, Int>>()
        var y = 0
        file.forEachLine {
            map.add(it)
            it.forEachIndexed { index, cha -> if (cha == '#') galaxies.add(index to y)}
            if (it.none { cha -> cha == '#' }) emptyRows.add(y)
            y++
        }

        for (col in 0 until map[0].length) {
            var empty = true
            for (row in 0 until map.size) {
                if (map[row][col] == '#') empty = false
            }
            if (empty) emptyCols.add(col)
        }

        for (i in 0 until galaxies.size) {
            for (j in i + 1 until galaxies.size) {
                val galA = galaxies[i]
                val galB = galaxies[j]

                val maxX = max(galA.first, galB.first)
                val maxY = max(galA.second, galB.second)

                val minX = min(galA.first, galB.first)
                val minY = min(galA.second, galB.second)

                var extra = 0L
                for (i in minX until maxX) {
                    if (emptyCols.contains(i)) extra += (1_000_000L - 1L)
                }

                for (i in minY until maxY) {
                    if (emptyRows.contains(i)) extra += (1_000_000L - 1L)
                }

                val dist = manhattan(galA, galB)
                val totalDist = dist + extra
                ans += totalDist

                println("Points ${i + i} and ${j + 1} are $dist apart with $extra for a total of $totalDist")
            }
        }

        println("Answer is $ans")

    }



}

