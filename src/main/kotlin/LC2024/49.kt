package LC2024

import java.util.Stack


class `49`() {

    fun groupAnagrams(strs: Array<String>): List<List<String>> {

        val ans = mutableListOf<MutableList<Pair<Map<Char,Int>, String>>>()

        strs.forEach { word ->
            val freq = getFrequencies(word)
            var added = false
            for (i in 0 until ans.size) {
                val ansMap = ans[i][0].first
                if (ansMap == freq) {
                    ans[i].add(ansMap to word)
                    added = true
                    break
                }
            }
            if (!added) ans.add(mutableListOf(freq to word))
        }

        return ans.map {
            it.map { it.second }
        }
    }

    fun getFrequencies(w1: String) :  Map<Char, Int> {
        val w1Map = mutableMapOf<Char, Int>()

        for (i in 0 until w1.length) {
            w1Map[w1[i]] = w1Map.getOrDefault(w1[i], 0) + 1

        }
        return w1Map
    }

}


fun main() {
    val soln = `49`()
    println(soln.groupAnagrams(arrayOf("eat","tea","tan","ate","nat","bat")))



}