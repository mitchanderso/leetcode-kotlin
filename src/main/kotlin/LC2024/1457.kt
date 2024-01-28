package LC2024



class `1457` {

    var count = 0

    fun pseudoPalindromicPaths (root: TreeNode?): Int {
        if (root == null) return 0
        palinR(root, mutableMapOf())
        return count
    }

    fun palinR(root: TreeNode, freqs: MutableMap<Int, Int>) {
        freqs[root.`val`] = freqs.getOrDefault(root.`val`, 0) + 1

        if (root.left == null && root.right == null) {
            var localCount = 0
            for (num in freqs.values) {
                if (num % 2 != 0) localCount++
            }
            if (freqs.values.count { it % 2 == 1 } == 1) count++

        }

        if (root.left != null) {
            palinR(root.left!!, freqs)
        }

        if (root.right != null) {
            palinR(root.right!!, freqs)
        }

        freqs[root.`val`] = freqs[root.`val`]!! - 1

    }


}

fun main() {
    val soln = `1457`()
    val tree = TreeNode(2).apply {
        left  = TreeNode(3).apply {
            left = TreeNode(3)
            right = TreeNode(1)
        }
        right = TreeNode(1).apply {
            right = TreeNode(1)
        }
    }
    println(soln.pseudoPalindromicPaths(tree))
}