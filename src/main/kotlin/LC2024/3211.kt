package LC2024

import java.util.*

class `3211` {
    fun validStrings(n: Int): List<String> {
        val list = LinkedList<String>()
        list.offer("0")
        list.offer("1")
        for (i in 1 until n) {
            for (j in 0 until list.size) {
                var popped = list.pop()
                if (popped.last() == '0') {
                    list.offer(popped + "1")
                } else {
                    list.offer(popped + "0")
                    list.offer(popped + "1")
                }
            }
        }
        return list
    }
}

fun main() {
    val soln = `3211`()
    println(soln.validStrings(3))
}