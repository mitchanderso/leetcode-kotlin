package AOC2024

import java.io.File

class `Day4` {
    val directions = listOf(
        -1 to 0,
        0 to -1,
        1 to 0,
        0 to 1,
        -1 to 1,
        1 to -1,
        1 to 1,
        -1 to -1
    )

    fun Char.next() : Char {
        return when (this) {
            'X' -> 'M'
            'M' -> 'A'
            'A' -> 'S'
            else -> '_'
        }
    }

    fun xmas(file : File): Long {
        val map = file.parse()
        var ans = 0L
        for (y in 0 until map.size) {
            for (x in 0 until map[0].length) {
                if (map[y][x] == 'X') {
                    directions.forEach { (xdis, ydis) ->
                        var word = "X"
                        for (i in 1 .. 3) {
                            val newX = (xdis * i) + x
                            val newY = (ydis * i) + y
                            if (newX >= 0 && newY >= 0
                                && newX < map[0].length && newY < map.size
                                && map[newY][newX] == word.last().next()) {
                                word += word.last().next()
                            }
                        }
                        if (word == "XMAS") ans++
                    }
                }
            }
        }
        return ans
    }

    val diagonals = listOf(
        -1 to 1, // LEFT DOWN
        1 to -1, // RIGHT UP
        1 to 1,  // RIGHT DOWN
        -1 to -1 // LEFT UP
    )



    fun hasMas(map: List<String>, sx: Int, sy: Int, direction: Pair<Int, Int>) : Boolean {
        var word = "M"
        for (i in 1 .. 2) {
            val newX = (direction.first * i) + sx
            val newY = (direction.second * i) + sy
            if (newX >= 0 && newY >= 0
                && newX < map[0].length && newY < map.size
                && map[newY][newX] == word.last().next()) {
                word += word.last().next()
            }
        }
        if (word == "MAS") return true
        return false
    }



    fun xmas2(file : File): Long {
        val map = file.parse()
        var ans = 0L

        val width = map.size

        for (y in 0 until map.size) {
            for (x in 0 until map[0].length) {
                if (y + 2 < width && x + 2 < width && map[y + 1][x + 1] == 'A') {
                    val d11 = map[y][x]
                    val d12 = map[y + 1][x + 1]
                    val d13 = map[y + 2][x + 2]

                    val d21 = map[y + 2][x]
                    val d22 = map[y + 1][x + 1]
                    val d23 = map[y][x + 2]

                    val diagWordOne = "$d11$d12$d13"
                    val diagWordTwo = "$d21$d22$d23"

                    if ( (diagWordOne == "MAS" || diagWordOne == "SAM")  && (diagWordTwo == "MAS" || diagWordTwo== "SAM")) {
                        ans++
                    }
                }

            }
        }
        return ans
    }




    fun File.parse() : List<String> {
        return this.readLines()
    }
}

fun main() {
    val soln = Day4()
    val file = File(Day4::class.java.getResource("/AOC2024/day4/input.txt").file)
    //println(soln.xmas(file))
    println(soln.xmas2(file))

}