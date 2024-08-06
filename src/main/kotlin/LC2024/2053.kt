package LC2024

class `2053` {
    fun kthDistinct(arr: Array<String>, k: Int): String {

        val ans = mutableMapOf<String, Int>()
        arr.forEachIndexed { idx, word ->
            if (ans.contains(word)) {
                ans[word] = -1
            } else {
                ans[word] = idx
            }
        }

        val sorted =  ans.filter { it.value >= 0 }.map { it.key to it.value  }.sortedBy { it.second }
        return if (k > sorted.size) "" else sorted[k - 1].first
    }
}

fun main() {
    val soln = `2053`()
    println(soln.kthDistinct(arrayOf("d","b","c","b","c","a"), 2))
}