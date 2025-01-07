package AOC2024

import utils.Utils.print
import java.io.File
import kotlin.math.abs

class `Day8` {
    data class Antinode(val x: Int, val y: Int, val freq: Char)

    fun p1(file : File): Int {
        val map = file.parse()
        val width = map[0].length
        val height = map.size
        val matchingAntenna = mutableMapOf<Char, MutableList<Pair<Int, Int>>>()
        map.forEachIndexed { colIdx, row ->
            row.forEachIndexed { rowIdx, cha ->
                if (cha != '.' && cha != '#') {
                    val existing = matchingAntenna.getOrDefault(cha, mutableListOf())
                    existing.add(rowIdx to colIdx)
                    matchingAntenna[cha] = existing
                }
            }
        }

        val antinodes = mutableSetOf<Pair<Int, Int>>()

        matchingAntenna.values.forEach { list ->

            // DOnt just iterate all
            for (i in 0 until list.size) {
                val loc1 = list[i]
                for (j in i + 1 until list.size) {

                    val loc2 = list[j]
                    val xDiff = abs(loc1.first - loc2.first)
                    val yDiff = abs(loc1.second - loc2.second)

                    if (yDiff == 0) {
                        // They are on the same row
                        val leftNode = if (loc1.first < loc2.first) loc1 else loc2
                        val rightNode = if (loc1.first > loc2.first) loc1 else loc2

                        antinodes.add(leftNode.first - xDiff to leftNode.second)
                        antinodes.add(rightNode.first + xDiff to leftNode.second)
                    } else if (xDiff == 0) {
                        // They are on the same column
                        val aboveNode = if (loc1.second < loc2.second) loc1 else loc2
                        val belowNode = if (loc1.second > loc2.second) loc1 else loc2

                        antinodes.add(aboveNode.first to aboveNode.second - yDiff)
                        antinodes.add(belowNode.first to belowNode.second + yDiff)
                    } else {
                        // Loc 2 is the 'floating' node
                        if (loc2.first > loc1.first) {
                            // Right
                            if (loc2.second < loc1.second) {
                                // Above Right
                                antinodes.add(loc2.first + xDiff to loc2.second - yDiff)
                                antinodes.add(loc1.first - xDiff to loc1.second + yDiff)
                            } else {
                                // Below Right
                                antinodes.add(loc2.first + xDiff to loc2.second + yDiff)
                                antinodes.add(loc1.first - xDiff to loc1.second - yDiff)
                            }
                        } else {
                            // Left
                            if (loc2.second < loc1.second) {
                                // Above Left
                                antinodes.add(loc2.first - xDiff to loc2.second - yDiff)
                                antinodes.add(loc1.first + xDiff to loc1.second + yDiff)
                            } else {
                                // Below Left
                                antinodes.add(loc2.first - xDiff to loc2.second + yDiff)
                                antinodes.add(loc1.first + xDiff to loc1.second - yDiff)
                            }
                        }


                    }
                }
            }


        }

        // Print
        var count = 0

        for (y in 0 until height) {
          for (x in 0 until width) {
              if (antinodes.contains(x to y)) {
                  print('#')
                  count++
              } else if (!antinodes.contains(x to y) && map[y][x] != '.') {
                  print(map[y][x])
              } else print('.')
          }
            println()
        }



        // Filter ones outside the map
        return antinodes.count { (x, y) ->
            x >= 0 && x < width && y >=0 && y < height
        }
    }

    fun p2(file : File): Int {
        val map = file.parse()
        val width = map[0].length
        val height = map.size
        val matchingAntenna = mutableMapOf<Char, MutableList<Pair<Int, Int>>>()
        val antenna = mutableListOf<Antinode>()
        val antinodes = mutableSetOf<Antinode>()
        map.forEachIndexed { colIdx, row ->
            row.forEachIndexed { rowIdx, cha ->
                if (cha != '.' && cha != '#') {
                    val existing = matchingAntenna.getOrDefault(cha, mutableListOf())
                    existing.add(rowIdx to colIdx)
                    matchingAntenna[cha] = existing
                    antenna.add(Antinode(rowIdx, colIdx, cha))
                } else if (cha == '#') {
                    //antinodes.add(Antinode(rowIdx, colIdx, cha))
                }
            }
        }



        matchingAntenna.entries.forEach { (key, list) ->

            // DOnt just iterate all
            for (i in 0 until list.size) {
                val loc1 = list[i]
                for (j in i + 1 until list.size) {

                    val loc2 = list[j]
                    val xDiff = abs(loc1.first - loc2.first)
                    val yDiff = abs(loc1.second - loc2.second)

                    // Loc 2 is the 'floating' node
                    if (loc2.first > loc1.first) {
                        // Right
                        if (loc2.second < loc1.second) {
                            // Above Right
                            antinodes.add(Antinode(loc2.first + xDiff,loc2.second - yDiff, key))
                            antinodes.add(Antinode(loc1.first - xDiff, loc1.second + yDiff, key))
                        } else {
                            // Below Right
                            antinodes.add(Antinode(loc2.first + xDiff, loc2.second + yDiff, key))
                            antinodes.add(Antinode(loc1.first - xDiff , loc1.second - yDiff, key))
                        }
                    } else {
                        // Left
                        if (loc2.second < loc1.second) {
                            // Above Left
                            antinodes.add(Antinode(loc2.first - xDiff, loc2.second - yDiff, key))
                            antinodes.add(Antinode(loc1.first + xDiff, loc1.second + yDiff, key))
                        } else {
                            // Below Left
                            antinodes.add(Antinode(loc2.first - xDiff, loc2.second + yDiff, key))
                            antinodes.add(Antinode(loc1.first + xDiff, loc1.second - yDiff, key))
                        }
                    }

                }
            }


        }

        for (y in 0 until height) {
            for (x in 0 until width) {
                if (antinodes.any { it.y == y && it.x == x }) {
                    print('#')
                } else if (!antinodes.any { it.y == y && it.x == x } && map[y][x] != '.') {
                    print(map[y][x])
                } else print('.')
            }
            println()
        }

        println()
        println()
        println()


        // For each anti node, create a new one if it is in a perfect line
        for (i in 0 until antenna.size) {

            for (j in i + i until antenna.size) {
                val p1 = antenna[i]
                val p2 = antenna[j]
                for (step in 1 until 500) {
                    val xDiff = abs(p1.x - p2.x)
                    val yDiff = abs(p1.y - p2.y)

                    val newP1 = Antinode(p1.x + xDiff * 1, p1.y + yDiff, p1.freq)
                    val newP2 = Antinode(p2.x - xDiff * 1, p2.y - yDiff, p1.freq)

                    val p1OutOfGrid = (newP1.x < 0 || newP1.x >= width || newP1.y < 0 || newP1.y >= height)
                    val p2OutOfGrid = (newP2.x < 0 || newP2.x >= width || newP2.y < 0 || newP2.y >= height)
                    if (p2OutOfGrid && p1OutOfGrid) {
                        break
                    }
                    if (newP1 in antenna || newP2 in antenna) {
                        antinodes.add(newP1)
                        antinodes.add(newP2)
                        break
                    }
                }

            }
        }

        // Print

        for (y in 0 until height) {
            for (x in 0 until width) {
                if (antinodes.any { it.y == y && it.x == x }) {
                    print('#')
                } else if (!antinodes.any { it.y == y && it.x == x } && map[y][x] != '.') {
                    print(map[y][x])
                } else print('.')
            }
            println()
        }



        // Filter ones outside the map
        return antinodes.filter { (x, y) ->
            x >= 0 && x < width && y >=0 && y < height
        }.distinctBy { "${it.x},${it.y}"}
            .count()
    }




    fun File.parse() : List<String> {
        return this.readLines()
    }

    fun solvePart2(input: String) {
        val nodeGroups = input.lines().flatMapIndexed { i, s ->
            s.mapIndexedNotNull { j, c ->
                if (c != '.') Triple(c, i, j) else null
            }
        }.groupBy({ (ch, _, _) -> ch }) { (_, i, j) -> i to j }.values.toList()

        val visited = mutableSetOf<Pair<Int, Int>>()
        nodeGroups.forEach { group ->
            group.getAllPairs().forEach { (first, second) ->
                val diffI = first.first - second.first
                val diffJ = first.second - second.second
                var step = 0
                while (true) {
                    val one = (first.first + diffI * step) to (first.second + diffJ * step)
                    val two = (second.first - diffI * step) to (second.second - diffJ * step)
                    visited.addAll(listOf(one, two))
                    step++
                    if (one.outOfBounds(input) && two.outOfBounds(input)) break
                }

            }
        }
        val unique = visited.filterNot { it.outOfBounds(input) }
        println(unique.size)
    }

    fun Pair<Int, Int>.outOfBounds(input: String) =
        first !in 0 until input.lines().size || second !in 0 until input.lines().first().length

    fun <T> List<T>.getAllPairs() = flatMapIndexed { i: Int, t: T ->
        drop(i + 1).map { u: T -> u to t }
    }
}

fun main() {
    val soln = Day8()
    val file = File(Day5::class.java.getResource("/AOC2024/day8/input.txt").file)
    //println(soln.p1(file))
    println(soln.solvePart2(file.readText()))

}