package LC2024

class `2181` {
    fun mergeNodes(head: ListNode?): ListNode? {
        var newHead = ListNode(-1)
        var headRef = newHead
        var movingHead = head?.next
        var sum = 0

        while (movingHead != null) {
            if (movingHead.`val` == 0 ) {
                val newNext = ListNode(sum)
                newHead.next = newNext
                newHead = newHead.next!!
                sum = 0
            } else {
                sum += movingHead.`val`
            }

            movingHead = movingHead.next
        }

        return headRef.next
    }
}

fun main() {
    val soln = `2181`()
    println(soln.mergeNodes(
        ListNode(0).apply {
            next = ListNode(3).apply {
                next = ListNode(1).apply {
                    next = ListNode(0).apply {
                        next = ListNode(4).apply {
                            next = ListNode(5).apply {
                                next = ListNode(2).apply {
                                    next = ListNode(0)
                                }
                            }
                        }
                    }
                }
            }
        }
    ))
}