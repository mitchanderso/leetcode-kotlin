package LC2024

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}


class `872` {

    fun leafSimilar(root1: TreeNode?, root2: TreeNode?): Boolean {
        val leaves1 = mutableListOf<Int>()
        val leaves2 = mutableListOf<Int>()
        findLeaves(root1, leaves1)
        findLeaves(root2, leaves2)
        return leaves1 == leaves2
    }

    fun findLeaves(root1: TreeNode?, leaves: MutableList<Int>) {
        if (root1 == null) return
        if (root1.left == null && root1.right == null) leaves.add(root1.`val`)
        findLeaves(root1.left, leaves)
        findLeaves(root1.right, leaves)
    }


}

fun main() {
    val soln = `872`()
    val tree1 = TreeNode(6).apply { left = TreeNode(5); right = TreeNode(6) }
    val tree2 = TreeNode(6).apply { left = TreeNode(5); right = TreeNode(6) }
    println(soln.leafSimilar(tree1, tree2))
}