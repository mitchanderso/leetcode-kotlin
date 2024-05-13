package LC2024

import com.sun.source.tree.Tree

class `100` {
    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        if (p == null && q == null) return true
        if (p == null || q == null) return false
        if (p?.`val` == q?.`val`) {
                return isSameTree(p?.left, q?.left) && isSameTree(p?.right, q?.right)
            }

            return false
    }



    fun isSameNode(n1: TreeNode?, n2: TreeNode?) : Boolean {
        if (n1 == null && n2 == null ) return true
        if (n1 == null && n2 != null || n2 == null && n1 == null) return false
        if (n1?.`val` == n2?.`val`) return true
        return false
    }
}



fun main() {
    val soln = `100`()
    val p = TreeNode(1).apply { left = TreeNode(1) }
    val q = TreeNode(1).apply { right = TreeNode(2) }
    println(soln.isSameTree(p, q))



}