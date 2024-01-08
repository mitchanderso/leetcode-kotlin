import java.io.File
import java.lang.Math.*

fun main() {
    val challenge = Day13()
    val file = File(Day13::class.java.getResource("/day13/input.txt").file)
    challenge.p1(file)
    challenge.p2(file)

}

class Day13 {

    fun horizontalMirrorPoint(map: List<String>, allowedDifferences: Int = 0) : Int {
        val width = map.first().length
        val height = map.size
        for (linePos in 1 until width ) {
            // Left of the line
            val leftOfLineWidth = linePos
            val rightOfLineWidth = width - linePos
            val leftStart = linePos - min(leftOfLineWidth, rightOfLineWidth)
            val dispSize = (leftStart until linePos).count()

            var mirror = true
            var differences = 0
            for (y in 0 until height) {
                var rightIdx = linePos
                var leftIdx = linePos - 1
                for (disp in 0 until dispSize) {
                    if (map[y][rightIdx + disp] != map[y][leftIdx - disp]) {
                        if (differences >= allowedDifferences) {
                            mirror = false
                            break
                        }
                        else differences++
                    }
                }
                if (!mirror) break
            }

            if (mirror && differences == allowedDifferences) {
                println("They are mirror on vertical line after index $linePos")
                return linePos
            }
        }
        return -1
    }

    fun verticalMirrorPoint(map: List<String>, allowedDifferences: Int = 0) : Int {

        val width = map.first().length
        val height = map.size
        for (linePos in 1 until height ) {
            // Left of the line
            val aboveLineWidth = linePos
            val belowLineWidth = height - linePos
            val aboveStart = linePos - min(aboveLineWidth, belowLineWidth)
            val dispSize = (aboveStart until linePos).count()

            var mirror = true
            var differences = 0
            for (x in 0 until width) {
                var belowIdx = linePos
                var aboveIdx = linePos - 1
                for (disp in 0 until dispSize) {
                    if (map[belowIdx + disp][x] != map[aboveIdx - disp][x]) {
                        if (differences >= allowedDifferences) {
                            mirror = false
                            break
                        }
                        else differences++
                    }
                }
                if (!mirror) break
            }

            if (mirror && differences == allowedDifferences) {
                println("They are mirror on horizontal line after index $linePos")
                return linePos
            }



        }
        return -1
    }

    fun p1(file: File) {
        var columnsToLeft = 0L
        var rowsAbove = 0L

        var map = mutableListOf<String>()
        file.forEachLine { line ->
            if (line.length <= 1) {
                val horizontalMirrorPoint = horizontalMirrorPoint(map)
                val verticalMirrorPoint = verticalMirrorPoint(map)
                if (horizontalMirrorPoint >= 0) columnsToLeft += horizontalMirrorPoint
                else if (verticalMirrorPoint >= 0) rowsAbove += verticalMirrorPoint
                map = mutableListOf()
            } else {
                map.add(line)
            }
        }


        println("Number of columns to the left is $columnsToLeft")
        println("Number of rows above is $rowsAbove")
        println("Ans is ${columnsToLeft + (100 * rowsAbove)}")

    }

    fun p2(file: File) {
        var columnsToLeft = 0L
        var rowsAbove = 0L

        var map = mutableListOf<String>()
        file.forEachLine { line ->
            if (line.length <= 1) {
                val horizontalMirrorPoint = horizontalMirrorPoint(map, allowedDifferences = 1)
                val verticalMirrorPoint = verticalMirrorPoint(map, allowedDifferences = 1)
                if (horizontalMirrorPoint >= 0) columnsToLeft += horizontalMirrorPoint
                else if (verticalMirrorPoint >= 0) rowsAbove += verticalMirrorPoint
                map = mutableListOf()
            } else {
                map.add(line)
            }
        }


        println("Number of columns to the left is $columnsToLeft")
        println("Number of rows above is $rowsAbove")
        println("Ans is ${columnsToLeft + (100 * rowsAbove)}")

    }



}

// val leftOfTheLine = mutableListOf<MutableList<Char>>()

//            for (left in leftStart until linePos) {
//                for (y in 0 until height) {
//                    if (leftOfTheLine.size <= y) leftOfTheLine.add(mutableListOf())
//                    leftOfTheLine[y].add( map[y][left] )
//                }
//            }
//            println("LHS")
//            for (i in 0 until leftOfTheLine.size) {
//                println(leftOfTheLine[i])
//            }
//
//            val rightOfLine = mutableListOf<MutableList<Char>>()
//            for (right in linePos until (linePos + min(leftOfLineWidth, rightOfLineWidth))) {
//                for (y in 0 until height) {
//                    if (rightOfLine.size <= y) rightOfLine.add(mutableListOf())
//                    rightOfLine[y].add( map[y][right] )
//                }
//            }
//
//            println("RHS")
//            for (i in 0 until rightOfLine.size) {
//                println(rightOfLine[i])
//            }