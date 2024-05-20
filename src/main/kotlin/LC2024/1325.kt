package LC2024

class `1325` {
    fun removeLeafNodes(root: TreeNode?, target: Int): TreeNode? {
        dfs(root, target)
        if (root?.left == null && root?.right == null && root?.`val` == target) return null
        return root
    }

    fun dfs(node: TreeNode?, target: Int) : Boolean {

        if (node == null) return true
        if (node.left == null && node.right == null && node.`val` == target) {
            return true
        }

        val left = dfs(node.left, target)
        val right = dfs(node.right, target)

        if (left) {
            node.left = null
        }
        if (right) {
            node.right = null;
        }

        if (left && right && target == node.`val`) return true

        return false;
    }





}

git filter-branch -f --env-filter \
"GIT_AUTHOR_NAME='Mitch Anderson'; GIT_AUTHOR_EMAIL='mitch.anderso@gmail.com'; \
GIT_COMMITTER_NAME='Mitch Anderson'; GIT_COMMITTER_EMAIL='mitch.anderso@gmail.com';" HEAD



fun main() {
    val soln = `1325`()

    println(soln.removeLeafNodes(
        TreeNode(2).apply {
            left = TreeNode(7).apply {
                left = TreeNode(2)
                right = TreeNode(2)
            }
            right = TreeNode(2)
        },
        2
    ))




}