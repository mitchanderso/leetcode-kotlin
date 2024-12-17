package AOC2024


import java.io.File
import java.util.LinkedList
import java.util.Stack
import kotlin.math.min


class Day15 {

    val displacementMap = mutableMapOf(
        '^' to (0 to -1),
        '>' to (1 to 0),
        'v' to (0 to 1),
        '<' to (-1 to 0),
    )

    data class MapWithInstructions(val map: MutableList<MutableList<Char>>, val instructions: List<String>)


    private fun pushRow(row: Int, column: Int, map: MutableList<MutableList<Char>>) : Boolean{
        val stack = Stack<Char>()
        stack.push('O')
        for (col in column until map[0].size) {
            if (map[row][col + 1] == '.') {
                // Work backwards from the stack
                var currentLocation = col + 1
                while (stack.isNotEmpty()) {
                    map[row][currentLocation] = stack.pop()
                    map[row][currentLocation - 1] = '.'
                    currentLocation--
                }
                return true
            } else if (map[row][col + 1] == 'O') {
                stack.push('O')
            } else if (map[row][col + 1] == '#') {
                return false
            }
        }
        return false
    }

    private fun pullRow(row: Int, column: Int, map: MutableList<MutableList<Char>>) : Boolean {
        val stack = Stack<Char>()
        stack.push('O')
        for (col in column downTo  1) {
            if (map[row][col - 1] == '.') {
                // Work backwards from the stack
                var currentLocation = col - 1
                while (stack.isNotEmpty()) {
                    map[row][currentLocation] = stack.pop()
                    map[row][currentLocation + 1] = '.'
                    currentLocation++
                }
                return true
            } else if (map[row][col - 1] == 'O') {
                stack.push('O')
            } else if (map[row][col - 1] == '#') {
                return false
            }
        }
        return false
    }

    private fun pushColumn(row: Int, column: Int, map: MutableList<MutableList<Char>>) : Boolean {
        val stack = Stack<Char>()
        stack.push('O')
        for (r in row until  map.size) {
            if (map[r + 1][column] == '.') {
                // Work backwards from the stack
                var currentLocation = r + 1
                while (stack.isNotEmpty()) {
                    map[currentLocation][column] = stack.pop()
                    map[currentLocation - 1][column] = '.'
                    currentLocation--
                }
                return true
            } else if (map[r + 1][column] == 'O') {
                stack.push('O')
            } else if (map[r + 1][column] == '#') {
                return false
            }
        }
        return false
    }

    private fun pullColumn(row: Int, column: Int, map: MutableList<MutableList<Char>>) : Boolean {
        val stack = Stack<Char>()
        stack.push('O')
        for (r in row downTo   0) {
            if (map[r - 1][column] == '.') {
                // Work backwards from the stack
                var currentLocation = r - 1
                while (stack.isNotEmpty()) {
                    map[currentLocation][column] = stack.pop()
                    map[currentLocation + 1][column] = '.'
                    currentLocation++
                }
                return true
            } else if (map[r - 1][column] == 'O') {
                stack.push('O')
            } else if (map[r - 1][column] == '#') {
                return false
            }
        }
        return false
    }

    fun p1(file: File) : Long {
        var (map, expandedInstructions) = file.parse()
        var currentLocation = 0 to 0
        for (y in 0 until map.size) {
            for (x in 0 until map[0].size) {
                if (map[y][x] == '@') {
                    currentLocation = x to y
                    map[y][x] = '.'
                    break
                }
            }
        }
        val instructions = expandedInstructions.joinToString("")


        instructions.forEach { instruction ->
            val (dx, dy) = displacementMap[instruction]!!
            val nx = dx + currentLocation.first
            val ny = dy + currentLocation.second
            if (map.isBox(nx, ny)) {
                val moved = when (instruction) {
                    '^' -> pullColumn(ny, nx, map)
                    '>' -> pushRow(ny, nx, map)
                    'v' -> pushColumn(ny, nx, map)
                    '<' -> pullRow(ny, nx, map)
                    else -> throw IllegalArgumentException("Impossible char")
                }
                if (moved) currentLocation = nx to ny
            } else if (map[ny][nx] == '.'){
                currentLocation = nx to ny
            }

        }

        return map.score()
    }

    fun List<MutableList<Char>>.score() : Long {
        var score = 0L
        for (y in 0 until this.size) {
            for (x in 0 until this[0].size) {
                if (this[y][x] == 'O') {
                    score += (100 * y) + x
                }
            }

        }
        return score
    }

    fun List<MutableList<Char>>.print(cx: Int, cy: Int) {
        for (y in 0 until this.size) {
            var line = ""
            for (x in 0 until this[0].size) {
                if (x == cx && y == cy){
                    line += '@'
                }
                else line += this[y][x]
            }
            println(line)
        }
    }

    fun List<MutableList<Char>>.isBox(nx: Int, ny: Int) = this[ny][nx] == 'O'


    fun File.parse() : MapWithInstructions {
        val lines = readLines()
        val map = lines.takeWhile { it.isNotEmpty() }.map { it.toMutableList() }
        val instructions = lines.takeLastWhile { it.isNotEmpty() }
        return MapWithInstructions(map.toMutableList(), instructions)
    }
}




fun main() {
    val soln = Day15()
    val file = File(Day15::class.java.getResource("/AOC2024/day15/input.txt").file)
    println(soln.p1(file))

}