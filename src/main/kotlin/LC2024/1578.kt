package LC2024

import kotlin.math.max
import kotlin.math.min

class `1578` {
    fun minCost(colors: String, neededTime: IntArray): Int {
        val dp = IntArray(colors.length) { 0 }
        for (i in 1 until colors.length) {
            if (colors[i] == colors[i - 1]) {
                dp[i] = dp[i - 1] + min(neededTime[i - 1], neededTime[i])
                neededTime[i] = max(neededTime[i - 1], neededTime[i])
            } else {
                dp[i] = dp[i - 1]
            }

        }

        return dp[colors.length - 1]
    }
}



fun main() {


}