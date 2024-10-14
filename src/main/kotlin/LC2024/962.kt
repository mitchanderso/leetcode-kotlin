package LC2024

import java.util.*
import kotlin.math.max

class `962` {
    fun maxWidthRamp(nums: IntArray): Int {
        var minInStack = nums[0]
        var ans = 0
        val stack = Stack<Pair<Int, Int>>()
        stack.push(nums[0] to 0)

        for (i in 1 until nums.size) {
            if (nums[i] < minInStack) {
                minInStack = nums[i]
                stack.push(nums[i] to i)
            } else {
                stack.forEach { (value, idx) ->
                    if (value <= nums[i]) {
                        ans = max(ans, i - idx)
                    }
                }
            }
        }

        return ans
    }
}

fun main() {
    val soln = `962`()
   // println(soln.maxWidthRamp(intArrayOf(6,0,8,2,1,5)))
    println(soln.maxWidthRamp(intArrayOf(1,2,1)))
}