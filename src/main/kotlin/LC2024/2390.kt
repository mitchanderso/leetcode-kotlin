package LC2024

import java.util.*

class `2390` {
    fun removeStars(s: String): String {
        var stack = Stack<Char>()
        s.forEach { c ->
            if (c != '*') stack.push(c)
            else stack.pop()
        }

        return stack.toList().joinToString(separator = "")
    }

}



fun main() {
    val soln = `2390`()
    println(soln.removeStars("leet**cod*e"))

}