package LC2024

class `523` {


    fun checkSubarraySum(nums: IntArray, k: Int): Boolean {

        val map = mutableMapOf<Int, Int>()
        map[0] = -1
        var sum = 0
        for (i in 0 until nums.size) {
            sum = (sum + nums[i]) % k
            if (map.containsKey(sum)) {
                if (i - map[sum]!! > 1) return true
            } else {
                map[sum] = i
            }

        }



        return false
    }


}



fun main() {
    val soln = `523`()
    println(soln.checkSubarraySum(
        intArrayOf(1,0), 2
    ))





}