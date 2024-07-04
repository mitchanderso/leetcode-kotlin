package LC2024

import java.util.*

class `621` {
    fun leastInterval(tasks: CharArray, n: Int): Int {
        val total = IntArray(26) { 0 }
        tasks.forEach { total[it - 'A']++ }
        val pq = PriorityQueue<Pair<Char, Int>>(compareByDescending { it.second })
        tasks.toSet().forEach { pq.offer(Pair(it, total[it - 'A'])) }

        var cycles = 0

        while (pq.isNotEmpty()) {
            var cycleComplete = n + 1
            val multiTasks = mutableListOf<Char>()
            while (cycleComplete > 0 && pq.isNotEmpty()) {
                val (mostCommonTask, mostCommonTaskAmount) = pq.poll()
                println("Doing $mostCommonTask")
                if (mostCommonTaskAmount > 1) {
                    multiTasks.add(mostCommonTask)
                }
                total[mostCommonTask -'A']--
                cycles++
                cycleComplete--
            }

            multiTasks.forEach { pq.offer(it to total[it - 'A']) }
            if (pq.isNotEmpty()) {
                //println("Skipping $cycleComplete")

                cycles += cycleComplete
            }

        }

        return cycles
    }
}

fun main() {
    val soln = `621`()
    //println(soln.leastInterval(charArrayOf('A','A','A','A','A','A','B','C','D','E','F','G'), 1))
    println(soln.leastInterval(charArrayOf('A','A','A', 'B','B','B'), 2))
//    println(soln.leastInterval(charArrayOf('A','C','A', 'B','D','B'), 1))
//    println(soln.leastInterval(charArrayOf('A','A','A', 'B','B','B'), 3))

}