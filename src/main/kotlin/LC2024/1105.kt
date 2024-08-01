package LC2024

import kotlin.math.max
import kotlin.math.min

class `1105` {
    var memo : Array<IntArray>? = null
    fun minHeightShelves(books: Array<IntArray>, shelfWidth: Int): Int {
        memo = Array(books.size) { IntArray (shelfWidth + 1) { -1 } }
        return solve(books, shelfWidth, shelfWidth, 0, 0)
    }

    fun solve(books: Array<IntArray>, shelfWidth: Int, remainingWidth: Int, n: Int, maxHeightInRow: Int) : Int {
        if (n >= books.size) {
            return maxHeightInRow
        }
        if (memo!![n][remainingWidth] != -1) {
            return memo!![n][remainingWidth]
        }

        var sameShelf = Int.MAX_VALUE
        // Add to same shelf
        if (remainingWidth >= books[n][0]) {
            sameShelf = solve(books, shelfWidth, remainingWidth - books[n][0], n + 1, max(maxHeightInRow,books[n][1]))
        }

        // Add to new shelf
        val newShelf = maxHeightInRow + solve(books, shelfWidth, shelfWidth - books[n][0], n + 1, books[n][1])
        memo!![n][remainingWidth] = min(sameShelf, newShelf)
        return memo!![n][remainingWidth]
    }
}

fun main() {
    val soln = `1105`()
//    println(soln.minHeightShelves(
//        arrayOf(
//            intArrayOf(1,1),
//            intArrayOf(2,3),
//            intArrayOf(2,3)
//        ), 4
//    ))
    println(soln.minHeightShelves(
        arrayOf(
            intArrayOf(1,1),
            intArrayOf(2,3),
            intArrayOf(2,3),
            intArrayOf(1,1),
            intArrayOf(1,1),
            intArrayOf(1,1),
            intArrayOf(1,2),
        ), 4
    ))
}