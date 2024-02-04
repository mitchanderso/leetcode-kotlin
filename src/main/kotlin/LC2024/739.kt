package LC2024

import java.util.*


class `739` {

    fun dailyTemperatures(temperatures: IntArray): IntArray {
        val ans = IntArray(temperatures.size) { 0 }
        val stack = Stack<Pair<Int, Int>>()

        temperatures.forEachIndexed { index, temp ->
            while (stack.isNotEmpty() && temp > stack.peek().first) {
                val tmp = stack.pop()
                val distance = index - tmp.second
                ans[tmp.second] = distance
                //println("The number at index ${tmp.second} first greater number is the number $temp at index $index which is $distance away")
            }
            stack.push(temp to index)
        }


        return ans
    }


}

fun main() {
    val soln = `739`()
    println(soln.dailyTemperatures(intArrayOf(5, 2, 3, 6)).toList())
}