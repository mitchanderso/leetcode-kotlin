import java.io.File
import java.lang.Math.max
import java.util.LinkedList

fun main() {
    val challenge = Day10()
    val file = File(Day10::class.java.getResource("/day10/simple.txt").file)
    challenge.p1(file)
//    challenge.p2(file)

}

class Day10 {

//    | is a vertical pipe connecting north and south.
//    - is a horizontal pipe connecting east and west.
//    L is a 90-degree bend connecting north and east.
//    J is a 90-degree bend connecting north and west.
//    7 is a 90-degree bend connecting south and west.
//    F is a 90-degree bend connecting south and east.
//    . is ground; there is no pipe in this tile.

    enum class Cardinal {
        NORTH,
        SOUTH,
        EAST,
        WEST,
        UNVISITED
    }


    fun p1(file: File) {

        val map = mutableListOf<String>()
        val visited = mutableListOf<MutableList<Boolean>>()
        val distances = mutableListOf<MutableList<Int>>()
        val heading = mutableListOf<MutableList<Cardinal>>()
        var start = Triple(1,1, Cardinal.UNVISITED)
        var y = 0
        file.forEachLine { line ->
            map.add(line)
            if (line.indexOf('S') >= 0) {
                start =  Triple(line.indexOf('S'), y, Cardinal.UNVISITED)
            }
            visited.add( Array (line.length) { false }.toMutableList())
            distances.add( Array (line.length) { -1 }.toMutableList())
            heading.add( Array (line.length) { Cardinal.UNVISITED }.toMutableList())
            y++
        }

        val height = map.size
        val width = map[0].length


        val q = LinkedList<Triple<Int, Int, Cardinal>>()
        var dist = 0
        q.offer(start)
        while (q.isNotEmpty()) {
            val qSize = q.size
            for (i in 0 until qSize) {
                val (x, y, card) = q.poll()
                distances[y][x] = dist
                visited[y][x] = true
                when (card) {
                    Cardinal.UNVISITED -> {
                        if (y + 1 < height && listOf('|', 'L', 'J').contains(map[y + 1][x]) && !visited[y + 1][x]) {
                            val pipe = map[y + 1][x]
                            val facing = if (pipe == '|') Cardinal.SOUTH else if (pipe == 'L') Cardinal.EAST else Cardinal.WEST
                            q.offer(Triple(x, y + 1, facing))
                        }

                        if (y - 1 >= 0 && listOf('|', '7', 'F').contains(map[y - 1][x]) && !visited[y - 1][x]) {
                            val pipe = map[y - 1][x]
                            val facing = if (pipe == '|') Cardinal.NORTH else if (pipe == '7') Cardinal.WEST else Cardinal.EAST
                            q.offer(Triple(x, y - 1, facing))
                        }

                        if (x + 1 < width && listOf('-', 'J', '7').contains(map[y][x + 1]) && !visited[y][x + 1]) {
                            val pipe = map[y][x + 1]
                            val facing = if (pipe == '-') Cardinal.EAST else if (pipe == 'J') Cardinal.NORTH else Cardinal.SOUTH
                            q.offer(Triple(x + 1, y, facing))
                        }

                        if (x - 1 >= 0 && listOf('-', 'F', 'L').contains(map[y][x - 1]) && !visited[y][x - 1]) {
                            val pipe = map[y][x - 1]
                            val facing = if (pipe == '-') Cardinal.WEST else if (pipe == 'F') Cardinal.SOUTH else Cardinal.NORTH
                            q.offer(Triple(x - 1, y, facing))
                        }
                    }
                    Cardinal.WEST -> {
                        if (x - 1 >= 0 && listOf('-', 'F', 'L').contains(map[y][x - 1]) && !visited[y][x - 1]) {
                            val pipe = map[y][x - 1]
                            val facing = if (pipe == '-') Cardinal.WEST else if (pipe == 'F') Cardinal.SOUTH else Cardinal.NORTH
                            q.offer(Triple(x - 1, y, facing))
                        }
                    }
                    Cardinal.EAST -> {
                        if (x + 1 < width && listOf('-', 'J', '7').contains(map[y][x + 1]) && !visited[y][x + 1]) {
                            val pipe = map[y][x + 1]
                            val facing = if (pipe == '-') Cardinal.EAST else if (pipe == 'J') Cardinal.NORTH else Cardinal.SOUTH
                            q.offer(Triple(x + 1, y, facing))
                        }
                    }
                    Cardinal.SOUTH -> {
                        if (y + 1 < height && listOf('|', 'L', 'J').contains(map[y + 1][x]) && !visited[y + 1][x]) {
                            val pipe = map[y + 1][x]
                            val facing = if (pipe == '|') Cardinal.SOUTH else if (pipe == 'L') Cardinal.EAST else Cardinal.WEST
                            q.offer(Triple(x, y + 1, facing))
                        }
                    }
                    Cardinal.NORTH -> {
                        if (y - 1 >= 0 && listOf('|', '7', 'F').contains(map[y - 1][x]) && !visited[y - 1][x]) {
                            val pipe = map[y - 1][x]
                            val facing = if (pipe == '|') Cardinal.NORTH else if (pipe == '7') Cardinal.WEST else Cardinal.EAST
                            q.offer(Triple(x, y - 1, facing))
                        }
                    }
                }

            }
            dist++
        }

        var ans = -1
        distances.forEach {
            ans = max(ans, it.max())
            println(it.toString().replace("-1", "."))
        }

        val visitedPoints =


        println(ans)



    }




}

