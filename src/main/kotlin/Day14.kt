import java.io.File
import java.lang.Math.*

fun main() {
    val challenge = Day14()
    val file = File(Day14::class.java.getResource("/day14/input.txt").file)
    challenge.p2(file)

}

class Day14 {

    enum class Direction {
        NORTH, SOUTH, EAST, WEST
    }

    fun tiltNorth(map: MutableList<MutableList<Char>>, row: Int, col: Int) : Int {
        var rowPos = row
        while (rowPos - 1 >= 0 && map[rowPos - 1][col] == '.') {
            map[rowPos - 1][col] = 'O'
            map[rowPos][col] = '.'
            rowPos--
        }
        return rowPos
    }

    fun tiltSouth(map: MutableList<MutableList<Char>>, row: Int, col: Int) : Int {
        var rowPos = row
        while (rowPos + 1 < map.size && map[rowPos + 1][col] == '.') {
            map[rowPos + 1][col] = 'O'
            map[rowPos][col] = '.'
            rowPos++
        }
        return rowPos
    }

    fun tiltEast(map: MutableList<MutableList<Char>>, row: Int, col: Int) : Int {
        var colPos = col
        while (colPos + 1 < map[0].size && map[row][colPos + 1] == '.') {
            map[row][colPos + 1] = 'O'
            map[row][colPos] = '.'
            colPos++
        }
        return row
    }

    fun tiltWest(map: MutableList<MutableList<Char>>, row: Int, col: Int) : Int {
        var colPos = col
        while (colPos - 1 >= 0 && map[row][colPos - 1] == '.') {
            map[row][colPos - 1] = 'O'
            map[row][colPos] = '.'
            colPos--
        }
        return row
    }

    fun tiltBoard(map: MutableList<MutableList<Char>>, direction: Direction) : Long {
        val height = map.size
        val width = map[0].size
        var weight = 0L
        val rowRange = when (direction ){
            Direction.NORTH -> 0 until height
            Direction.SOUTH -> height - 1 downTo 0
            else -> 0 until height
        }

        val colRange = when (direction ){
            Direction.WEST -> 0 until width
            Direction.EAST -> width - 1 downTo 0
            else ->  0 until width
        }
        for (row in rowRange) { // Rocks in row 0 cant go further north
            for (col in colRange) {
                if (map[row][col] == 'O') {
                    val posAfterTilt = when (direction ){
                        Direction.NORTH -> tiltNorth(map, row, col)
                        Direction.SOUTH -> tiltSouth(map, row, col)
                        Direction.EAST -> tiltEast(map, row, col)
                        Direction.WEST -> tiltWest(map, row, col)

                    }
                    weight += (height - posAfterTilt)
                }
            }
        }
        return weight
    }

    fun p2(file: File) {
        var map = mutableListOf<MutableList<Char>>()
        file.forEachLine { line ->
            map.add(line.toMutableList())
        }

        val seenBefore = mutableMapOf<MutableList<MutableList<Char>>, Int>()
        val cache = mutableMapOf<MutableList<MutableList<Char>>,  Pair<MutableList<MutableList<Char>>, Long>>()

        var startsRepeating = -1

        var ans = 0L
        var cycleLength = -1
        var cnt = 0
        val total = 1_000_000_000

        while (cnt < total) {
            val northTilt = tiltBoard(map, Direction.NORTH)
            val westTilt = tiltBoard(map, Direction.WEST)
            val southTilt = tiltBoard(map, Direction.SOUTH)
            val finalTilt = tiltBoard(map, Direction.EAST)

            val copy = map.toMutableList()
            if (seenBefore.containsKey(copy)) {
                if (startsRepeating < 0) {
                    startsRepeating = cnt + 1
                    cycleLength = cnt - seenBefore[copy]!!
                    break
                }

            } else {
                seenBefore[copy] = cnt
            }
            println("$cnt -> $finalTilt")
            cnt++

        }


        println("Starts repeating at $startsRepeating for a length of $cycleLength")
        val remainingCycles = (total - cnt) % cycleLength
        for (i in 0 until remainingCycles) {
            val northTilt = tiltBoard(map, Direction.NORTH)
            val westTilt = tiltBoard(map, Direction.WEST)
            val southTilt = tiltBoard(map, Direction.SOUTH)
            val finalTilt = tiltBoard(map, Direction.EAST)
            println(finalTilt)
        }
        println("We want to read from ")

    }






    fun p1(file: File) {
        var map = mutableListOf<MutableList<Char>>()
        file.forEachLine { line ->
            map.add(line.toMutableList())
        }

        repeat(1) {
            var weight = 0L

            weight += tiltBoard(map, Direction.NORTH)
            println("After tilting north the weight is $weight")


            println("=================================================")
        }

    }

//    def transpose(data) = data.map(&:chars).transpose.map(&:join)
//    def reverse(data) = data.reverse
//
//    def tilt_south(data) = reverse tilt_north reverse data
//    def tilt_west(data) = transpose tilt_north transpose data
//    def tilt_east(data) = transpose reverse tilt_north reverse transpose data
//    def cycle(data) = tilt_east tilt_south tilt_west tilt_north data
//
//    def cycles(data, n)
//    seq = [data]
//    cycle_begin = 0
//    loop do
//    data = cycle(data)
//    idx = seq.index(data)
//    if !idx.nil?
//    cycle_begin = idx
//    break
//    end
//    seq << data
//    end
//    return seq[n] if n < cycle_begin
//    seq[cycle_begin + ((n - cycle_begin) % (seq.size - cycle_begin))]
//    end
//
//    data = $<.readlines(chomp: true)
//    data = cycles(data, 1000000000)
//    puts north_load(data)





}
