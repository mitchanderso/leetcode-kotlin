package LC2024

import kotlin.system.measureTimeMillis


class `3151`() {
    fun isArraySpecial(nums: IntArray): Boolean {
        if (nums.size == 1) return true
        for (i in 1 until nums.size) {
            if (nums[i] % 2 == nums[i - 1] % 2) return false
        }
        return true
    }


}


fun main() {
    val soln = `279`()
//    println( measureTimeMillis {  println(soln.perfectSquares(6255)) } )
    println( measureTimeMillis {  println(soln.perfectSquares(6254)) } )




}