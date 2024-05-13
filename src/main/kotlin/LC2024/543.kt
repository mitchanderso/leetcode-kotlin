package LC2024

import com.sun.source.tree.Tree
import java.lang.Math.max

class `543` {
    fun diameterOfBinaryTree(root: TreeNode?): Int {
        if (root == null) return 0
        val leftDepth = longestPath(root.left)
        val rightDepth = longestPath(root.right)
        return leftDepth + rightDepth
    }

    fun longestPath(root: TreeNode?) : Int {
        if (root == null) return 0
        val leftDepth =  longestPath(root.left)
        val rightDepth =  longestPath(root.right)
        return max(leftDepth, rightDepth) + 1
    }
}



fun main() {
    val soln = `543`()
    val p = TreeNode(1).apply {
        left = TreeNode(1)
        right = TreeNode(2).apply { right = TreeNode(3).apply { left = TreeNode(4) } }
    }
    println(soln.diameterOfBinaryTree(p))



}