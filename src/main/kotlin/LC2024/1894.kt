package LC2024

class `1894` {
    fun chalkReplacer(chalk: IntArray, k: Int): Int {
        val cumulative = mutableListOf<Long>()
        cumulative.add(chalk[0].toLong())
        for (i in 1 until chalk.size) {
            cumulative.add(chalk[i] + cumulative[i - 1])
        }

        // Binary search
        var low = 0
        var high = cumulative.size - 1

        var remainder = k % cumulative.last()

        for (i in 0 until cumulative.size) {
            if (cumulative[i] > remainder) return i
        }


        while (low < high) {
            val mid = low + high / 2
            if (cumulative[mid] >= remainder) {
                // Go lower
                high = mid - 1
            } else {
                low = mid
            }
        }

        return high
    }
}

fun main() {
    val soln = `1894`()
    println(soln.chalkReplacer(intArrayOf(22,25,39,3,45,45,12,17,32,9), 835))
}