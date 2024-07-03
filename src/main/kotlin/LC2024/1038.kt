package LC2024

class `1038` {
    var carry = 0
    fun bstToGst(root: TreeNode?): TreeNode? {
        sum(root)
        return root
    }

    fun sum(root: TreeNode?) {
        if (root == null) return
        sum(root.right)
        carry += root.`val`
        root.`val` = carry
        sum(root.left)
    }
}

fun main() {
    val soln = `1038`()
//    println(soln.bstToGst(
//        TreeNode(4).apply {
//            left = TreeNode(3).apply {  left = TreeNode(2).apply { left = TreeNode(1) }}
//            right = TreeNode(6)
//        }
//    ))
    println(soln.bstToGst(
        TreeNode(4).apply {
            left = TreeNode(1).apply {
                left = TreeNode(0)
                right = TreeNode(2).apply {
                    right = TreeNode(3)
                }
            }
            right = TreeNode(6).apply {
                left = TreeNode(5)
                right = TreeNode(7).apply {
                    right = TreeNode(8)
                }
            }
        }
    ))
}