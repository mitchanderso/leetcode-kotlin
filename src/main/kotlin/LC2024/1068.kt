package LC2024



class `1068` {

    fun specialArray(nums: IntArray): Int {
        val sorted = nums.sortedDescending()

        for (i in 0 .. 1000) {
            var count = 0
            for (j in 0 until sorted.size) {
                if (sorted[j] < i) break
                else count++
            }
            if (count == i) return i
        }

        return  -1
    }





}

fun main() {
    val soln = `1068`()
    println(soln.specialArray(intArrayOf(3,5)))
    println(soln.specialArray(intArrayOf(0,0)))
    println(soln.specialArray(intArrayOf(0,4,3,0,4)))
}