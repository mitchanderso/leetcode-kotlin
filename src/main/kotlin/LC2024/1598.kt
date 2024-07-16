package LC2024

import java.util.*

class `1598` {
    fun minOperations(logs: Array<String>): Int {
        val fileStack = Stack<String>()
        fileStack.push("ROOT")

        logs.forEach { command ->
            if (command == "../") {
                if (fileStack.peek() != "ROOT") {
                    fileStack.pop()
                }
            } else if (command != "./") {
                fileStack.push(command)
            }

        }

        return fileStack.size - 1
    }
}

fun main() {
    val soln = `1598`()
    println(soln.minOperations(arrayOf("d1/","d2/","../","d21/","./"))) // 2
}