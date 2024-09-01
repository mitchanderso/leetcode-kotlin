package LC2024

class Node(var `val`: Int) {
        var children: List<Node?> = listOf()
}

class `590` {
    fun postorder(root: Node?): List<Int> {
        val list = mutableListOf<Int>()
        if (root != null) traverse(root, list)
        return list
    }

    fun traverse(current: Node, list: MutableList<Int>) {
        current.children.forEach {
            if (it != null) traverse(it, list)
        }
        list.add(current.`val`)
    }
}

fun main() {
    val soln = `590`()
    println(soln.postorder(
        Node(1).apply {
            children = listOf(
                Node(3).apply {
                              children = listOf(Node(5), Node(6))
                },
                Node(2),
                Node(4)
            )
        }
    ))
}