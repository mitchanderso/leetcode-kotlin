package LC2024

class `3309` {
    fun maxGoodNumber(nums: IntArray): Int {
        val comparator = Comparator { s1: String, s2: String ->
            ((s1 + s2).compareTo(s2 + s1)) * -1
        }

        return nums
            .map { Integer.toBinaryString(it) }
            .sortedWith(comparator)
            .fold("") {acc, curr -> acc + curr}
            .toInt(2)

    }
}

fun main() {
    val soln = `3309`()
    println(soln.maxGoodNumber(intArrayOf(1,2,3)))
    println(soln.maxGoodNumber(intArrayOf(2,8,16)))
}