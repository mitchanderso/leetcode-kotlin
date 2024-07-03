package LC2024


class `1863` {



    fun subsetXORSum(nums: IntArray): Int {
        //val memo = mutableMapOf<Pair<Int, Int>, Int>()
        return subsetXORRecurse(nums, 0, 0)

    }

    fun subsetXORRecurse(nums: IntArray, pos: Int, total: Int) : Int {

        if (pos >= nums.size) return total

        var take = subsetXORRecurse(nums, pos + 1, total xor nums[pos])
        var dontTake = subsetXORRecurse(nums, pos + 1, total)

        return take + dontTake



    }


}

fun main() {
    val soln = `1863`()
    println(soln.subsetXORSum(intArrayOf(5,1,6)))
}