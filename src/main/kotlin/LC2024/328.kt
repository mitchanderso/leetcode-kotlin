package LC2024

class `328` {
    fun oddEvenList(head: ListNode?): ListNode? {
        var ptr = head?.next
        var movingHead = head

        var secondRef = head?.next
        while (ptr != null && movingHead != null) {
            var tmp = movingHead.next
            movingHead.next = ptr.next
            ptr = ptr.next
            movingHead = tmp
        }
        //
        movingHead = head
        while (movingHead?.next != null) movingHead = movingHead.next
        movingHead?.next = secondRef
        return head

    }




}



fun main() {
    val soln = `328`()
    println(soln.oddEvenList(
        ListNode(1).apply { next = ListNode(2).apply { next = ListNode(3).apply { next = ListNode(4).apply { next = ListNode(5).apply { next = ListNode(6).apply { next = ListNode(7).apply { next = ListNode(8) } } } } } } }
    ))




}