package LC2024

import java.util.*

class `1905` {

    data class IslandLand(val y: Int, val x: Int, val islandNumber: Int)

    val adjacent = listOf<Pair<Int, Int>>(0 to 1, 0 to -1, 1 to 0, -1 to 0)

    fun countSubIslands(grid1: Array<IntArray>, grid2: Array<IntArray>): Int {
        var ans = 0
        val lands = mutableSetOf<IslandLand>()
        var landIndex = 0

        for (y in 0 until grid1.size) {
            for (x in 0 until grid1[0].size) {
                if (grid1[y][x] == 1) {
                    // Mark all lands as part of the island by index
                    lands.add(IslandLand(y, x, landIndex))
                    grid1[y][x] = 2
                    val q = LinkedList<Pair<Int, Int>>()
                    q.offer(y to x)
                    while (q.isNotEmpty()) {
                        val (yy, xx) = q.poll()

                        // Check adjacent
                        adjacent.forEach { (xDisp, yDisp) ->
                            val newX = xx + xDisp
                            val newY = yy + yDisp

                            if (newX >= 0 && newY >= 0 && newX < grid1[0].size && newY < grid1.size && grid1[newY][newX] == 1 && IslandLand(newY, newX, landIndex) !in lands) {
                                q.offer(newY to newX)
                                lands.add(IslandLand(newY, newX, landIndex))
                                grid1[newY][newX] = 2
                            }
                        }
                    }
                    landIndex++
                }
            }
        }

        val landsMap = lands.associate { (it.y to it.x) to it.islandNumber }
        val lands2 = mutableSetOf<IslandLand>()

        for (y in 0 until grid2.size) {
            for (x in 0 until grid2[0].size) {
                if (grid2[y][x] == 1) {
                    // Find the island number of the matching island
                    val islandFromGrid1 = landsMap.getOrDefault(y to x, -1)
                    grid2[y][x] = 2
                    lands2.add(IslandLand(y, x, landIndex))

                    // If we actually have a matching island at all....


                    var allInSameIsland = islandFromGrid1 >= 0
                    val q = LinkedList<Pair<Int, Int>>()
                    val visited = mutableSetOf<Pair<Int, Int>>()
                    q.offer(y to x)
                    visited.add(y to x)
                    while (q.isNotEmpty()) {
                        val (yy, xx) = q.poll()

                        if (landsMap.getOrDefault(yy to xx, -1) != islandFromGrid1) {
                            allInSameIsland = false
                        }

                        // Check adjacent
                        adjacent.forEach { (xDisp, yDisp) ->
                            val newX = xx + xDisp
                            val newY = yy + yDisp

                            if (newX >= 0 && newY >= 0 && newX < grid2[0].size && newY < grid2.size && grid2[newY][newX] == 1 && IslandLand(newY, newX, landIndex) !in lands2) {
                                q.offer(newY to newX)
                                lands2.add(IslandLand(newY, newX, landIndex))
                                grid2[newY][newX] = 2
                            }
                        }
                    }
                    if (allInSameIsland) {
                        ans++
                    }




                }
            }
        }




        return ans
    }


}

fun main() {
    val soln = `1905`()
    println(soln.countSubIslands(
        arrayOf(
            intArrayOf(0,0,0,0,0),
            intArrayOf(1,1,1,1,1),
            intArrayOf(1,0,1,0,1),
        ),
        arrayOf(
            intArrayOf(0,1,0,1,0),
            intArrayOf(0,1,0,1,0),
            intArrayOf(1,0,0,0,1),
        )
    ))
}