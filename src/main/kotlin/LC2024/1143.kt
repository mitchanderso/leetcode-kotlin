package LC2024

import kotlin.math.max

class `1143` {

    fun longestCommonSubsequence(text1: String, text2: String): Int {
        val dp = Array ( text1.length ) { IntArray (text2.length) { -1 } }
        return dfs(0, 0, text1, text2, dp)
    }

    fun dfs(t1: Int, t2: Int, text1: String, text2: String, dp: Array<IntArray>) : Int {
        if (t1 >= text1.length || t2 >= text2.length) {
            return 0
        }

        if (dp[t1][t2] != -1) return dp[t1][t2]

        val best =  if (text1[t1] == text2[t2]) {
            // If they are both equal, we can increase the length
            1 + dfs(t1 + 1, t2 + 1, text1, text2, dp)
        } else {
            max(
                dfs(t1 + 1, t2, text1, text2, dp),
                dfs(t1, t2 +1, text1, text2, dp)
            )
        }

        dp[t1][t2] = best
        return best
    }
}

fun main() {
    val soln = `1143`()
    println(soln.longestCommonSubsequence("abcde", "ace"))
}