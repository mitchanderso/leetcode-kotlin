package LC2024

import java.util.*


class `3170` {



    fun clearStars(s: String): String {
        val comparator = compareBy<Pair<Char, Int>> { it.first }.thenByDescending { it.second }
        val pq = PriorityQueue<Pair<Char, Int>>(comparator)

        val sb = java.lang.StringBuilder(s)

        for (i in 0 until s.length) {
            if (s[i] != '*') {
                pq.offer(s[i] to i)
            } else {
                if (pq.isNotEmpty()) {
                    val first = pq.poll()
                    sb[first.second] = '*'
                }

            }
        }

        return sb.filterNot { it == '*' }.toString()
    }


}

fun main() {
    val soln = `3170`()
    println(soln.clearStars("*aba*"))// 1
}