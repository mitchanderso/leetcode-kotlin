package LC2024

import kotlin.math.max

class `2673` {
    // Max under a certain index
    val maxUnderMap = mutableMapOf<Int,Int>()

    var carried = 0

    fun minIncrements(n: Int, cost: IntArray): Int {
        carried = 0
        // Find the max path]
        val newCost = intArrayOf(-100) + cost
        val max = maxPath(1, newCost)
        //val max = cost[0] + max(maxPath(1, cost), maxPath(2, cost))
        println("Max cost is $max")

        increment(1, newCost, max, 0)
        return carried
    }

    fun increment(current: Int, cost: IntArray, max: Int, carry: Int) {
        val left = 2 * current
        val right = 2 * current + 1

        if (left >= cost.size || right >= cost.size) {
            // Its a leaf
            carried += max - (cost[current] + carry)
            return
        }

        var newCarry = 0

        if (maxUnderMap[current]!! + carry < max) {
            // We can increment it, because its own local max is less than the global
            newCarry = max - (maxUnderMap[current]!! + carry)
            carried += newCarry
        }
            newCarry += cost[current]


        increment(left, cost, max, carry + newCarry)
        increment(right, cost, max, carry + newCarry)

    }

    fun maxPath(current: Int, cost: IntArray) : Int {
        val left = 2 * current
        val right = 2 * current + 1
        if (left >= cost.size || right >= cost.size) {
            maxUnderMap[current] = cost[current]
            return cost[current]
        } else {
            val lmax = cost[current] + maxPath(left, cost)
            val rmax = cost[current] + maxPath(right, cost)
            maxUnderMap[current] = max(lmax, rmax)
            return max(lmax, rmax)
        }
    }
}

fun main() {
    val soln = `2673`()
    println(soln.minIncrements(7, intArrayOf(1,5,2,2,3,3,1)))
    println(soln.minIncrements(3, intArrayOf(5,3,3)))
}