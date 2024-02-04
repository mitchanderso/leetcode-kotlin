package LC2024

import java.util.*


class `2966` {

    fun divideArray(nums: IntArray, k: Int): Array<IntArray> {
        val sorted = nums.sorted()
        val ans = mutableListOf<IntArray>()

        var ptr = 0


        while (ptr < sorted.size - 2) {
            if (sorted[ptr + 2] - sorted[ptr] > k) {
                return emptyArray()
            }
            else {
                ans.add(sorted.subList(ptr, ptr + 3).toIntArray())
                ptr += 3
            }
        }


        return ans.toTypedArray()
    }


}

fun main() {
    val soln = `2966`()
    println(soln.divideArray(intArrayOf(1,3,4,8,7,9,3,5,1), 2).map { it.toList() })
}