import java.io.File
import java.lang.Math.abs

fun main() {
    val challenge = Day9()
    val file = File(Day9::class.java.getResource("/day9/input.txt").file)
//    challenge.p1(file)
    challenge.p2(file)

}

class Day9 {

    fun differences(list: List<Long>) : List<Long> {
        val differences = mutableListOf<Long>()
        var sumDifferences = 0L
        for (i in 1 until list.size) {
            val difference = list[i] - list[i - 1]
            differences.add( difference )
            sumDifferences += difference
        }
        return differences
    }

    fun p1(file: File) {

        var totalSum = 0L
        file.forEachLine { line ->
            var tree = mutableListOf<MutableList<Long>>()
            var numbers = line.split(' ').map { it.toLong() }
            tree.add(numbers.toMutableList())
            while ( numbers.sum() != 0L) {
                val differences = differences(numbers)
                tree.add(differences.toMutableList())
                numbers = differences
            }

            var sum = 0L

            tree.reverse()
            tree.forEachIndexed { index, ints ->
                if (index == 0) {
                    tree[index] += 0
                }
                else {
                    val newNum = ints.last() + tree[index - 1][ints.size - 1]
                    tree[index] += newNum
                    //println(tree[index])
                    sum = newNum
                }

            }

            totalSum += sum

            println("------------------------")

        }
        println("part1 total sum is $totalSum")


    }

    fun p2(file: File) {

        var totalSum = 0L
        file.forEachLine { line ->
            var tree = mutableListOf<MutableList<Long>>()
            var numbers = line.split(' ').map { it.toLong() }
            tree.add(numbers.toMutableList())
            while ( numbers.sum() != 0L) {
                val differences = differences(numbers)
                tree.add(differences.toMutableList())
                numbers = differences
            }

            var sum = 0L

            tree.reverse()
            tree.forEachIndexed { index, ints ->

                if (index == 0) {
                    tree[index].add(0, 0)
                    println(tree[index])
                    //println("Increase 0 by 0")
                }
                else {
                    val newNum = ints.first() + tree[index - 1][0]
                    tree[index].add(0, -newNum)
                    println(tree[index])
                    sum = newNum
                }

            }

            totalSum += sum

        }
        println("part2 total sum is $totalSum")


    }

}

