package LC2024

class `2096` {
    val paths = mutableListOf<MutableList<Pair<Int, Char>>>()
    fun getDirections(root: TreeNode?, startValue: Int, destValue: Int): String {
        //println("Path to start")

        dfs(root!!, startValue, mutableListOf())
        dfs(root!!, destValue, mutableListOf())

        paths[0].add(0, root.`val` to 'X')
        paths[1].add(0, root.`val` to 'X')



        val lca = paths[0] intersect paths[1]

        var idx = 0
        while (idx < paths[0].size && idx < paths[1].size && paths[0][idx].first == paths[1][idx].first ) {
            idx++
        }

        val sp = paths[0].subList(idx, paths[0].size)
        val ep = paths[1].subList(idx, paths[1].size)


        val ans = StringBuilder()
        repeat(sp.size) {
            ans.append("U")
        }

        for (i in 0 until ep.size) {
            ans.append(ep[i].second)
        }

        // From start to LCA is always going upwards
        // From LCA to end, that is a direction

//        println("Path to end")
//        println( dfs(root, destValue, mutableListOf()))
        return ans.toString()
    }

    fun dfs(root: TreeNode?,destValue: Int, path: MutableList<Pair<Int, Char>>)  {
        if (root == null) return

        if (root.`val` == destValue) {
            paths.add(path.toMutableList())
            return
        }

        if (root.left != null) {
            path.add(root.left!!.`val` to 'L')
            dfs(root.left, destValue, path)
            path.removeAt(path.size - 1)
        }

        if (root.right != null) {
            path.add(root.right!!.`val` to 'R')
            dfs(root.right, destValue, path)
            path.removeAt(path.size - 1)
        }


    }
}

fun main() {
    val soln = `2096`()
    println(soln.getDirections(

//        TreeNode(5).apply {
//            left = TreeNode(1).apply {
//                left = TreeNode(3)
//            }
//            right = TreeNode(2).apply {
//                left = TreeNode(6)
//                right = TreeNode(4)
//            }
//        },
        TreeNode(2).apply {
            left = TreeNode(1)
        },
        2,
        1


    ))
}