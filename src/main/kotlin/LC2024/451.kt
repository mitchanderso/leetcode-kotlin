package LC2024

import java.util.Stack


class `451`() {
    fun frequencySort(s: String): String {
        val freqs = mutableMapOf<Char, Int>()
        s.forEach { ch ->
            freqs[ch] = freqs.getOrDefault(ch, 0) + 1
        }

        val sorted = freqs.map { it.key to it.value }.sortedByDescending { it.second }

        var ans = ""
        sorted.forEach {
            ans += "${it.first}".repeat(it.second)
        }

        return ans
    }


}


fun main() {
    val soln = `451`()
    println(soln.frequencySort("tree"))
    println(soln.frequencySort("cccaaa"))
    println(soln.frequencySort("Aabb"))



}