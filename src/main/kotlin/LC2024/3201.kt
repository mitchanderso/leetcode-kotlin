package LC2024

import kotlin.math.max

enum class Num {
    EVEN, ODD, EMPTY, ANY, ALT;


}

class `3201` {


    val memo = Array(5) { Array(5) { Array(200001) { -1 } }}


    fun which(num : Int) : Num {
        if (num % 2 == 0) return Num.EVEN
        else return Num.ODD
    }


    fun maximumLength(nums: IntArray): Int {
        return dfs(nums, 0, Num.EMPTY, Num.ANY, memo)
    }

    fun dfs(nums: IntArray, pos: Int, last: Num, possibleNext: Num, memo: Array<Array<Array<Int>>>) : Int {
        if (pos >= nums.size) {
            return 0
        }
        if (memo[last.ordinal][possibleNext.ordinal][pos] != -1) {
            return memo[last.ordinal][possibleNext.ordinal][pos]
        }
        var best = 0
        val current = which(nums[pos])
        if (last == Num.EMPTY) {
            best = max(best , 1 + dfs(nums, pos + 1, current, current, memo)) // Same all the way through
            best = max(best , 1 + dfs(nums, pos + 1, current, if (current == Num.EVEN) Num.ODD else Num.EVEN, memo)) // Take and ask the opposite
            best = max(best , dfs(nums, pos + 1, last, Num.ANY, memo))
        }
        else {
            if (current != last && current == possibleNext) {
                // If they are opposite (alternating) and newLast is what we need
                best = max(best , 1 + dfs(nums, pos + 1, current, if (current == Num.EVEN) Num.ODD else Num.EVEN, memo))
            }
            // Take
            if (current == last) {
                best = max(best , 1 + dfs(nums, pos + 1, current, current, memo)) // Even all the way through
            }

            best = max(best , dfs(nums, pos + 1, last, if (current == Num.EVEN) Num.ODD else Num.EVEN, memo))
            best = max(best , dfs(nums, pos + 1, last, if (current == Num.EVEN) Num.EVEN else Num.ODD, memo))

        }

        // Dont take, and want odd


        memo[last.ordinal][possibleNext.ordinal][pos] = best

        return best
    }
}

fun main() {
    val soln = `3201`()
    //println(soln.maximumLength(intArrayOf(1,2,3,4)))
    println(soln.maximumLength(intArrayOf(1,2,1,1,2,1,2)))
    //println(soln.maximumLength(intArrayOf(79,11,58,90,69,43,22,72)))
}