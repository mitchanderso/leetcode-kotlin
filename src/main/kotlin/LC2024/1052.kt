package LC2024

class `1052` {
    fun maxSatisfied(customers: IntArray, grumpy: IntArray, minutes: Int): Int {
        var windowStart = 0
        var windowEnd = 0
        var best = -1
        var bindex = -1
        var current = 0
        var usedMinutes = 0

        while (windowEnd < customers.size) {
            // If its grumpy
            if (usedMinutes < minutes) {
                usedMinutes++
            } else {
                current -= if (grumpy[windowStart] == 1) customers[windowStart] else 0
                windowStart++
            }

            current += if (grumpy[windowEnd] == 1) customers[windowEnd] else 0
            if (current > best) {
                best = current
                bindex = windowStart
            }
            windowEnd++
        }

        println("Use grupy skills from index $bindex")
        var ans = 0
        for (i in 0 until customers.size) {
            if (i >= bindex && i < bindex + minutes) {
                ans += customers[i]
            } else if (grumpy[i] == 0) {
                ans += customers[i]
            }
        }

        return ans
    }
}

fun main() {
    val soln = `1052`()

    println(soln.maxSatisfied(
        intArrayOf(4,10,10),
        intArrayOf(1,1,0),
        2
    ))
    println(soln.maxSatisfied(
        intArrayOf(1,0,1,2,1,1,7,5),
        intArrayOf(0,1,0,1,0,1,0,1),
        3
    ))

    println(soln.maxSatisfied(
        intArrayOf(1),
        intArrayOf(0),
        1
    ))
}