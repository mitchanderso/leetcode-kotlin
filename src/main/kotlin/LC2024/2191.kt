package LC2024

class `2191` {
    data class Jumbled(val initial: Int, val jumbled: Int, val index: Int)
    fun sortJumbled(mapping: IntArray, nums: IntArray): IntArray {

        return nums.mapIndexed { index, it ->
            Jumbled(it, it.jumble(mapping), index)
        }.sortedWith(
            compareBy<Jumbled> { it.jumbled }.thenBy { it.index }
        ).map { it.initial }.toIntArray()
    }

    private fun Int.jumble(mapping: IntArray): Int {
        val str = this.toString()
        val ans = StringBuilder()
        str.forEach {
            val jumbledDigit = mapping[it.digitToInt()]
            ans.append(jumbledDigit)
        }
        return ans.toString().toInt()
    }
}



fun main() {
    val soln = `2191`()
    println(soln.sortJumbled(intArrayOf(8,9,4,0,2,1,3,5,7,6), intArrayOf(991,338,38)).toList())
}