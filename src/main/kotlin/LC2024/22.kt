package LC2024

class `22` {
    val ans = mutableListOf<String>()
    fun generateParenthesis(n: Int): List<String> {
        dfs(n, 0, 0, 0, java.lang.StringBuilder())
        return ans
    }

    fun dfs(n: Int, right: Int, currentOpenLeft: Int, totalOpenedLeft: Int, working: StringBuilder)  {
        if (n == right) {
            ans.add(working.toString())
            return
        }

        // Add a left if possible
        if (totalOpenedLeft < n) {
            val newWorking = java.lang.StringBuilder(working).append("(")
            dfs(n, right, currentOpenLeft + 1, totalOpenedLeft + 1, newWorking)
        }

        // Add a right
        if (currentOpenLeft > 0) {
            val newWorking = java.lang.StringBuilder(working).append(")")
            dfs(n, right + 1, currentOpenLeft - 1, totalOpenedLeft, newWorking)
        }
    }
}

fun main() {
    val soln = `22`()
    println(soln.generateParenthesis(3))
}