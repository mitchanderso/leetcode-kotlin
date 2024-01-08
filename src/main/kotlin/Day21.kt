
import java.io.File
import java.util.*

fun main() {
    val challenge = Day21()
    val file = File(Day21::class.java.getResource("/day21/input.txt").file)
    challenge.p1(file)


}

class Day21 {

    val directions = listOf(
        -1 to 0,
        1 to 0,
        0 to 1,
        0 to -1
    )

    fun p1(file: File) {
        val grid = mutableListOf<String>()

        var start = 0 to 0
        var yy = 0
        file.forEachLine { line ->

            if (line.indexOf('S') >= 0) {
                start = line.indexOf('S') to yy
            }
            grid.add(line)
            yy++
        }

        val q = LinkedList<Pair<Int, Int>>()
        q.offer(start)
        var step = 0
        while (q.isNotEmpty() && step != 64) {
            val qSize = q.size
            for (i in 0 until qSize) {
                val (x, y) = q.poll()
                directions.forEach { (xDisp, yDisp) ->
                    val newX = x + xDisp
                    val newY = y + yDisp
                    val qSet = q.toMutableSet()
                    if (newX >= 0 && newY >= 0 && newY < grid.size && newX < grid[0].length && grid[newY][newX] != '#' && !qSet.contains(newX to newY)) {
                        q.offer(newX to newY)
                    }
                }
            }
            step++
            val locationsSet = q.toMutableSet()
            println("After $step step we can reach ${locationsSet.size} locations")
        }
    }
}