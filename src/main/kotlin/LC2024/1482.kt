package LC2024

class `1482` {
    fun minDays(bloomDay: IntArray, m: Int, k: Int): Int {
        // Binary search
        var left = 0
        var right = bloomDay.max()
        var ans = -1
        while (left <= right) {
            val middle = ( left + right ) / 2
            if (bloomDay.valid(k, m, middle)) {
                ans = middle
                right = middle - 1
            }
            else {
                left = middle + 1
            }
        }
        return ans
    }

    fun IntArray.valid(boquetSize: Int, boquetAmount: Int, day: Int) : Boolean {
        var idx = 0
        var currentBoquets = 0
        var currentFlowersInBoquet = 0
        while (idx < this.size) {
            if (currentBoquets == boquetAmount) return true
            if (this[idx] <= day) {
                currentFlowersInBoquet++
            } else currentFlowersInBoquet = 0
            if (currentFlowersInBoquet == boquetSize) {
                currentBoquets++
                currentFlowersInBoquet = 0
            }
            idx++
        }

        return currentBoquets == boquetAmount
    }
}

fun main() {
    val soln = `1482`()
    println(soln.minDays(intArrayOf(7,7,7,7,12,7,7), 2 , 3))
}