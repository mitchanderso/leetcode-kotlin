package LC2024

import kotlin.math.min

class `179` {
    fun largestNumber(nums: IntArray): String {
        // Sort by each digit, then by length
        val strings = nums.map { it.toString() }

        // -1 a is less than b
        // 0 is equal
        // 1 is a is more than b

        val comp = Comparator<String> { a, b ->
            for (i in 0 until min(a.length, b.length)) {
                if (a[i].digitToInt() < b[i].digitToInt()) {
                     return@Comparator 1
                } else if (a[i].digitToInt() > b[i].digitToInt()){
                    return@Comparator -1
                }
            }
            if (a.length < b.length) return@Comparator -1
            if (a.length > b.length) return@Comparator 1
            return@Comparator 0
        }

        val sorted = strings.sortedWith(comp).toMutableList()
        val sb = java.lang.StringBuilder()
        while (sorted.isNotEmpty()) {
            val best = sorted[0]
            sb.append(best)
            sorted.removeAt(0)
        }

        return sb.toString()
    }
}

fun main() {
    val soln = `179`()
    println(soln.largestNumber(intArrayOf(3,30,34,5,9)))

}