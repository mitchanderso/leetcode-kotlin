package LC2024

import java.util.Stack


class `2149`() {
    fun rearrangeArray(nums: IntArray): IntArray {
        var posPointer = 0
        var negPointer = 0

        val ans = mutableListOf<Int>()
        while (ans.size != nums.size) {
            while (nums[posPointer] <= 0) posPointer++
            while (nums[negPointer] >= 0) negPointer++

            ans.add(nums[posPointer])
            ans.add(nums[negPointer])

            negPointer++
            posPointer++
        }

        return ans.toIntArray()
    }


}


fun main() {
    val soln = `2149`()
    println(soln.rearrangeArray(intArrayOf(3,1,-2,-5,2,-4)).toList())




}