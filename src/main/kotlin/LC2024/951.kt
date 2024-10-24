package LC2024

class `951` {
    fun flipEquiv(root1: TreeNode?, root2: TreeNode?): Boolean {
        return dfs(root1, root2)
    }

    fun dfs(root1: TreeNode?, root2: TreeNode?) : Boolean {
        if (root1?.`val` != root2?.`val`) return false
        if (root1 == null && root2 == null) return true
        if (root1 == null && root2 != null || root2 == null && root1 != null) {
            return false
        }

        val originalLeft = root1?.left?.`val`
        val originalRight = root1?.right?.`val`

        val otherLeft = root2?.left?.`val`
        val otherRight = root2?.right?.`val`

        if ( (originalLeft == otherLeft && originalRight == otherRight) ) {
            return dfs(root1?.left, root2?.left) && dfs(root1?.right, root2?.right)
        }
        else if ((originalLeft == otherRight && originalRight == otherLeft)) {
            // Flip
            return dfs(root1?.left, root2?.right) && dfs(root1?.right, root2?.left)
        }
        return false
    }
}

fun main() {
    val soln = `951`()
    println(soln.flipEquiv(TreeNode(1), null))
    println(soln.flipEquiv(null, null))
//    println(soln.flipEquiv(
//        TreeNode(1).apply {
//            left = TreeNode(2)
//            right = TreeNode(3).apply {
//                left = TreeNode(6)
//            }
//        },
//
//        TreeNode(1).apply {
//            left = TreeNode(3).apply {
//                right = TreeNode(6)
//            }
//            right = TreeNode(2)
//        }
//
//    ))
    println(soln.flipEquiv(
        TreeNode(1).apply {
            left = TreeNode(2).apply {
                left = TreeNode(4)
                right = TreeNode(5).apply {
                    left = TreeNode(7)
                    right = TreeNode(8)
                }
            }
            right = TreeNode(3).apply {
                left = TreeNode(6)
            }
        },

        TreeNode(1).apply {
            right = TreeNode(2).apply {
                left = TreeNode(4)
                right = TreeNode(5).apply {
                    left = TreeNode(8)
                    right = TreeNode(7)
                }
            }
            left = TreeNode(3).apply {
                right = TreeNode(6)
            }
        }

    ))
}