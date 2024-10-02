package LC2024

import java.util.*

class CustomStack(maxSize: Int) {

    val max = maxSize
    val stack = Stack<Int>()

    fun push(x: Int) {
        if (stack.size < max) stack.push(x)
    }

    fun pop(): Int {
        if (stack.isEmpty()) return -1
        return stack.pop()
    }

    fun increment(k: Int, `val`: Int) {
        val size = stack.size

        // Take the total size and see how many we have to "skip"
        var skip = if ( k > size ) 0 else size - k

        val skipped = mutableListOf<Int>()
        while (skip > 0) {
            skipped.add(stack.pop())
            skip--
        }

        val incremented = mutableListOf<Int>()
        while (stack.isNotEmpty()) {
            incremented.add(stack.pop() + `val`)
        }

        for (i in incremented.size - 1 downTo 0) stack.push(incremented[i])
        for (i in skipped.size - 1 downTo 0) stack.push(skipped[i])

    }

}

fun main() {
    val soln = CustomStack(5)
    soln.push(4)
    soln.push(1)
    soln.push(7)
    soln.push(2)
    soln.push(3)
    soln.increment(3,2)
    println(soln.stack)
}