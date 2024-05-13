package LC2024

 class ListNode(var `val`: Int) {
         var next: ListNode? = null
     }

class `19` {
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        var clonedHead : ListNode? = ListNode(-69).apply { next = head }
        var originalClonedHead = clonedHead
        var fast = head

        var nodeCount = 0

        while (fast != null) {
            nodeCount++
            fast = fast.next
        }

        val nodeIdxToDelete = (nodeCount - n)
        //if (nodeIdxToDelete <= 0) return null
        for (i in 0 until nodeIdxToDelete ) {
            clonedHead = clonedHead?.next
        }
        clonedHead?.next = clonedHead?.next?.next
        return originalClonedHead?.next
    }




}



fun main() {
    val soln = `19`()

//    val p = ListNode(1).apply { next = ListNode(2).apply { next = ListNode(3).apply { next = ListNode(4).apply { next = ListNode(5) } } } }
//    println(soln.removeNthFromEnd(p,2))
//
    val q = ListNode(1).apply { next = ListNode(2) }
    println(soln.removeNthFromEnd(q,2))

//    val l = ListNode(1)
//    println(soln.removeNthFromEnd(l,1))



}