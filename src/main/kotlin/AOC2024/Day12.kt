package AOC2024


import java.io.File
import java.util.LinkedList



class Day12 {

    private val DIRECTIONS = listOf(
        1 to 0,
        -1 to 0,
        0 to 1,
        0 to -1
    )

    private infix fun Pair<Int, Int>.within(map: List<String>): Boolean {
        val width = map[0].length
        val height = map.size
        return first >= 0 && first < width && second >= 0 && second < height
    }

    private fun List<String>.perminter(loc: Pair<Int, Int>, plantCode: Char): Int {
        var perimeter = 0
        DIRECTIONS.forEach { (xDiff, yDiff) ->
            val newX = loc.first + xDiff
            val newY = loc.second + yDiff
            val newLoc = newX to newY
            if (!(newLoc within this)) {
                // If its outside the zone, we always add an edge
                perimeter++
            } else if (this[newY][newX] != plantCode) {
                // If the adjacent plant isnt the same, then add edges
                perimeter++
            }

        }
        return perimeter
    }

    private infix fun Pair<Int,Int>.notadjacent(newLoc: Pair<Int, Int>): Boolean {
        return this.first - 1 to this.second != newLoc &&
            this.first + 1 to this.second != newLoc &&
            this.first to this.second - 1 != newLoc &&
            this.first to this.second + 1 != newLoc
        
    }

    private infix fun Pair<Int,Int>.adjacent(newLoc: Pair<Int, Int>): Boolean {
        return this.first - 1 to this.second == newLoc ||
                this.first + 1 to this.second == newLoc ||
                this.first to this.second - 1 == newLoc ||
                this.first to this.second + 1 == newLoc

    }

    private fun List<String>.sides(plot: MutableSet<Pair<Int, Int>> , plantCode: Char): Int {

        // For each point, add all sides, only if they are not adjacent to any other
        var left = mutableListOf<Pair<Int, Int>>()
        var right = mutableListOf<Pair<Int, Int>>()
        var above = mutableListOf<Pair<Int, Int>>()
        var below = mutableListOf<Pair<Int, Int>>()
        var listToAdd: MutableList<Pair<Int,Int>> = mutableListOf()
        var answer = mutableSetOf<Pair<Int,Int>>()
        plot.forEach { plotPoint ->
            DIRECTIONS.forEach { (xDiff, yDiff) ->
                val newX = plotPoint.first + xDiff
                val newY = plotPoint.second + yDiff
                val newLoc = newX to newY

                if (xDiff == -1) listToAdd = left
                else if (xDiff == 1) listToAdd = right
                else if (yDiff == 1) listToAdd = below
                else if (yDiff == -1) listToAdd = above
                if (!(newLoc within this)) {
                    listToAdd.add(newLoc)
                } else if (this[newY][newX] != plantCode) {
                    listToAdd.add(newLoc)
                }



            }
        }
        
        // For each list, remove adjacent
        var leftFinal = mutableListOf<Pair<Int, Int>>()
        while (left.isNotEmpty()) {
            // Take the first entry
            val first = left.removeAt(0)
            
            // Add it to the final answer
            leftFinal.add(first)
            
            // Remove all its adjacent points
            left = reachableRemove(left.toMutableList(), first)
        }

        // For each list, remove adjacent
        var rightFinal = mutableListOf<Pair<Int, Int>>()
        while (right.isNotEmpty()) {
            // Take the first entry
            val first = right.removeAt(0)

            // Add it to the final answer
            rightFinal.add(first)

            // Remove all its adjacent points
            right = reachableRemove(right.toMutableList(), first)
        }

        // For each list, remove adjacent
        var aboveFinal = mutableListOf<Pair<Int, Int>>()
        while (above.isNotEmpty()) {
            // Take the first entry
            val first = above.removeAt(0)

            // Add it to the final answer
            aboveFinal.add(first)

            // Remove all its adjacent points
            above = reachableRemove(above.toMutableList(), first)
        }

        // For each list, remove adjacent
        var belowFinal = mutableListOf<Pair<Int, Int>>()
        while (below.isNotEmpty()) {
            // Take the first entry
            val first = below.removeAt(0)

            // Add it to the final answer
            belowFinal.add(first)

            // Remove all its adjacent points
            below = reachableRemove(below.toMutableList(), first)
        }
        

  


        return leftFinal.size + rightFinal.size + aboveFinal.size + belowFinal.size
    }
    
    fun reachableRemove(points: List<Pair<Int, Int>>, startingLocation: Pair<Int, Int>) : MutableList<Pair<Int,Int>> {
        // This function will return the original list, with all the points reachable from the starting location removed
        val visited = mutableListOf<Pair<Int, Int>>()
        val q = LinkedList<Pair<Int, Int>>()
        q.offer(startingLocation)
        visited.add(startingLocation)
        while (q.isNotEmpty()) {
            val current = q.poll()
            
            // Check all the points, if it is adjacent add it
            points.forEach { point ->
                if (point adjacent current && point !in visited) {
                    visited.add(point)
                    q.offer(point)
                }
            }
        }
        val ans = points.toMutableList()
        ans.removeAll(visited)
        return ans
    }

    fun p2(file : File, times: Int): Long {
        var ans = 0L
        val map = file.parse()
        val width = map[0].length
        val height = map.size
        val inRegion = mutableSetOf<Pair<Int, Int>>()

        for (y in 0 until height) {
            for (x in 0 until width) {
                val location = x to y
                if (location !in inRegion) {

                    // It is not in a region, lets begin a region
                    // Do a BFS to find pairs
                    val plot = mutableSetOf<Pair<Int, Int>>()
                    val plantCode = map[y][x]
                    val q = LinkedList<Pair<Int,Int>>()
                    q.offer(location)
                    plot.add(location)


                    while (q.isNotEmpty()) {
                        val currentPlot = q.poll()
                        inRegion.add(location)

                        DIRECTIONS.forEach { (xDiff, yDiff) ->
                            val newX = currentPlot.first + xDiff
                            val newY = currentPlot.second + yDiff
                            val newLoc = newX to newY
                            if (newLoc within map
                                && map[newY][newX] == plantCode
                                && newLoc !in plot) {
                                plot.add(newLoc)

                                inRegion.add(newLoc)
                                q.offer(newLoc)

                            }
                        }
                    }

                    val sides = map.sides(plot, plantCode)
                    val area = plot.size
                    ans += (area * sides)
                    println("Found a plot beginning at $x,$y growing $plantCode with area $area and sides $sides cost = $${(area * sides)}")
                }
            }
        }
        return ans
    }

    fun p1(file : File, times: Int): Long {
        var ans = 0L
        val map = file.parse()
        val width = map[0].length
        val height = map.size
        val inRegion = mutableSetOf<Pair<Int, Int>>()

        val regions = mutableListOf<MutableSet<Pair<Int, Int>>>()

        for (y in 0 until height) {
            for (x in 0 until width) {
                val location = x to y
                if (location !in inRegion) {

                    // It is not in a region, lets begin a region
                    // Do a BFS to find pairs
                    val plot = mutableSetOf<Pair<Int, Int>>()
                    val plantCode = map[y][x]
                    val q = LinkedList<Pair<Int,Int>>()
                    q.offer(location)
                    plot.add(location)
                    var permimeter = 0

                    while (q.isNotEmpty()) {
                        val currentPlot = q.poll()
                        val perim = map.perminter(currentPlot, plantCode)
                        permimeter += perim
                        inRegion.add(location)

                        DIRECTIONS.forEach { (xDiff, yDiff) ->
                            val newX = currentPlot.first + xDiff
                            val newY = currentPlot.second + yDiff
                            val newLoc = newX to newY
                            if (newLoc within map
                                && map[newY][newX] == plantCode
                                && newLoc !in plot) {
                                plot.add(newLoc)

                                inRegion.add(newLoc)
                                q.offer(newLoc)

                            }
                        }
                    }
                    val area = plot.size
                    ans += (area * permimeter)
                    println("Found a plot beginning at $x,$y growing $plantCode with area $area and perimeter $permimeter cost = $${(area * permimeter)}")
                }
            }
        }
        return ans
    }




    fun File.parse() : List<String> {
        return readLines()
    }
}




fun main() {
    val soln = Day12()
    val file = File(Day11::class.java.getResource("/AOC2024/day12/input.txt").file)
    //println(soln.p1(file, 25))
    println(soln.p2(file, 25))

}