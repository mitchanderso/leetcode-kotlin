package AOC2024

import java.io.File

class `Day3` {
    fun mul(file : File): Long {
        val regex = Regex("(mul\\((\\d+),(\\d+)\\))")
        return file.parse()
            .map { memoryLine ->
                regex
                    .findAll(memoryLine)
                    .fold(0L) { acc, matchResult ->
                        val num1 = Integer.parseInt(matchResult.groupValues[2])
                        val num2 = Integer.parseInt(matchResult.groupValues[3])
                        acc + (num1 * num2)
                    }

            }.sum()
    }

    fun mul2(file : File): Long {
        var enabled = true
        //mul\((\d+),(\d+)\)|(do)|(don't)
        val regex = Regex("mul\\((\\d+),(\\d+)\\)|(don't)|(do)")
        return file.parse()
            .map { memoryLine ->
                regex
                    .findAll(memoryLine)
                    .fold(0L) { acc, matchResult ->

                        if (matchResult.groupValues[0].equals("do")) {
                            enabled = true
                            return@fold acc
                        } else if (matchResult.groupValues[0].equals("don't")){
                            enabled = false
                            return@fold acc
                        } else {
                            val num1 = Integer.parseInt(matchResult.groupValues[1])
                            val num2 = Integer.parseInt(matchResult.groupValues[2])
                            if (enabled) {
                                return@fold acc + (num1 * num2)
                            }
                            return@fold acc
                        }

                    }

            }.sum()



    }



    fun File.parse() : List<String> {
        return this.readLines()
    }
}

fun main() {
    val soln = Day3()
    val file = File(Day3::class.java.getResource("/AOC2024/day3/input.txt").file)
    println(soln.mul(file))
    println(soln.mul2(file))
}