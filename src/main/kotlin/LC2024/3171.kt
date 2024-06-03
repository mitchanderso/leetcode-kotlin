package LC2024

import kotlin.math.abs
import kotlin.math.min


class `3171` {

    var min = Int.MAX_VALUE

    fun minimumDifference(nums: IntArray, k: Int): Int {
        dfs(nums, k, 0, 0, 0, mutableListOf())
        return min
    }

    fun dfs(nums: IntArray, k: Int, pos: Int, working: Int, sz: Int, mutableList: MutableList<Int>)  {
        if (pos >= nums.size) {

            println("At the end of the array $mutableList the and value is $working")
            min = min(min, abs(k - working))

            return
        }

        //Take
        for (i in pos until nums.size) {
            val newList = mutableList + nums[pos]
            dfs(nums, k, pos + 1, if ( sz == 0 ) nums[pos] else working and nums[pos], sz + 1, newList.toMutableList())
        }




    }


}

fun main() {
    println(22 and 78)
    val soln = `3171`()
    println(soln.minimumDifference(intArrayOf(22,87,5,78,94), 10))
}