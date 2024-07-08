package LC2024

import java.util.*

class `1823` {
    fun findTheWinner(n: Int, k: Int): Int {
        val contestants = LinkedList<Int>()
        for (i in 1 .. n) {
            contestants.offer(i)
        }

        var totalItr = 0


        while (contestants.size > 1) {
            for (i in 0 until k - 1) {
                val removed = contestants.pop()
                contestants.offer(removed)
                totalItr++
            }
            contestants.poll()
        }


        println(totalItr)
        return contestants.first
    }
}

fun main() {
    val soln = `1823`()
    println(soln.findTheWinner(500,2))
}