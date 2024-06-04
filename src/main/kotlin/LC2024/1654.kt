package LC2024

import java.util.*

class `1654` {

    fun minimumJumps(forbidden: IntArray, a: Int, b: Int, x: Int): Int {
        val visited = mutableMapOf<Pair<Int, Int>, Boolean>()
        val q = LinkedList<Triple<Int, Int, Int>>()
        val forbiddenSet = forbidden.toSet()


        q.offer(Triple(0,0,0))
        visited[0 to 0] = true
        val max = x + (a * 10) + (b * 10)
        while (q.isNotEmpty()) {
            val pos = q.poll()

            if (pos.first == x) return pos.third

            if (!visited.containsKey(pos.first + a to 0) && pos.first + a !in forbiddenSet && pos.first + a < max) {
                q.offer(Triple(pos.first + a ,0, pos.third + 1))
                visited[pos.first + a to 0] = true
            }
            if (pos.second == 0 && pos.first - b >= 0 && !visited.containsKey(pos.first - b to 1) && pos.first - b !in forbiddenSet && pos.first - b < max) {
                q.offer(Triple(pos.first - b ,1, pos.third + 1))
                visited[pos.first - b to 1] = true
            }

        }
        return -1
    }
}

fun main() {
    val soln = `1654`()
    println(soln.minimumJumps(
        intArrayOf(1944, 924, 1025, 1686, 357),
        47,969,12
    ))



}