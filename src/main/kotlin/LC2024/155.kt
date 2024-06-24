package LC2024

import kotlin.math.min

class `155` {
    class MinStack() {

        val mstack: MutableList<Pair<Int, Int>> = mutableListOf()

        fun push(`val`: Int) {
            val pmin = if (mstack.isNotEmpty()) mstack.first().second else Int.MAX_VALUE
            mstack.add(0, `val` to min(pmin, `val`))
        }

        fun pop() {
            mstack.removeAt(0)
        }

        fun top(): Int {
            return mstack.first().first
        }

        fun getMin(): Int {
            return mstack.first().second
        }

    }
}

fun main() {
    val soln = `75`()
    println(soln.sortColors(intArrayOf(2,0,2,1,1,0)))
}