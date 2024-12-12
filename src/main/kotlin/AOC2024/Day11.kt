package AOC2024

import utils.Utils.split
import java.io.File

class `Day11` {
    fun p1(file : File, times: Int): Long {
        var stones = file.parse()[0].split(' ').map { it.toLong() }.toMutableList()

        repeat(times) {
            val newStones = stones.toMutableList()
            var shift = 0
            println("Size is now ${newStones.size} after $it iterations")
            stones.forEachIndexed { index, stone ->
                var idx = index + shift
                when  {
                    stone == 0L -> newStones[idx] = 1
                    stone.toString().length % 2 == 0 -> {
                        val midPoint = stone.toString().length / 2
                        val left = stone.toString().substring(0 ,midPoint)
                        val right = stone.toString().substring(midPoint)
                        shift++
                        newStones[idx] = left.toLong()
                        newStones.add(idx + 1, right.toLong())

                    }
                    else -> newStones[idx] = (stone * 2024)
                }
            }
            stones = newStones
        }

        return stones.size.toLong()

    }

    fun p2(file : File, times: Int): Long {
        var stones = file.parse()[0].split(' ').map { it.toLong() }.toMutableList()

        var stoneCounts = mutableMapOf<Long, Long>()

        stones.forEach { stone ->
            stoneCounts[stone] = stoneCounts.getOrDefault(stone, 0L) + 1L
        }

        repeat(times) {
            val nextStoneCounts = mutableMapOf<Long, Long>()

            stoneCounts.forEach { stone, amt ->
                // Get what the next set of stones would look like
                val nextStones = mutableListOf<Long>()
                when  {
                    stone == 0L -> nextStones.add(1)
                    stone.toString().length % 2 == 0 -> {
                        val midPoint = stone.toString().length / 2
                        val left = stone.toString().substring(0 ,midPoint)
                        val right = stone.toString().substring(midPoint)
                        nextStones.add(left.toLong())
                        nextStones.add(right.toLong())
                    }
                    else -> nextStones.add(stone * 2024L)
                }

                // For each of thiese next ones update its own map
                nextStones.forEach { nextStone ->
                    nextStoneCounts[nextStone] = nextStoneCounts.getOrDefault(nextStone, 0L) + amt
                }
            }

            stoneCounts = nextStoneCounts
        }

        return stoneCounts.values.sum()

    }



    fun File.parse() : List<String> {
        return readLines()
    }
}

fun main() {
    val soln = Day11()
    val file = File(Day11::class.java.getResource("/AOC2024/day11/input.txt").file)
    println(soln.p1(file, 25))
    println(soln.p2(file, 25))
    println(soln.p2(file, 75))

}