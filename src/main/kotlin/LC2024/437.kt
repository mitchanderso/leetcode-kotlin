package LC2024

class `437` {
    var ans = 0
    fun pathSum(root: TreeNode?, targetSum: Int): Int {
        outerDfs(root, targetSum.toLong())
        return ans
    }

    fun outerDfs(root: TreeNode?, targetSum: Long) {
        if (root == null) return
        dfs(root, targetSum, 0)
        outerDfs(root.left, targetSum)
        outerDfs(root.right, targetSum)
    }

    fun dfs(root: TreeNode?, targetSum: Long, runningSum: Long)  {
        if (root == null) return
        var running = runningSum + root!!.`val`
        if (running == targetSum) ans++
        dfs(root.left, targetSum, running)
        dfs(root.right, targetSum, running)
    }




}



fun main() {
    val soln = `437`()
    println(soln.pathSum(
        TreeNode(10).apply {
            left = TreeNode(5).apply {
                left = TreeNode(10)
                right = TreeNode(10)
            }
        },
        15
    ))




}