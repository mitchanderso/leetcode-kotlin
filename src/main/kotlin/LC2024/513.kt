package LC2024

import com.sun.source.tree.Tree
import java.lang.Math.pow
import java.util.LinkedList
import java.util.Queue
import kotlin.math.pow

class `513` {
    fun findBottomLeftValue(root: TreeNode?): Int {
        if (root == null) return -1
        val q = LinkedList<TreeNode>()
        q.offer(root!!)
        var leftMostValue = -1
        while(q.isNotEmpty()) {
            val size = q.size
            for (i in 0 until q.size) {
                val node = q.poll()
                if (i == 0) leftMostValue = node.`val`
                if (node.left != null) q.offer(node.left)
                if (node.right != null) q.offer(node.right)
            }
        }
        return leftMostValue
    }

    fun isHappy(n: Int): Boolean {
        val seen = mutableMapOf<Int, Boolean>()

        var squared = n.toString().fold(0) { acc, c ->
            acc + c.digitToInt().toDouble().pow(2).toInt()
        }

        while (squared != 1) {
            if (seen.containsKey(squared)) return false
            seen[squared] = true
            squared = squared.toString().fold(0) { acc, c ->
                acc + c.digitToInt().toDouble().pow(2).toInt()
            }

        }

        return true
    }




}



fun main() {
    val soln = `513`()
    val p = TreeNode(1).apply {
        left = TreeNode(2) .apply {
            left = TreeNode(4)
        }
        right = TreeNode(3).apply {
            left = TreeNode(5).apply {
                left = TreeNode(7)
            }
            right = TreeNode(6)
        }
    }
//    println(soln.findBottomLeftValue(p))
    println(soln.isHappy(19))



}