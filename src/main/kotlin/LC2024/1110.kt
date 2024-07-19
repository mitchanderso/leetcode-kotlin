package LC2024

import java.util.*

class `1110` {
    fun delNodes(root: TreeNode?, to_delete: IntArray): List<TreeNode?> {
        val parentMap = mutableMapOf<TreeNode, TreeNode?>()

        val toDeleteSet = to_delete.toSet()
        if (root!!.`val` !in toDeleteSet) parentMap[root!!] = null

        val bfsQ = LinkedList<TreeNode>()
        bfsQ.offer(root!!)
        while (bfsQ.isNotEmpty()) {
            var current = bfsQ.poll()
            if (current.left != null) {

                bfsQ.offer(current.left)
                if (current.left!!.`val` in toDeleteSet) {
                    current.left = null
                } else parentMap[current.left!!] = if (current.`val` !in toDeleteSet) current else null

            }

            if (current.right != null) {

                bfsQ.offer(current.right)
                if (current.right!!.`val` in toDeleteSet) {
                    current.right = null
                } else parentMap[current.right!!] = if (current.`val` !in toDeleteSet) current else null
            }
        }

        val ans = mutableListOf<TreeNode>()
        parentMap.forEach { (k,v) ->
            if (v == null) ans.add(k)
        }
        return ans
    }
}

fun main() {
    val soln = `1110`()
    println(soln.delNodes(
        TreeNode(1).apply {
            left = TreeNode(2).apply {
                left = TreeNode(4)
                right = TreeNode(5)
            }
            right = TreeNode(3).apply {
                left = TreeNode(6)
                right = TreeNode(7)
            }
        },
        intArrayOf(3,5)
    ))
}