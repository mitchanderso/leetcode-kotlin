package LC2024

import java.util.*


class `1609`() {
    fun isEvenOddTree(root: TreeNode?): Boolean {
        if (root == null) return false
        val q = LinkedList<TreeNode>()
        var level = 0
        q.offer(root)
        while (q.isNotEmpty()) {
            val sz = q.size
            var previous = if (level % 2 == 0) Int.MIN_VALUE else Int.MAX_VALUE
            for(i in 0 until sz) {
                val curr = q.poll()
                //println("On level $level checking that ${curr.`val`} is ${if(level % 2 == 0) "strictly greater" else "strictly less"} than $previous")
                if (level % 2 == 0) {
                    if (curr.`val` <= previous || curr.`val` % 2 == 0) return false
                } else {
                    if (curr.`val` >= previous || curr.`val` % 2 != 0) return false
                }
                previous = curr.`val`
                if (curr.left != null) q.offer(curr.left)
                if (curr.right != null) q.offer(curr.right)
            }
            level++
        }
        return true
    }

}


fun main() {
    val soln = `1609`()
    println(
        soln.isEvenOddTree(
            TreeNode(5).apply {
                left = TreeNode(9).apply {
                    left = TreeNode(3)
                    right = TreeNode(5)
                }
                right = TreeNode(1).apply {
                    left = TreeNode(7)
                }
            }
        )
    )
//    println(
//        soln.isEvenOddTree(
//            TreeNode(1).apply {
//                left = TreeNode(10).apply {
//                    left = TreeNode(3).apply {
//                        left = TreeNode(12)
//                        right = TreeNode(8)
//                    }
//                }
//                right = TreeNode(4).apply {
//                    left = TreeNode(7).apply {
//                        left = TreeNode(6)
//                    }
//                    right = TreeNode(9).apply {
//                        right = TreeNode(2)
//                    }
//                }
//            }
//        )
//    )




}