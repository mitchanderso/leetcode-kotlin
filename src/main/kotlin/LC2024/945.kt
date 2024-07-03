package LC2024

import java.util.*

class `945` {
    fun minIncrementForUnique(nums: IntArray): Int {
        val sortedNums = nums.sorted()
        val slots = TreeMap<Int, Int>()
        var moves = 0
        var lastKeyUsed = 0

        sortedNums.forEach { num ->
            if (slots.containsKey(num)) {
                lastKeyUsed++
                slots[lastKeyUsed] = num
                moves += (lastKeyUsed - num)
            } else {
                slots[num] = num
                lastKeyUsed = num
            }
            //counts[num] = counts.getOrDefault(num, 0) + 1

        }
        return moves
    }
}

fun main() {
    val soln = `945`()
    println(soln.minIncrementForUnique(intArrayOf(1,1,1,1,1,1,2,2,2,7)))
}