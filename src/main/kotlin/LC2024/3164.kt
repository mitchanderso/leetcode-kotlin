package LC2024

import kotlin.math.sqrt


class `3164`() {
    fun numberOfPairs(nums1: IntArray, nums2: IntArray, k: Int): Long {
        var ans = 0L
        val memo = mutableMapOf<Int, Long>()
        val count = mutableMapOf<Int, Int>()
        for (i in 0 until nums2.size) {
            count[nums2[i] * k] = count.getOrDefault(nums2[i] * k, 0) + 1
        }

        nums1.forEach { num ->
            if (!memo.containsKey(num)) {

                var local = 0L
                //println("Factors of $num")
                for (i in 1 .. sqrt(num.toDouble()).toInt()) {
                    if (num % i == 0) {
                        if (num / i == i) {
                            //println("$i")
                            local += count.getOrDefault(i, 0)
                        }
                        else  {
                            local += count.getOrDefault(i, 0)
                            local += count.getOrDefault(num/i, 0)
                            //println("$i")
                            //println("${num/i}")
                        }
                    }
                }
                memo[num] = local
            }
            ans += memo[num]!!
        }

        return ans
    }

}


fun main() {
    val soln = `3164`()
    println(soln.numberOfPairs(
        intArrayOf(1,3,4),
        intArrayOf(1,3,4),
        1
    ))


}