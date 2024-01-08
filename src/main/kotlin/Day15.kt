import java.io.File
import java.lang.Math.*

fun main() {
    val challenge = Day15()
    val file = File(Day15::class.java.getResource("/day15/simple.txt").file)
//    challenge.p1(file)
    challenge.p2(file)

}

class Day15 {

    fun hash(line: String) : Int {
        var sum = 0
        line.forEach { cha ->
            sum += cha.code
            sum *= 17
            sum %= 256
        }
        return sum
    }

    fun p1(file: File) {
        var sum = 0
        file.forEachLine { line ->
            val split = line.split(',')
            split.forEach {seq ->
                val hash = hash(seq)
                sum += hash
            }
        }
        println("p1 Ans is $sum")
    }

    fun p2(file: File) {
        var sum = 0
        val boxes = mutableMapOf<Int, MutableList<Pair<String, Int>>>()
        file.forEachLine { line ->
            val split = line.split(',')
            split.forEach {seq ->
                val letters = seq.substring(0, seq.indexOfAny( charArrayOf('-', '=')))
                val character = seq[seq.indexOfAny( charArrayOf('-', '='))]
                println("The letters are $letters $character")

                val box = hash(letters)
                if (character == '-') {
                    if (boxes.containsKey(box)) {
                        val indexOfBoxToReplace = boxes[box]!!.map { it.first }.indexOf(letters)
                        if (indexOfBoxToReplace >= 0) {
                            boxes[box]!!.removeAt(indexOfBoxToReplace)
                        }
                    }
                } else {
                    val lensPower = seq.last().digitToInt()
                    if (boxes.containsKey(box)) {
                        val indexOfBoxToReplace = boxes[box]!!.map { it.first }.indexOf(letters)
                        if (indexOfBoxToReplace >= 0) {
                            boxes[box]!![indexOfBoxToReplace] = letters to lensPower
                        } else {
                            boxes[box]!!.add(letters to lensPower)
                        }
                    } else {
                        boxes[box] = mutableListOf(letters to lensPower)
                    }
                }

                boxes.forEach { k, v ->
                    println("Box $k = $v")
                }

                println()
            }
        }

        boxes.forEach { k, v ->
            var boxSum = 1 + k
            var valueIdx = 1

            v.forEach {
                sum += boxSum * valueIdx * it.second
                valueIdx++
            }
        }

        println("p2 Ans is $sum")
    }

}
