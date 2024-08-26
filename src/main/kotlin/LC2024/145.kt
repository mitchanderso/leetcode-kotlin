package LC2024

import java.util.*

class `145` {
    fun postorderTraversal(root: TreeNode?): List<Int> {
        postOrderIterative(root)
        val ans = mutableListOf<Int>()
        traverse(root, ans)
        return ans

    }

    fun postOrderIterative(root: TreeNode?) : List<Int> {
        val stack = Stack<TreeNode>()
        val ans = mutableListOf<Int>()
        if (root == null) return emptyList()
        stack.push(root)
        while (stack.isNotEmpty()) {

            val top = stack.pop()!!
            ans.add(0, top.`val`)
            if (top.left != null) stack.push(top.left)
            if (top.right != null) stack.push(top.right)
        }

        return ans
    }
    fun traverse(root: TreeNode?, ans: MutableList<Int>) {
        if (root == null) return
        traverse(root.left, ans)
        traverse(root.right, ans)
        ans.add(root.`val`)
    }
}

fun main() {
    val soln = `145`()
    println(soln.postorderTraversal(
        TreeNode(1).apply {
            right = TreeNode(2).apply {
                left = TreeNode(3)
                right = TreeNode(4)
            }
        }
    ))
}