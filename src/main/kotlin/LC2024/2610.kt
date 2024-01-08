package LC2024

class `2610` {
    fun findMatrix(nums: IntArray) : List<List<Int>> {
        var counts = nums.associateWith { num -> nums.count { it == num } }.toMutableMap()
        val ans = mutableListOf<MutableList<Int>>()
        while (counts.isNotEmpty()) {
            val line = mutableListOf<Int>()
            counts.forEach { k, v ->
                line.add(k)
                counts[k] = counts[k]!! - 1
            }
            ans.add(line)
            counts = counts.filterValues { it != 0 }.toMutableMap()
        }
        return ans
    }
}

fun main() {
    val soln = `2610`()
    println(soln.findMatrix(intArrayOf(1,3,4,1,2,3,1)))
}