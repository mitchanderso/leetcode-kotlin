package LC2024

import java.util.*
import kotlin.math.max

class `3296` {
    data class Seq(val inc: Long, var uses: Long, val time: Long)
    fun minNumberOfSeconds(mountainHeight: Int, workerTimes: IntArray): Long {
        val pq = PriorityQueue<Seq>(compareBy { it.time  })
        workerTimes.forEach { pq.offer(Seq(it.toLong(), 1L, it.toLong())) }

        var max = -1L
        for (i in 0 until mountainHeight) {
            val (inc, uses, time) = pq.poll()
            max = max(max, time)
            val newUses = uses + 1
            val newTime = inc * ((newUses  * (newUses + 1)) / 2)
            pq.offer(Seq(inc, newUses, newTime))


        }

        return max
    }
}

fun main() {
    val soln = `3296`()
    println(soln.minNumberOfSeconds(10, intArrayOf(3,2,2,4)))
}