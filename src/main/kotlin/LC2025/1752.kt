package LC2025

class `1752` {
    fun check(nums: IntArray): Boolean {
        var minInFirstRun = nums[0]
        var mindex = 0
        for (i in 0 until nums.size) {
            if (nums[i] < minInFirstRun) {
                minInFirstRun = nums[i]
                mindex = i
            }
        }

        val mindices = mutableListOf<Int>()
        nums.forEachIndexed { index, i -> if (i == minInFirstRun) mindices.add(index) }

        mindices.forEach {
            mindex = it
            var valid = true
            for (i in 0 until nums.size - 1) {
                val next = if (mindex + 1 >= nums.size) 0 else mindex + 1
                if (nums[next] < nums[mindex]) {
                    valid = false
                    break
                }
                mindex++
                if (mindex >= nums.size) mindex = 0
            }
            if (valid) return true

        }


        return false

    }
}

fun main() {
    val soln = `1752`()
    //println(soln.check(intArrayOf(3,4,5,1,2)))
    //println(soln.check(intArrayOf(2,1,3,4)))
    println(soln.check(intArrayOf(6,10,6)))


}