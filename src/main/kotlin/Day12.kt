import utils.Utils.split
import java.io.File
import java.lang.Math.*

fun main() {
    val challenge = Day12()
    val file = File(Day12::class.java.getResource("/day12/simple.txt").file)
    challenge.p1(file)

}

class Day12 {


    // DP
//    Dynamic programming. f (pos, groups, len) = number of ways to:
//
//    parse the first pos positions
//
//    have groups groups of #
//
//    with the last group of # having length len
//
//    The transitions are:
//
//    if the character is # or ?, we can continue the previous group or start a new group:
//
//    f (pos + 1, groups + (len == 0), len + 1) += f (pos, groups, len)
//
//    if the character is . or ?, and the length of the current group is zero or exactly what we need, we can proceed without a group:
//
//    f (pos + 1, groups, 0) += f (pos, groups, len)
//
//    In the end, the answer is f (lastPos, numberOfGgroups, 0). (Add a trailing . to the string for convenience to avoid cases.)
//



    fun p1(file: File) {

        var ans = 0L
        file.forEachLine {
            val (line, arrangementsString) = it.split(' ')
            val arrangements = arrangementsString.split(',').map { it.toInt() }
            ans += combos(line, arrangements)
        }


        println("Answer is $ans")

    }

    fun combos(line: String, arrangements: List<Int>) : Long{
        println("Line $line has arrangements $arrangements")
        val splitLine = line.split("#+".toRegex())

        // Figure out which hashes we remove from the array
        var start = 0
        var groupSize = 0
        while (start < line.length) {
            if (line[start] == '#' || line[start] == '.') {
                println("Found a group of question makrs $groupSize long")
                groupSize = 0
            }
            if (line[start] == '?') {
                groupSize++
            }
            start++

        }

        return 1L
    }

    fun tryToFit(spaces: String, spacesIndex: Int, arrangements: List<Int>, arrangementsIndex: Int) : Int {
        if (spaces.length < arrangements[arrangementsIndex]) {
            println("CANT fit ${arrangements[arrangementsIndex]} inside of ${spaces.length}")
            return 0
        }

        println("CAN fit ${arrangements[arrangementsIndex]} inside of ${spaces.length}")
        return 1
    }



}

