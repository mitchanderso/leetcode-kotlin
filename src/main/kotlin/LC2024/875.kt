package LC2024

import kotlin.math.ceil

class `875` {

    private fun List<Int>.checkPossible(rate: Int, h: Int): Boolean {
        var hoursLeft = h
        for (i in 0 until this.size) {
            hoursLeft -= ceil(this[i] / rate.toDouble()).toInt()
            if (hoursLeft < 0) return false
        }
        return true
    }

    fun minEatingSpeed(piles: IntArray, h: Int): Int {
        val sortedPiles = piles.sorted()
        var right = sortedPiles.last()
        var left = 1

        var best = right

        while (left <= right) {
            var mid = (left + right) / 2
            // Check if it is possible
            var possible = sortedPiles.checkPossible(mid, h)

            if (possible) {
                //println("A rate of $mid bananas per hour is possible, but can we do better...")
                // Go smaller
                best = mid
                right = mid - 1
            } else {
                //println("A rate of $mid bananas per hour is NOT possible, trying to do better")
                left = mid + 1
            }
        }

        return best
    }
}



fun main() {
    val soln = `875`()
    println(soln.minEatingSpeed(intArrayOf(3,6,7,11), 8))
}