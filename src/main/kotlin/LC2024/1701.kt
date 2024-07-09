package LC2024

class `1701` {
    fun averageWaitingTime(customers: Array<IntArray>): Double {
        var lastFinishedTime = -1
        var waitingTime = 0.0

        customers.forEach { (arrivalTime, cookTime) ->
            var extraWait = 0
            if (lastFinishedTime > arrivalTime) {
                // They are forced to weight a certain amount of time
                extraWait += (lastFinishedTime - arrivalTime)
            }
            val totalWait = extraWait + cookTime
            waitingTime += totalWait
            lastFinishedTime = arrivalTime + totalWait
        }

        return waitingTime / customers.size
    }
}

fun main() {
    val soln = `1701`()
    println(soln.averageWaitingTime(
        arrayOf(
            intArrayOf(1,2),
            intArrayOf(2,5),
            intArrayOf(4,3),
        )
    )) // 5.0000
}