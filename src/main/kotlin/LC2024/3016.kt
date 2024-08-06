package LC2024

import java.util.*

class `3016` {

    data class KeyChar(val key : Int, val chars: MutableList<Char>)
    fun minimumPushes(word: String): Int {
        // First assign the most popular

        val leastUsedKey = PriorityQueue<KeyChar>(compareBy<KeyChar> { it.chars.size}.thenBy { it.key })
        for (i in 2 until 10) leastUsedKey.offer(KeyChar(i, mutableListOf()))

        val frequencies = mutableMapOf<Char, Int>()
        word.forEach { cha ->
            frequencies[cha] = frequencies.getOrDefault(cha, 0) + 1
        }

        val sorted = frequencies.map { it.key to it.value }.sortedByDescending { it.second }

        sorted.forEach { (cha, count) ->
            val top = leastUsedKey.poll()
            top.chars.add(cha)
            leastUsedKey.offer(top)
        }

        val reverseMap = mutableMapOf<Char, Int>()
        while (leastUsedKey.isNotEmpty()) {
            val top = leastUsedKey.poll()
            top.chars.forEachIndexed { index, c ->
                reverseMap[c] = index + 1
            }
        }

        return word.fold(0) { acc, cur ->
            acc + reverseMap[cur]!!
        }




    }
}

fun main() {
    val soln = `3016`()
    println(soln.minimumPushes("aabbccddeeffgghhiiiiii"))
}