package LC2024

import java.util.Stack


class `387`() {

    fun firstUniqChar(s: String): Int {
        val locs = mutableMapOf<Char, Int>()
        s.forEachIndexed { idx, ch ->
            if (!locs.containsKey(ch)) {
                locs[ch] = idx
            } else {
                locs[ch] = Int.MAX_VALUE
            }
            println("")
        }

        val ans = locs.values.sorted().first()
        return if (ans == Int.MAX_VALUE) -1 else ans
    }

}


fun main() {
    val soln = `387`()
    println(soln.firstUniqChar("leetcode"))

}