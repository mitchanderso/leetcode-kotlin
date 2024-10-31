package LC2024

class `3331` {
    fun findSubtreeSizes(parent: IntArray, s: String): IntArray {
        // You can get the parent by just checking the array

        // You can get the children by scanning the array to find the index
        // of the values that equal the parent node
        val newParents = IntArray(parent.size) { -1 }

        for (i in 1 until parent.size) {
            var parentNode = parent[i]
            // Walk backwards to find its ancestor
            while (parentNode != -1 && s[parentNode] != s[i]) {
                parentNode = parent[parentNode]
            }
            newParents[i] = if (parentNode != -1) parentNode else parent[i]
        }


        val children = Array(parent.size) { mutableListOf<Int>() }
        for (i in 0 until newParents.size) {
            val parent = newParents[i]
            if (parent != -1) {
                children[parent].add(i)
            }
        }

        val ans = IntArray(newParents.size) { 1 }
        dfs(0, children, ans)

        return ans
    }


    fun dfs(current: Int, children: Array<MutableList<Int>>, memo: IntArray) : Int {
        var ans = 1
        // Get all children nodes
        val childNodes = children[current]
        childNodes?.forEach { ans += dfs(it, children, memo) }
        memo[current] = ans
        return ans
    }


}

fun main() {
    val soln = `3331`()
    println(soln.findSubtreeSizes(intArrayOf(-1,0,0,1,1,1), "abaabc").toList())
    println(soln.findSubtreeSizes(intArrayOf(-1,0,4,0,1), "abbba").toList())
}