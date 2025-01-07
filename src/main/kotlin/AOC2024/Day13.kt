package AOC2024


import java.io.File
import java.util.LinkedList
import kotlin.math.min


class Day13 {

    data class Arcade(val a: ArcadeButton, val b: ArcadeButton, val xTarget: Int, val yTarget: Int)

    data class ArcadeButton(val x: Int, val y: Int)

    fun p1(file: File) : Long {
        val arcades = file.parse()

        var ans = 0L

        // For each arcade try various combinations
        arcades.forEachIndexed { idx, currentArcade ->
            var min = Int.MAX_VALUE
            for (acount in 0 until 101) {
                val ax = acount * currentArcade.a.x
                val ay = acount * currentArcade.a.y

                for (bcount in 0 until 101) {
                    val bx = bcount * currentArcade.b.x
                    val by = bcount * currentArcade.b.y
                    if (ax + bx == currentArcade.xTarget && ay + by == currentArcade.yTarget) {
                        min = min(min, acount * 3 + bcount)
                    }
                }

            }
            println("For Arcade $idx the min is $min")
            ans += if ( min != Int.MAX_VALUE) min else 0
        }


        return ans
    }

    fun p2(file: File) : Long {
        val arcades = file.parse()

        var ans = 0L

        // For each arcade try various combinations
        arcades.forEachIndexed { idx, currentArcade ->
            var min = Int.MAX_VALUE
            for (acount in 0 until 101) {
                val ax = acount * currentArcade.a.x
                val ay = acount * currentArcade.a.y

                for (bcount in 0 until 101) {
                    val bx = bcount * currentArcade.b.x
                    val by = bcount * currentArcade.b.y
                    if (ax + bx == currentArcade.xTarget && ay + by == currentArcade.yTarget) {
                        min = min(min, acount * 3 + bcount)
                    }
                }

            }
            println("For Arcade $idx the min is $min")
            ans += if ( min != Int.MAX_VALUE) min else 0
        }


        return ans
    }

    fun File.parse() : List<Arcade> {
        val buttonRegex = "\\+(\\d+)".toRegex()
        val prizeXRegex = "=(\\d+)".toRegex()
        val lines = readLines()
        val arcades = mutableListOf<Arcade>()
        for (i in 0 until lines.size step 4) {
            val idx = i
            val (ax, ay) = buttonRegex.findAll(lines[idx]).map { it.groups[1]!!.value }.toList()
            val (bx, by) = buttonRegex.findAll(lines[idx + 1]).map { it.groups[1]!!.value }.toList()
            val (px, py) = prizeXRegex.findAll(lines[idx + 2]).map { it.groups[1]!!.value }.toList()
            arcades.add(
                Arcade(
                    ArcadeButton(Integer.parseInt(ax), Integer.parseInt(ay)),
                    ArcadeButton(Integer.parseInt(bx), Integer.parseInt(by)),
                    Integer.parseInt(px),
                    Integer.parseInt(py)
                )
            )
        }

        return arcades
    }
}




fun main() {
    val soln = Day13()
    val file = File(Day13::class.java.getResource("/AOC2024/day13/input.txt").file)
    println(soln.p1(file))

}