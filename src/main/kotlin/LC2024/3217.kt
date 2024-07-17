package LC2024

class `3217` {
    fun modifiedList(nums: IntArray, head: ListNode?): ListNode? {

        val numMap = nums.associateWith { true }


        var mutableHead = head
        var dummy = ListNode(-1)
        var prev = dummy


        while (mutableHead != null) {
            if (!numMap.containsKey(mutableHead.`val`)) {
                prev.next = mutableHead
//                prev = prev.next!!
                prev = mutableHead
            }
            mutableHead = mutableHead.next
        }

        prev.next = null
        return dummy.next
    }




}

fun main() {
    val soln = `3217`()
    println(soln.modifiedList(
        nums = intArrayOf(3),
        head = ListNode(1).apply {
            next = ListNode(2).apply {
                next = ListNode(3).apply {
                    next = ListNode(4).apply {
                        next = ListNode(5)
                    }
                }
            }
        }
    ))
}