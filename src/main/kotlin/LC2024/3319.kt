package LC2024

class `3319` {
    fun kthLargestPerfectSubtree(root: TreeNode?, k: Int): Int {
        if (root == null) return -1
        // BFS over each node
        val ans = mutableListOf<Int>()
        dfs(root, ans)
        val sorted = ans.sortedDescending()
        if (k - 1 >= ans.size) return -1
        return sorted[k - 1]
    }


    fun dfs(root: TreeNode?, ans: MutableList<Int>) : Int {
        if (root == null) return 0

        val leftTreeHeight = dfs(root.left, ans)
        val rightTreeHeight = dfs(root.right, ans)

        // has unbalanced children
        if ( (root.left == null && root.right != null)
            || (root.right == null && root.left != null) ) {
            return -1
        }

        // Is not perfect child / children are not the same height
        if ( leftTreeHeight == -1 || rightTreeHeight == -1 || leftTreeHeight != rightTreeHeight) {
            return -1
        }

        val height = leftTreeHeight + rightTreeHeight + 1
        ans.add(height)
        return leftTreeHeight + rightTreeHeight + 1
    }
}

fun main() {
    val soln = `3319`()
    println(soln.kthLargestPerfectSubtree(
        TreeNode(5).apply {
            left = TreeNode(3).apply {
                left = TreeNode(5).apply {
                    left = TreeNode(1)
                    right = TreeNode(8)
                }
                right = TreeNode(2)
            }
            right = TreeNode(6).apply {
                left = TreeNode(5).apply {
                    left = TreeNode(6)
                    right = TreeNode(8)
                }
                right = TreeNode(7)
            }
        },
        2))
}