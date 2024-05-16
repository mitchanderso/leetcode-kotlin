package LC2024

class `2331` {
    fun evaluateTree(root: TreeNode?): Boolean {
        return dfs(root!!)
    }

    fun dfs(node: TreeNode) : Boolean {

        if (node.left == null && node.right == null) {
            return if (node.`val` == 0) false else true
        }

        return if (node.`val` == 2) {
            // Or
            dfs(node.left!!) or dfs(node.right!!)
        } else {
            dfs(node.left!!) and dfs(node.right!!)
        }


    }





}



fun main() {
    val soln = `2331`()

    println(soln.evaluateTree(
        TreeNode(2).apply {
            left = TreeNode(1)
            right = TreeNode(3).apply {
                left = TreeNode(0)
                right = TreeNode(1)
            }
        }
    ))




}