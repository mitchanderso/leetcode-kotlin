package LC2024

import kotlin.math.max

class `826` {
    fun maxProfitAssignment(difficulty: IntArray, profit: IntArray, worker: IntArray): Int {
        val difficultyToProfit = difficulty.zip(profit).sortedBy { it.first }
        val sortedWorkers = worker.sorted()
        var workerIndex = 0
        var maxSoFar = 0
        var ans = 0
        var diffProfitIndex = 0
        while (workerIndex < worker.size) {
            while (diffProfitIndex < difficultyToProfit.size && difficultyToProfit[diffProfitIndex].first <= sortedWorkers[workerIndex]) {
                maxSoFar = max(maxSoFar, difficultyToProfit[diffProfitIndex].second)
                diffProfitIndex++
            }
            ans += maxSoFar
            //println("Worker $workerIndex makes $maxSoFar")
            workerIndex++
        }

        return ans
    }
}

fun main() {
    val soln = `826`()
    println(soln.maxProfitAssignment(
        intArrayOf(5,10,15),
        intArrayOf(5,10,1),
        intArrayOf(6,11,16),
    ))

    println(soln.maxProfitAssignment(
        intArrayOf(2,4,6,8,10),
        intArrayOf(10,20,30,40,50),
        intArrayOf(4,5,6,7),
    ))

    println(soln.maxProfitAssignment(
        intArrayOf(85,47,57),
        intArrayOf(24,66,99),
        intArrayOf(40,25,25),
    ))
}