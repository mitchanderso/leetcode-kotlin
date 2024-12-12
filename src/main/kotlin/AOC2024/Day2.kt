package AOC2024

import java.io.File
import kotlin.math.sign

class `Day2` {
    fun safety(file : File): Long {
        var count = 0L
        file.parse().forEach {
            if (it.isSafe()) {
                count++
            }
        }
        return count
    }

    fun safety2(file : File): Long {
        var count = 0L
        file.parse().forEach {
            var anySafe = false
            for (i in 0 until it.size) {
                val mutated = it.toMutableList().apply { removeAt(i) }
                if (mutated.isSafe()) {
                    anySafe = true
                    break
                }
            }
            if (anySafe) count++

        }
        return count
    }

    fun List<Int>.isSafe() : Boolean {
        if (size == 1) return true
        var direction = this[1] - this[0]
        if (direction == 0) return false
        for (i in 1 until size) {
            val difference = this[i] - this[i - 1]
            if (Math.abs(difference) !in 1 .. 3) {
                return false
            }
            // If positive, increasing, if negative decreasing
            if (difference.sign != direction.sign) {
                return false
            }
            direction = difference
        }
        return true
    }

    fun File.parse() : List<List<Int>> {
        return readLines()
            .map { line ->
                line.split(" ")
                    .map { Integer.parseInt(it) }
            }
    }
}

fun main() {
    val soln = Day2()
    val file = File(Day2::class.java.getResource("/AOC2024/day2/input.txt").file)
    //println(soln.safety(file))
    println(soln.safety2(file))
    //println(soln.reconcile2(file))
}