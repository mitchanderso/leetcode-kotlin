
import java.io.File
import java.lang.Math.*
import java.util.*
import kotlin.math.absoluteValue

fun main() {
    val challenge = Day18()
    val file = File(Day18::class.java.getResource("/day18/input.txt").file)
    challenge.p1(file)
//    challenge.p2(file)
}





class Day18 {

    enum class Heading(val disp: Pair<Int, Int>, val dir: String) {
        NORTH(0 to -1, "U"),
        SOUTH(0 to 1, "D"),
        EAST(1 to 0, "R"),
        WEST(-1 to 0, "L");

        companion object {
            fun from(s: String) = values().find { it.dir == s } ?: throw java.lang.IllegalArgumentException("No such elem")
            fun fromChar(c: Char) = when (c) {
                '0' -> EAST
                '1' -> SOUTH
                '2' -> WEST
                '3' -> NORTH
                else -> {throw java.lang.IllegalArgumentException("No such elem")}
            }
        }
    }

    data class Square(val dug: Boolean, val x: Int, val y: Int, val colour: String, val originalLine: Boolean, val flooded : Boolean = false)

    fun p2(file: File) {
        var border = 0L
        var x = 0L
        var y = 0L

        val points = mutableListOf<Pair<Long, Long>>()
        points.add(x to y)
        file.forEachLine { line ->
            val (direction, dist , colour) = line.split(" ")
            val newDistance = colour.filterNot { it == '(' || it == ')'  || it == '#'}.take(5).toLong(16)
            val newDirectionDigit = colour.filterNot { it == '(' || it == ')'  || it == '#'}.last()
            when (Heading.fromChar(newDirectionDigit)) {
                Heading.NORTH -> y -= newDistance
                Heading.SOUTH -> y += newDistance
                Heading.EAST -> x += newDistance
                Heading.WEST -> x -= newDistance
            }
            points.add(x to y)
            border += newDistance
        }

        var shoelaceArea = 0L
        for (i in 0 until points.size) {
            val currentPoint = points[i]
            val nextPoint = points[(i + 1) % points.size]
            val det = (currentPoint.first * nextPoint.second) - (currentPoint.second * nextPoint.first)
            shoelaceArea += det
        }
        shoelaceArea /= 2L

        val internal = shoelaceArea + 1L - (border / 2L)
        val ans = internal + border

        println("My border $border")
        println("My shoelace $shoelaceArea")
        println("My interior $internal")
        println("My ans $ans")


    }


    fun p1(file: File) {

        val map = mutableListOf<MutableList<Square>>()
        var maxX = 0
        var maxY = 0
        var minX = 0
        var minY = 0
        var x = 0
        var y = 0
        val commands = mutableListOf<String>()
        file.forEachLine { line ->
            val (direction, dist , colour) = line.split(" ")
            val displacement = dist.toInt()
            val heading = Heading.from(direction)
            commands += line
            when (heading) {
                Heading.NORTH -> y -= displacement
                Heading.SOUTH -> y += displacement
                Heading.EAST -> x += displacement
                Heading.WEST -> x -= displacement
            }
            maxX = max(x, maxX)
            maxY = max(y, maxY)

            minX = min(x, minX)
            minY = min(y, minY)
        }
        maxX++
        maxY++
        minY--
        minX--

//        minX = 0
//        minY = 0
//        maxX = 1000
//        maxY = 1000

//        for (row in minY .. maxY + 1) {
//            map.add(MutableList ((maxX - minX) + 1) { x -> Square(dug = false, x = x, y = row, colour = "NONE", originalLine = false)})
//        }

        for (row in 0 .. abs(minY) + 11000) {
            map.add(MutableList (abs(minX) + 11000) { x -> Square(dug = false, x = x, y = row, colour = "NONE", originalLine = false)})
        }





        x = 0
        y = 0

        var pointsList = mutableListOf<Pair<Int, Int>>()
        commands.forEach { line ->
            val (direction, dist , colour) = line.split(" ")
            val displacement = dist.toInt()
            val heading = Heading.from(direction)
//            dig(x +  abs(minX), y + abs(minY), map, displacement, heading, colour)
            dig(x +  abs(minX), y + abs(minY), map, displacement, heading, colour)
            when (heading) {
                Heading.NORTH -> y -= displacement
                Heading.SOUTH -> y += displacement
                Heading.EAST -> x += displacement
                Heading.WEST -> x -= displacement
            }

            pointsList.add(x to y)

        }


        map.printMap()

        println("----")
        floodFill(maxX, maxY, map)
        println("flooded")



//        map.forEachIndexed { yIndex, squares ->
//            squares.forEachIndexed { xIndex, square ->
//                if (insidePolygon(xIndex, yIndex, map, squares)) {
//                    map[yIndex][xIndex] = map[yIndex][xIndex].copy( dug = true)
//                }
//            }
//        }


//        map.forEach {
//            println(it.map { sq -> if (!sq.flooded || sq.originalLine) "#" else "." })
//        }

        val count = map.map { it.count { sq -> !sq.flooded || sq.originalLine } }.sum()
        println("Ans is $count")


    }

    fun floodFill(sx:Int, sy: Int, map: MutableList<MutableList<Square>>) {
        val q = LinkedList<Pair<Int, Int>>()

        q.add(sx to sy)
        while (q.isNotEmpty()) {
            val (x, y) = q.poll()

            Heading.values().forEach { heading ->
                val newX = x + heading.disp.first
                val newY = y + heading.disp.second
                if (newX >= 0 && newY >= 0 && newY < map.size && newX < map[0].size && !map[newY][newX].flooded && !map[newY][newX].originalLine) {
                    q.add(newX to newY)
                    map[newY][newX] = map[newY][newX].copy(flooded = true)
                }
            }
        }
    }

    fun dig(x: Int, y: Int, map: MutableList<MutableList<Square>>, displacement: Int, heading: Heading, colour: String) {
        var newX = x
        var newY = y
        for (i in 0 until displacement) {
            if (newX >= 0 && newY >= 0 && newY < map.size && newX < map[0].size) {
                map[newY][newX] = map[newY][newX].copy(dug = true, colour = colour, originalLine = true)
                newX += heading.disp.first
                newY += heading.disp.second
            }

        }
    }

    fun insidePolygon(x: Int, y: Int, map: MutableList<MutableList<Square>>, row: MutableList<Square>) : Boolean {
        if (map[y][x].dug) return true
        else {
            var leftbound = false
            var rightbound = false

            for (j in x downTo 0) {
                if (row[j].originalLine) {
                    leftbound = true
                    break
                }
            }

            for (k in x until row.size) {
                if (row[k].originalLine) {
                    rightbound = true
                    break
                }
            }

            return leftbound && rightbound

//            for (i in 0 until  row.size) {
//                // If the previous char was the end of a line
//                if (row[i].originalLine == false) foundLine = false
//                if (row[i].originalLine && !foundLine) {
//                    foundLine = true
//                    hits++
//                }
//                if (i == x) return hits % 2 == 1
//            }

        }
        return false
    }

}

private fun  MutableList<MutableList<Day18.Square>>.printMap() {
    forEach {
        println(it.map { sq -> if (sq.dug || sq.originalLine) "#" else "." })
    }
}
