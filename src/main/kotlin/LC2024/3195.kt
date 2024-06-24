package LC2024

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class `3195` {
    fun minimumArea(grid: Array<IntArray>): Int {
        var minx = Int.MAX_VALUE
        var miny = Int.MAX_VALUE
        var maxx = Int.MIN_VALUE
        var maxy = Int.MIN_VALUE

        for (y in 0 until grid.size) {
            for (x in 0 until grid[0].size) {
                if (grid[y][x] == 1) {
                    minx = min(x, minx)
                    miny = min(y, miny)
                    maxx = max(x, maxx)
                    maxy = max(y, maxy)
                }
            }
        }

        val len = abs(minx - maxx) + 1
        val wid = abs(miny - maxy ) + 1
        return len * wid
    }
}

fun main() {
    val soln = `3195`()

    println("${soln.minimumArea(
        arrayOf(
            intArrayOf(0,0,1),
            intArrayOf(0,0,0),
            intArrayOf(0,0,1),
        )
    )} == 3")

    println("${soln.minimumArea(
        arrayOf(
            intArrayOf(0,0,1),
            intArrayOf(0,0,0),
            intArrayOf(0,1,1),
        )
    )} == 6")

    println("${soln.minimumArea(
        arrayOf(
            intArrayOf(0,0,1),
            intArrayOf(0,0,0),
            intArrayOf(1,0,0),
        )
    )} == 9")

    println("${soln.minimumArea(
        arrayOf(
            intArrayOf(1,0,0),
            intArrayOf(0,0,0),
            intArrayOf(0,0,1),
        )
    )} == 9")

    println("${soln.minimumArea(
        arrayOf(
            intArrayOf(0,1,1),
            intArrayOf(1,0,1),
            intArrayOf(1,0,1),
        )
    )} == 9")

    println("${soln.minimumArea(
        arrayOf(
            intArrayOf(0,1,0),
            intArrayOf(1,0,1),
        )
    )} == 6")

    println("${soln.minimumArea(
        arrayOf(
            intArrayOf(0,1),
            intArrayOf(0,0),
        )
    )} == 1")
}