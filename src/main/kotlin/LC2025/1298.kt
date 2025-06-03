package LC2025

import java.util.*

class `1298` {
    fun maxCandies(status: IntArray, candies: IntArray, keys: Array<IntArray>, containedBoxes: Array<IntArray>, initialBoxes: IntArray): Int {
        val canOpen = Array (status.size) { false }
        val heldBoxes = Array (status.size) { false }
        val visited = Array (status.size) { false }
        var candyCount = 0

        val q = LinkedList<Int>()

        status.forEachIndexed { index, i ->
            canOpen[index] = i == 1
        }

        initialBoxes.forEach { box ->
            heldBoxes[box] = true
            if (canOpen[box]) {
                q.offer(box)
                visited[box] = true
                candyCount += candies[box]
            }
        }

        while (q.isNotEmpty()) {
            val currentBox = q.poll()
            keys[currentBox].forEach {
                canOpen[it] = true
                if (!visited[it] && heldBoxes[it]) {
                    q.offer(it)
                    visited[it] = true
                    candyCount += candies[it]
                }
            }
            println()
            containedBoxes[currentBox].forEach {
                heldBoxes[it] = true
                if (!visited[it] && canOpen[it]) {
                    q.offer(it)
                    visited[it] = true
                    candyCount += candies[it]
                }
            }
        }

        return candyCount
    }


}

fun main() {
    val soln = `1298`()

//    println(soln.maxCandies(
//        intArrayOf(1,1,1),
//        intArrayOf(100,1,100),
//        arrayOf(
//            intArrayOf(),
//            intArrayOf(0,2),
//            intArrayOf(),
//
//        ),
//        arrayOf(
//            intArrayOf(),
//            intArrayOf(),
//            intArrayOf(),
//        ),
//        intArrayOf(1)
//    ))
//
//    println(soln.maxCandies(
//        intArrayOf(1,0,0,0,0,0),
//        intArrayOf(1,1,1,1,1,1),
//        arrayOf(
//            intArrayOf(1,2,3,4,5),
//            intArrayOf(),
//            intArrayOf(),
//            intArrayOf(),
//            intArrayOf(),
//            intArrayOf(),
//        ),
//        arrayOf(
//            intArrayOf(1,2,3,4,5),
//            intArrayOf(),
//            intArrayOf(),
//            intArrayOf(),
//            intArrayOf(),
//            intArrayOf(),
//        ),
//        intArrayOf(0)
//    ))



    println(soln.maxCandies(
        intArrayOf(1,0,1,0),
        intArrayOf(7,5,4,100),
        arrayOf(
            intArrayOf(),
            intArrayOf(),
            intArrayOf(1),
            intArrayOf(),
        ),
        arrayOf(
            intArrayOf(1,2),
            intArrayOf(3),
            intArrayOf(),
            intArrayOf(),
        ),
        intArrayOf(0)
    ))


}