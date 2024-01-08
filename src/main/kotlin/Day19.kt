
import java.io.File
import java.lang.Math.*
import java.util.*
import kotlin.math.absoluteValue

fun main() {
    val challenge = Day19()
    val file = File(Day19::class.java.getResource("/day19/simple.txt").file)
//    challenge.p1(file)
    challenge.p2(file)
}

class Day19 {

    data class Instruction(val symbol: Char? = null, val part: Char? = null, val comparator: String? = null, val dest: String) {
        fun isNonConditional() = symbol == null || part == null || comparator == null
        companion object {
            operator fun invoke(instruction: String): Instruction {
                return if (instruction.contains(':')) {
                    val destination = instruction.substring(instruction.indexOf(':') + 1)
                    val part = instruction.first()
                    val symbol = instruction[1]
                    val comparator = instruction.substring(instruction.indexOf(symbol) + 1 until instruction.indexOf(':'))
                    Instruction(symbol, part, comparator, destination)
                } else {
                    Instruction(dest = instruction)
                }

            }
        }
    }

    fun isAccepted( workflowName: String,  values: List<Pair<Char, String>>, workflows: MutableMap<String, List<Instruction>>) : Boolean {
        val workflow = workflows[workflowName]!!
        for (i in 0 until workflow.size - 1) {
            val currentWorkflow = workflow[i]
            values.forEach { value ->
                if (value.first == currentWorkflow.part) {
                    if (currentWorkflow.symbol == '<') {
                        if (value.second.toInt() < currentWorkflow.comparator!!.toInt()) {
                            if (currentWorkflow.dest == "A") return true
                            else if (currentWorkflow.dest == "R") return false
                            else return isAccepted(currentWorkflow.dest, values, workflows)
                        }
                    } else if (currentWorkflow.symbol == '>') {
                        if (value.second.toInt() > currentWorkflow.comparator!!.toInt()) {
                            if (currentWorkflow.dest == "A") return true
                            else if (currentWorkflow.dest == "R") return false
                            else return isAccepted(currentWorkflow.dest, values, workflows)
                        }
                    }
                }
            }

        }
        val final = workflow.last()
        if (final.dest == "A") return true
        else if (final.dest == "R") return false
        else return isAccepted(final.dest, values, workflows)
    }

    fun p1(file: File) {
        val workflows = mutableMapOf<String, List<Instruction>>()
        var foundSpace = false
        var acceptedScore = 0
        file.forEachLine { line ->
            if (line.isEmpty()) {
                foundSpace = true
            }
            else if (!foundSpace) {
                val workflowName = line.substring(0 until line.indexOf('{'))
                val instructions = line.substring(line.indexOf('{') + 1 until line.indexOf('}')).split(',')
                val typedInstructions = instructions.map { Instruction(it) }
                workflows[workflowName] = typedInstructions
            } else {
                val chars = line.drop(1).dropLast(1).split(',').map { it.first() to it.substring(it.indexOf('=') + 1) }
                val isAccepted = isAccepted("in", chars, workflows)
                if (isAccepted) acceptedScore += chars.map { it.second.toInt() }.sum()
            }



        }
        println("Ans is $acceptedScore")
    }

    fun p2(file: File) {
        val workflows = mutableMapOf<String, List<Instruction>>()
        var foundSpace = false

        file.forEachLine { line ->
            if (line.isEmpty()) {
                foundSpace = true
            } else if (!foundSpace) {
                val workflowName = line.substring(0 until line.indexOf('{'))
                val instructions = line.substring(line.indexOf('{') + 1 until line.indexOf('}')).split(',')
                val typedInstructions = instructions.map { Instruction(it) }
                workflows[workflowName] = typedInstructions
            }

        }
       val ans = isAcceptedPart2Cheat("in", mutableListOf('x' to 1 .. 4000, 'm' to 1 .. 4000, 'a' to 1 .. 4000, 's' to 1 .. 4000), workflows)
//        val ans = isAcceptedPart2("in", mutableListOf('x' to 0 .. 4, 'm' to 0 .. 4, 'a' to 0 .. 4, 's' to 0 .. 4),  workflows)
        println("Ans is $ans")
    }

    fun MutableList<Pair<Char, IntRange>>.product(): Long {
        return this.fold(1L) { acc, it ->  acc * ( (it.second.last.toLong() - it.second.first.toLong()) + 1L) }
    }

    fun IntRange.merge(other: IntRange) = (maxOf(first, other.first) .. minOf(last, other.last))

    fun isAcceptedPart2Cheat( workflowDest: String,  values: MutableList<Pair<Char, IntRange>>, workflows: MutableMap<String, List<Instruction>>) : Long {

        return when (workflowDest) {
            "R" -> 0L
            "A" -> values.product()
            else -> {
                val workflow = workflows[workflowDest]!!
                val valuesCopy = values.toMutableList()
                var sum = 0L
                workflow.forEach { instruction ->
                    if (instruction.isNonConditional()) sum += isAcceptedPart2Cheat(instruction.dest, valuesCopy, workflows)
                    else {
                        val xmas = instruction.part!!
                        val indexOfXmas = valuesCopy.map { it.first }.indexOf(xmas)
                       if (instruction.symbol!! == '<') {
                           val instructionRange = 1 until instruction.comparator!!.toInt()

                           val newRange = valuesCopy[indexOfXmas].second.merge(instructionRange)
                           valuesCopy[indexOfXmas] = xmas to newRange
                       } else {
                           val instructionRange= instruction.comparator!!.toInt() .. 4000

                           val newRange = valuesCopy[indexOfXmas].second.merge(instructionRange)
                           valuesCopy[indexOfXmas] = xmas to newRange

                       }

                        val instrunctionRangeReversed = if (instruction.symbol!! == '<') {
                            val instructionRange= instruction.comparator!!.toInt() .. 4000
                            valuesCopy[indexOfXmas].second.merge(instructionRange)
                        } else {
                            val instructionRange = 1 until instruction.comparator!!.toInt()
                            valuesCopy[indexOfXmas].second.merge(instructionRange)
                        }

                       sum += isAcceptedPart2Cheat(instruction.dest, valuesCopy, workflows).also { valuesCopy[indexOfXmas] = xmas to instrunctionRangeReversed }
                    }
                }
                sum
            }
        }
    }

    fun isAcceptedPart2( workflowName: String,  values: MutableList<Pair<Char, IntRange>>, workflows: MutableMap<String, List<Instruction>>) : Long {
// 777244491000000
        var ans = 0L
        val workflow = workflows[workflowName]!!
//        val valuesCopy = values.toMutableList()
        for (i in 0 until workflow.size - 1) {
            val currentWorkflow = workflow[i]

            for (j in 0 until values.size) {
                val value = values[j]

                //values.forEach { value ->
                    if (value.first == currentWorkflow.part) {
                        if (currentWorkflow.symbol == '<') {
                            if (currentWorkflow.dest == "A") {
                                if (value.second.last > currentWorkflow.comparator!!.toInt()) {
                                    val valuesCopy = values.toMutableList()
                                    val idx = values.map { it.first }.indexOf( value.first )
                                    valuesCopy.removeAt(idx)
                                    valuesCopy.add(value.first to (value.second.first until currentWorkflow.comparator!!.toInt()))
                                    ans += valuesCopy.product()
                                }

                                //break

                            }
                            else if (currentWorkflow.dest == "R") {
                                ans += 0
                                //break
                            }
                            else {
                                if (value.second.last > currentWorkflow.comparator!!.toInt()) {
                                    val valuesCopy = values.toMutableList()
                                    val idx = values.map { it.first }.indexOf( value.first )
                                    valuesCopy.removeAt(idx)
                                    valuesCopy.add(value.first to (value.second.first until currentWorkflow.comparator!!.toInt()))
                                    ans += isAcceptedPart2(currentWorkflow.dest, valuesCopy, workflows)
                                    //break
                                }

                            }
                        }
                        else if (currentWorkflow.symbol == '>') {
                            if (currentWorkflow.dest == "A") {
                                if (value.second.last > currentWorkflow.comparator!!.toInt()) {
                                    val valuesCopy = values.toMutableList()

                                    val idx = values.map { it.first }.indexOf( value.first )
                                    valuesCopy.removeAt(idx)
                                    valuesCopy.add(value.first to (currentWorkflow.comparator!!.toInt() + 1 .. value.second.last))
                                    ans += valuesCopy.product()
                                }

                                //break
                            }
                            else if (currentWorkflow.dest == "R") {
                                ans += 0
                                //break
                            }
                            else {
                                if (value.second.last > currentWorkflow.comparator!!.toInt()) {
                                    val valuesCopy = values.toMutableList()
                                    val idx = values.map { it.first }.indexOf( value.first )
                                    valuesCopy.removeAt(idx)
                                    valuesCopy.add(value.first to (currentWorkflow.comparator!!.toInt() + 1 .. value.second.last))
                                    ans += isAcceptedPart2(currentWorkflow.dest, valuesCopy, workflows)
                                }


                                //break
                            }
                        }
                    }
                //}
            }


        }
        val final = workflow.last()
        if (final.dest == "A")  {
            val valuesCopy = values.toMutableList()
//            val idx = values.map { it.first }.indexOf( final.part )
//            valuesCopy.removeAt(idx)
//            valuesCopy.add(final.part!! to (values[idx].second.first until final.comparator!!.toInt()))
            ans += valuesCopy.product()
        }
        else if (final.dest == "R") ans += 0
        else {
            val valuesCopy = values.toMutableList()
//            val idx = values.map { it.first }.indexOf( final.part )
//            valuesCopy.removeAt(idx)
//            valuesCopy.add(final.part!! to (values[idx].second.first until final.comparator!!.toInt()))
            ans += isAcceptedPart2(final.dest, valuesCopy, workflows)
        }
        return ans
    }

}

