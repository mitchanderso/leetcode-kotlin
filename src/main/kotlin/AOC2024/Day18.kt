package AOC2024


import java.io.File
import java.util.*


class Day18 {

    private val DIRECTIONS = listOf(
        1 to 0,
        -1 to 0,
        0 to 1,
        0 to -1
    )

    data class Point(val x: Int, val y: Int, val dist: Int)

    fun p1(file: File) : Int {
        val bytes = file.parse()
        return bfs(bytes, 1024)
    }

    fun p2(file: File) : String {
        val bytes = file.parse()

        // Do a binary search
        var left = 1
        var right = bytes.size
        var smallest = Int.MAX_VALUE
        while (left <= right) {
            val mid = (left + right) / 2
            val res = bfs(bytes, mid)
            if (res == -1) {
                // Its not possible, but we might be able to go smaller
                smallest = mid
                right = mid - 1
            } else if (res >= 0) {
                // Its still possible, add more bytes
                left = mid + 1
            }
        }


        return bytes[smallest - 1]

    }

    fun bfs(bytes: List<String>, take: Int) : Int {
        val map = MutableList(71) { MutableList (71) { '.' } }

        repeat(take) {
            val byteString = bytes[it]
            val x = Integer.parseInt(byteString.substringBefore(',') )
            val y = Integer.parseInt(byteString.substringAfter(','))
            map[y][x] = '#'
        }

        val q = LinkedList<Point>()
        val visited = mutableSetOf<Pair<Int, Int>>()
        val width = map.first().size
        val height = map.size
        visited.add(0 to 0)
        q.offer(Point(0,0,0))

        while (q.isNotEmpty()) {
            val (x, y, dist) = q.poll()
            if (x == width - 1 && y == height - 1) {
                return dist
            }
            DIRECTIONS.forEach { (dx, dy) ->
                val nx = dx + x
                val ny = dy + y
                if (ny >= 0 && ny < height
                    && nx >= 0 && nx < width
                    && nx to ny !in visited
                    && map[ny][nx] == '.') {
                    q.offer(Point(nx, ny, dist + 1))
                    visited.add(nx to ny)
                }
            }
        }

        return -1
    }

    fun File.parse(): List<String> {
        return readLines()

    }
}




fun main() {
    val soln = Day18()
    val file = File(Day18::class.java.getResource("/AOC2024/day18/input.txt").file)
    println( soln.p1(file))
    println( soln.p2(file))

}