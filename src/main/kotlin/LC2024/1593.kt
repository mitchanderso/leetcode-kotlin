package LC2024

import kotlin.math.max

class `1593` {
    fun maxUniqueSplit(s: String): Int {
        val set = mutableSetOf<String>()
        var idx = 0
        while (idx < s.length) {
            val start = idx
            while (idx < s.length - 1 && set.contains(s.substring(start, idx + 1))) {
                idx++
            }
            set.add(s.substring(start, idx + 1))
            idx++
        }

        return set.size
    }

    fun maxUniqueSplit2(s: String): Int {
        return dfs(s, mutableSetOf(), 0, 0)
    }

    fun dfs(s: String, seen: MutableSet<String>, start: Int, end: Int): Int {
        if (end + 1 > s.length) {
            return seen.size
        }

        // Split
        var best = -1
        var movingEnd = end
        while (movingEnd < s.length - 1 && seen.contains(s.substring(start, movingEnd + 1))) {
            movingEnd++
        }
        seen.add(s.substring(start, movingEnd + 1))
        best = max(best, dfs(s, seen, movingEnd + 1, movingEnd + 1))
        seen.remove(s.substring(start, movingEnd + 1))


        // Move on
        best = max(best, dfs(s, seen, start, end + 1))
        return best
    }
}

fun main() {
    val soln = `1593`()
    println(soln.maxUniqueSplit2("wwwzfvedwfvhsww"))
    println(soln.maxUniqueSplit2("ababccc"))
    println(soln.maxUniqueSplit2("aba"))
    println(soln.maxUniqueSplit2("aa"))
}