package LC2024

import java.util.*
import kotlin.math.ceil

class `2530` {
    fun maxKelements(nums: IntArray, k: Int): Long {
        val pq = PriorityQueue<Int>( compareByDescending { it })

        nums.forEach { pq.offer(it) }

        var score = 0L
        var count = k
        while (count > 0) {
            val top = pq.poll()

            pq.offer(ceil(top / 3.0).toInt())
            score += top.toLong()
            count--
        }

        return score
    }
}

fun main() {
    val soln = `2530`()
    println(soln.maxKelements(intArrayOf(1,10,3,3,3), 3))
}