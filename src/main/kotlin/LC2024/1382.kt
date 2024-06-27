package LC2024

import java.util.*

class `1382` {
    fun balanceBST(root: TreeNode?): TreeNode? {
        val q = LinkedList<TreeNode>()
        val list = mutableListOf<Int>()
        q.offer(root!!)
        while(q.isNotEmpty()) {
            val top = q.poll()
            list.add(top.`val`)
            if (top.left != null) q.offer(top.left)
            if (top.right != null) q.offer(top.right)
        }
        list.sort()



        return balance(list, 0, list.size - 1)
    }

    fun balance(list: MutableList<Int>, l: Int, r: Int) : TreeNode? {
        if (l > r) {
            return null
        }
        val middle = (l + r) / 2

        val tn = TreeNode(list[middle])

        tn.left = balance(list, l, middle - 1)
        tn.right = balance(list, middle + 1, r)

        return tn
    }
}

fun main() {
    val soln = `1382`()
    println(soln.balanceBST(
        TreeNode(7).apply {
            left = TreeNode(3)
            right = TreeNode(5).apply {
                left = TreeNode(10)
                right = TreeNode(1)
            }
        }
    ))
}