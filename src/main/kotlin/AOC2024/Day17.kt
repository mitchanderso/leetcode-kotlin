package AOC2024


import utils.Utils.pow
import utils.Utils.split
import java.io.File
import java.util.LinkedList
import java.util.Stack
import kotlin.math.min
import kotlin.math.pow


class Day17 {

    data class Computer(val a: Int, val b: Int, val c: Int, val program: List<Command>) {
        fun literal(comIdx: Int) = program[comIdx].operand
        fun combo(comIdx: Int): Int {
            val operand = program[comIdx].operand
            return when (operand) {
                0,1,2,3 -> operand
                4 -> a
                5 -> b
                6 -> c
                else -> throw IllegalArgumentException()
            }
        }
    }
    data class Command(val opcode: Int, val operand: Int)


    fun p1(file: File)  {
        var computer = file.parse()
        var instruction = 0
        while (instruction < computer.program.size) {
            val opcode = computer.program[instruction].opcode
            when (opcode) {
                0 -> {
                    computer = computer.copy(
                        a = (computer.a / 2.pow(computer.combo(instruction)))
                    )
                    instruction++
                }
                1 -> {
                    computer = computer.copy(
                        b = (computer.b xor computer.literal(instruction))
                    )
                    instruction++
                }
                2 -> {
                    computer = computer.copy(
                        b = computer.combo(instruction) % 8
                    )
                    instruction++
                }
                3 -> {
                    if (computer.a != 0) {
                        instruction = computer.literal(instruction)
                    } else {
                        instruction++
                    }
                }
                4 -> {
                    computer = computer.copy(
                        b = computer.b xor computer.c
                    )
                    instruction++
                }
                5 -> {
                    print("${computer.combo(instruction) % 8},")
                    instruction++
                }
                6 -> {
                    computer = computer.copy(
                        b = (computer.a / 2.pow(computer.combo(instruction)))
                    )
                    instruction++
                }
                7 -> {
                    computer = computer.copy(
                        c = (computer.a / 2.pow(computer.combo(instruction)))
                    )
                    instruction++
                }
            }


            }

    }






    fun File.parse() : Computer {
        val lines = readLines()
        val a = lines[0].substringAfter(": ").toLong().toInt()
        val b = lines[1].substringAfter(": ").toLong().toInt()
        val c = lines[2].substringAfter(": ").toLong().toInt()

        val instructions = lines[4].substringAfter(": ").split(",")
        val commands = mutableListOf<Command>()
        for (i in 0 .. instructions.size - 2 step 2) {
            commands.add(Command(
                opcode = instructions[i][0].digitToInt(),
                operand = instructions[i + 1][0].digitToInt())
            )
        }

        return Computer(a, b, c, commands)

    }
}




fun main() {
    val soln = Day17()
    val file = File(Day17::class.java.getResource("/AOC2024/day17/input.txt").file)
    soln.p1(file)

}