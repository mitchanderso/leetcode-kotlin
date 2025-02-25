package LC2025

class `1524` {
    fun numOfSubarrays(arr: IntArray): Int {
        var prefixSum = 0
        var evenSums = 1
        var oddSums = 0
        var count = 0

        val MOD  = 1_000_000_007

        for (i in arr.indices) {
            prefixSum += arr[i]
            // If PSUM is even then to form an odd subarray we need an odd subarray sum before
            if (prefixSum % 2 == 0) {
                count += oddSums
                evenSums++
            } else {
                count += evenSums
                oddSums++
            }

            count %= MOD
        }

        return count
    }
}

fun main() {
    val soln = `1524`()
    println(soln.numOfSubarrays(
        intArrayOf(1,2,3,4,5,6,7)
    ))

}