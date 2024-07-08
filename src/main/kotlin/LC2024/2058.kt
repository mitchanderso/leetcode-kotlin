package LC2024

import kotlin.math.min

class `2058` {
    fun nodesBetweenCriticalPoints(head: ListNode?): IntArray {
        var behind = ListNode(head!!.`val`).apply { next = head }
        var current = head
        var previousCriticalIndex = -1
        var next = current?.next
        var min = Int.MAX_VALUE
        var firstCriticalIndex = -1
        var idx = 0

        while (next != null) {
            if ( current!!.`val` < behind.`val` && current.`val` < next!!.`val` ||
                     current!!.`val` > behind.`val` && current.`val` > next!!.`val` ) {
                if (firstCriticalIndex == -1) {
                    firstCriticalIndex = idx
                }
                if (previousCriticalIndex == -1) {
                    previousCriticalIndex = idx
                } else {
                    min = min(min, idx - previousCriticalIndex)
                    previousCriticalIndex = idx
                }
            }
            behind = current
            current = next
            next = next?.next
            idx++
        }



        return intArrayOf(
            if (min == Int.MAX_VALUE) -1 else min,
            if (min == Int.MAX_VALUE) -1 else previousCriticalIndex - firstCriticalIndex
        )
    }
}

fun main() {
    val soln = `2058`()
    println(soln.nodesBetweenCriticalPoints(
        ListNode(5).apply {
            next = ListNode(3).apply {
                next = ListNode(1).apply {
                    next = ListNode(2).apply {
                        next = ListNode(5).apply {
                            next = ListNode(1).apply {
                                next = ListNode(2)
                            }
                        }
                    }
                }
            }
        }
    ).toList())
}