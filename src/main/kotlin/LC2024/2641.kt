package LC2024

import java.util.*

class `2641` {
    fun replaceValueInTree(root: TreeNode?): TreeNode? {
        // Do a bfs level by level
        val q = LinkedList<Pair<TreeNode, TreeNode?>>()
        if (root == null) return root

        //var ans = root

        q.offer(root to null)
        val newValues = mutableListOf<Pair<TreeNode, Int>>()
        while (q.isNotEmpty()) {
            val qSize = q.size
            val allAtThisLevel = mutableListOf<Pair<TreeNode, TreeNode?>>()
            var sumOfLevel = 0
            for (i in 0 until qSize) {
                // Go through all in the level
                val current = q.poll()
                allAtThisLevel.add(current)
                sumOfLevel += current.first.`val`
                // Add to q
                if (current.first.left != null) q.offer(current.first.left!! to current.first)
                if (current.first.right != null) q.offer(current.first.right!! to current.first)
            }


            allAtThisLevel.forEach { (node, parent) ->
                val left = parent?.left?.`val` ?: 0
                val right = parent?.right?.`val` ?: 0
                val amount = if (parent == null) 0 else sumOfLevel - left - right
                newValues.add(node to amount)
            }


        }
        newValues.forEach { it.first.`val` = it.second }

        return root
    }
}

fun main() {
    val soln = `2641`()
    println(soln.replaceValueInTree(
        TreeNode(5).apply {
            left = TreeNode(4).apply {
                left = TreeNode(1)
                right = TreeNode(10)
            }
            right = TreeNode(9).apply {
                right = TreeNode(7)
            }
        }
    ))
}