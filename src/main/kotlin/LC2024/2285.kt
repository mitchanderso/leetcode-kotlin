package LC2024

import java.util.*

class `2285` {

    data class City(var nid: Int, var degree: Int)

    fun maximumImportance(n: Int, roads: Array<IntArray>): Long {
        val degree = mutableMapOf<Int, Int>()

        roads.forEach { (from, to) ->
            degree[from] = degree.getOrDefault(from, 0) + 1
            degree[to] = degree.getOrDefault(to, 0) + 1
        }

        val pq = PriorityQueue<City>(compareByDescending { it.degree })
        degree.forEach { (node, degree) ->
            pq.offer(City(node, degree))
        }

        var score = n
        val nodeScores = IntArray (n)

        while (pq.isNotEmpty()) {
            val bestCity = pq.poll()
            nodeScores[bestCity.nid] = score
            score--
        }

        var ans = 0L
        roads.forEach { (from, to) ->
            val s1 = nodeScores[from].toLong()
            val s2 = nodeScores[to].toLong()
            ans += (s1 + s2)
        }


        return ans

    }
}

fun main() {
    val soln = `2285`()
    println(soln.maximumImportance(5,
        arrayOf(
            intArrayOf(0,1),
            intArrayOf(1,2),
            intArrayOf(2,3),
            intArrayOf(0,2),
            intArrayOf(1,3),
            intArrayOf(2,4),
        )
        ))
}