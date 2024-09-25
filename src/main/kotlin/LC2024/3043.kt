package LC2024

import kotlin.math.max

data class LCPTrie(var isTerminal: Boolean, val children: MutableMap<Char, LCPTrie>,val current: Char)

class `3043` {

    fun longestCommonPrefix(arr1: IntArray, arr2: IntArray): Int {
        val arr1Trie = LCPTrie(false, mutableMapOf(), '.')
        (arr1).forEach { addToTrie(arr1Trie, it.toString()) }

        val arr2Trie = LCPTrie(false, mutableMapOf(), '.')
        (arr2).forEach { addToTrie(arr2Trie, it.toString()) }

        var maxDepth = 0
        arr1.forEach { word ->
            maxDepth = max(maxDepth, maxDepth(arr2Trie, word.toString()))
        }

        arr2.forEach { word ->
            maxDepth = max(maxDepth, maxDepth(arr1Trie, word.toString()))
        }

        return maxDepth

    }

    fun maxDepth(rootNode: LCPTrie, search: String) : Int {
        var movingRoot = rootNode
        var i = 0
        while (i < search.length) {
            val currentChar = search[i]
            if (movingRoot.children.containsKey(currentChar)) {
                movingRoot = movingRoot.children[currentChar]!!
            } else return i

            i++
        }

        return i
    }

    fun addToTrie(rootNode: LCPTrie, toAdd: String) {
        var movingRoot = rootNode
        for (i in 0 until toAdd.length) {
            val currentChar = toAdd[i]
            val isTerminal = i == toAdd.length - 1
            if (!movingRoot.children.containsKey(currentChar)) {
                movingRoot.children[currentChar] = (LCPTrie(isTerminal, mutableMapOf(), currentChar))
            }
            movingRoot = movingRoot.children[currentChar]!!
        }
    }


}

fun main() {
    val soln = `3043`()
    println(soln.longestCommonPrefix(intArrayOf(1,2,3,12), intArrayOf(123)))
}