package AOC2024

import java.io.File

class `Day1` {
    fun reconcile(file : File): Long {
        val (l1, l2) = file.parse()
        var diff = 0L
        for (i in l1.indices) {
            diff += Math.abs(l1[i] - l2[i])
        }
        return diff
    }

    fun reconcile2(file : File): Long {
        val (l1, l2) = file.parse()
        var diff = 0L
        val counts = mutableMapOf<Int, Long>()
        l2.forEach { num ->
            counts[num] = counts.getOrDefault(num, 0) + 1
        }
        for (i in l1.indices) {
            diff += (l1[i] * counts.getOrDefault(l1[i], 0))
        }
        return diff
    }



    fun File.parse() : Pair<List<Int>, List<Int>> {
        val l1 = mutableListOf<Int>()
        val l2 = mutableListOf<Int>()

        forEachLine { line ->
            val p1 = line.subSequence(0, 5).toString()
            val p2 = line.subSequence(8, 13).toString()
            l1.add(Integer.parseInt(p1))
            l2.add(Integer.parseInt(p2))
        }

        return l1.sorted() to l2.sorted()
    }
}

fun main() {
    val soln = Day1()
    val file = File(Day1::class.java.getResource("/AOC2024/day1/input.txt").file)
    println(soln.reconcile(file))
    println(soln.reconcile2(file))
}