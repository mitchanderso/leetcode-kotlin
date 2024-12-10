package AOC2024

import java.io.File
import java.util.*

class `Day10` {

    private val DIRECTIONS = listOf(
        1 to 0,
        -1 to 0,
        0 to 1,
        0 to -1
    )

    private infix fun Pair<Int, Int>.inside(strings: List<String>) =
        this.first >= 0 && this.first < strings[0].length && this.second >= 0 && this.second < strings.size


    private fun List<String>.peaks(trailHeadLocation: Pair<Int, Int>, allReachable: Boolean) : Int {
        var allReachablePeaks = 0
        val bfsQ = LinkedList<Pair<Int, Int>>()
        val peaks = mutableSetOf<Pair<Int, Int>>()
        bfsQ.offer(trailHeadLocation.first to trailHeadLocation.second)
        while (bfsQ.isNotEmpty()) {
            val (xx, yy) = bfsQ.poll()
            DIRECTIONS.forEach { (xDisp, yDisp) ->
                val newLocation = xx + xDisp to yy + yDisp
                if (newLocation inside this
                    && this[newLocation.second][newLocation.first].digitToInt() == this[yy][xx].digitToInt() + 1) {
                    if (this[newLocation.second][newLocation.first].digitToInt() == 9) {
                        peaks.add(newLocation)
                        allReachablePeaks++
                    }
                    else bfsQ.offer(newLocation)
                }
            }
        }
        return if (allReachable) allReachablePeaks else peaks.size
    }

    fun p1(file: File): Int {
        val map = file.parse()
        return map.flatMapIndexed { y, it ->
            it.mapIndexed { x, _ ->
                x to y
            }
        }.filter { map[it.second][it.first] == '0' }
            .sumOf { trailHeadLocation ->
                map.peaks(trailHeadLocation, false)
            }
    }

    fun p2(file:File) : Int {
        val map = file.parse()
        return map.flatMapIndexed { y, it ->
            it.mapIndexed { x, _ ->
                x to y
            }
        }.filter { map[it.second][it.first] == '0' }
            .sumOf { trailHeadLocation ->
                map.peaks(trailHeadLocation, true)
            }
    }

    fun File.parse() : List<String> {
        return readLines()
    }
}


fun main() {
    val soln = Day10()
    val file = File(Day10::class.java.getResource("/AOC2024/day10/input.txt").file)
    println(soln.p1(file))
    println(soln.p2(file))

}