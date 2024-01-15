package LC2024

class `2225` {
    fun findWinners(matches: Array<IntArray>): List<List<Int>> {
        val winsAndLosses = mutableMapOf<Int, Pair<Int, Int>>()

        matches.forEach { (winner, loser) ->
            val winnerPair = winsAndLosses.getOrDefault(winner, 0 to 0)
            winsAndLosses[winner] = winnerPair.first + 1 to winnerPair.second

            val loserPair = winsAndLosses.getOrDefault(loser, 0 to 0)
            winsAndLosses[loser] = loserPair.first to loserPair.second + 1
        }

        val noLosers = winsAndLosses.filter { (k, v) -> v.second == 0 }.map { (k, _) -> k }.sorted()
        val oneLosers = winsAndLosses.filter { (k, v) -> v.second == 1 }.map { (k, _) -> k }.sorted()

        return listOf(noLosers, oneLosers)
    }
}

fun main() {
    val soln = `2225`()
    println(soln.findWinners(arrayOf(
        intArrayOf(1,3),
        intArrayOf(2,3),
        intArrayOf(3,6),
        intArrayOf(5,6),
        intArrayOf(5,7),
        intArrayOf(4,5),
        intArrayOf(4,8),
        intArrayOf(4,9),
        intArrayOf(10,4),
        intArrayOf(10,9),

        )))
}