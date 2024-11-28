package LC2024

import java.util.LinkedList

class `773` {
    val COMPLETE = "123450"
    fun slidingPuzzle(board: Array<IntArray>): Int {
        val visited = mutableSetOf<String>()
        val q = LinkedList<Pair<String, Int>>()
        q.offer(board.mapToString() to 0)

        while (q.isNotEmpty()) {
            val (current, moves) = q.poll()
            if (current == COMPLETE) return moves



            // Get the possibles
            val positionOfZero = current.indexOf('0')

            // Move 0 left
            if (positionOfZero != 0 && positionOfZero != 3) {
                val mutableCurrent = StringBuilder(current)
                val temp = mutableCurrent[positionOfZero - 1]
                mutableCurrent[positionOfZero - 1] = '0'
                mutableCurrent[positionOfZero] = temp
                if (mutableCurrent.toString() !in visited) {
                    visited.add(mutableCurrent.toString())
                    q.offer(mutableCurrent.toString() to moves + 1)
                }
            }

            // Move 0 Right
            if (positionOfZero != 2 && positionOfZero != 5) {
                // +3 to move down
                val mutableCurrent = StringBuilder(current)
                val temp = mutableCurrent[positionOfZero + 1]
                mutableCurrent[positionOfZero + 1] = '0'
                mutableCurrent[positionOfZero] = temp
                if (mutableCurrent.toString() !in visited) {
                    visited.add(mutableCurrent.toString())
                    q.offer(mutableCurrent.toString() to moves + 1)
                }
            }

            // Move up
            if (positionOfZero !in setOf(0,1,2)) {
                // -3 to move down
                val mutableCurrent = StringBuilder(current)
                val temp = mutableCurrent[positionOfZero - 3]
                mutableCurrent[positionOfZero - 3] = '0'
                mutableCurrent[positionOfZero] = temp
                if (mutableCurrent.toString() !in visited) {
                    visited.add(mutableCurrent.toString())
                    q.offer(mutableCurrent.toString() to moves + 1)
                }
            }

            // Move down
            if (positionOfZero !in setOf(3,4,5)) {
                // +3 to move down
                val mutableCurrent = StringBuilder(current)
                val temp = mutableCurrent[positionOfZero + 3]
                mutableCurrent[positionOfZero + 3] = '0'
                mutableCurrent[positionOfZero] = temp
                if (mutableCurrent.toString() !in visited) {
                    visited.add(mutableCurrent.toString())
                    q.offer(mutableCurrent.toString() to moves + 1)
                }
            }
        }

        return -1

    }

    fun Array<IntArray>.mapToString() : String {
        val sb = StringBuilder()
        for (row in 0 until 2) {
            for (col in 0 until 3) {
                sb.append(this[row][col])
            }
        }
        return sb.toString()
    }

//    fun String.mapToBoard() : Array<IntArray> {
//        var col = 0
//        var row = 0
//        val ans = Array (2) { IntArray(3) }
//        for (i in 0 until this.length) {
//            ans[row][col] = this[i].digitToInt()
//            col++
//            if (col == 3) {
//                col = 0
//                row = 1
//            }
//        }
//    }
}

fun main() {
    val soln = `773`()
    println(soln.slidingPuzzle(
        arrayOf(
            intArrayOf(4,1,2),
            intArrayOf(5,0,3)
        )
    ))
}