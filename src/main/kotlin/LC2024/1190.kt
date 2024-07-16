package LC2024

import java.util.*

class `1190` {
    private fun StringBuilder.reverseInRange(s1: Int, e1: Int)  : java.lang.StringBuilder{
        var start = s1
        var end = e1
        var temp = 'X'
        while (end > start) {
            temp = this[start]
            this[start] = this[end]
            this[end] = temp
            start++
            end--
        }
        return this
    }

    fun reverseParentheses(s: String): String {
        val stack = Stack<Int>()
        val sb = StringBuilder(s)
        sb.forEachIndexed { idx, cha ->
            if (cha == '(') stack.push(idx)
            else if (cha == ')') {
                sb.reverseInRange(stack.pop() + 1, idx - 1)
            }
        }

        return sb.filterNot { it == '(' || it == ')' }.toString()
    }
}



fun main() {
    val soln = `1190`()
//    println( StringBuilder("abcd").reverseInRange(2, 3))
    println(soln.reverseParentheses("(ed(et(oc))el)"))
}