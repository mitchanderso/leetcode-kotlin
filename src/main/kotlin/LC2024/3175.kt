package LC2024

import java.util.*

class `3175` {
    fun findWinningPlayer(skills: IntArray, k: Int): Int {
        val q = LinkedList<Pair<Int, Int>>()
        skills.forEachIndexed { index, i ->  q.offer(i to index) }
        val max = skills.max()
        var inARow = 0
        var lastWinner = -1
        while (inARow != k) {

            val first = q.poll()
            val second = q.poll()

            if (first.first == max) return first.second

            if (first.first > second.first) {
                q.push(first)
                q.offer(second)
                if (first.second == lastWinner) inARow++
                else {
                    lastWinner = first.second
                    inARow = 1
                }
            } else {
                q.push(second)
                q.offer(first)
                if (second.second == lastWinner) inARow++
                else {
                    lastWinner = second.second
                    inARow = 1
                }
            }
        }
        return lastWinner
    }
}

fun main() {
    val soln = `3175`()
    println(soln.findWinningPlayer(intArrayOf(16,4,7,17), 562084119))
}