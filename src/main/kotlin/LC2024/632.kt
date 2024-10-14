package LC2024

import java.util.*
import kotlin.math.max

class `632` {
    fun smallestRange(nums: List<List<Int>>): IntArray {
        var ans = Int.MAX_VALUE
        val pq = PriorityQueue<Triple<Int,Int,Int>>(compareBy { it.first })


        var max = Int.MIN_VALUE

        // Triple is value, which list it came from, then its own index
        nums.forEachIndexed { index, ints ->
            pq.offer(Triple(nums[index][0], index, 0))
            max = max(nums[index][0], max)
        }

        var start = 0
        var end = Int.MAX_VALUE

        while (pq.size == nums.size) {
            var (topValue, topList, topIndex) = pq.poll()

            var min = topValue
            val range = max - min
            if (range < end - start) {
                start = min
                end = max
            }

            if (topIndex + 1 < nums[topList].size) {
                // Get the new value
                pq.offer(Triple(nums[topList][topIndex + 1], topList, topIndex + 1))
                max = max(max, nums[topList][topIndex + 1])

            }
        }



        return intArrayOf(start, end)

    }
}

fun main() {
    val soln = `632`()
    println(soln.smallestRange(
        listOf(
            listOf(-5,-4,-3,-2,-1),
            listOf(1,2,3,4,5),
        )
    ).toList())
}