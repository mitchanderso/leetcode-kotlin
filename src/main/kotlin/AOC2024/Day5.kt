package AOC2024

import java.io.File

class `Day5` {


    fun page(file : File, file2: File): Long {
        val pages = file.parse()
        val tasks = file2.parse().map { it.split(",") }
        val mustBeBefore = mutableMapOf<String, MutableSet<String>>()
        pages.forEach { page ->
            // Left must be before right
            val left = page.substring(0,2)
            val right = page.substring(3, 5)
            mustBeBefore[right] = mustBeBefore.getOrDefault(right, mutableSetOf()).apply { add(left) }
        }

        // Map -> check that number, if any of the tasks after it are in the set, its bad

        val validTasks = mutableListOf<List<String>>()
        tasks.forEachIndexed { index, task ->
            if (isValid(mustBeBefore, task)) validTasks.add(task)
        }

        val ans = validTasks.sumOf { list ->
            val midpoint = list.size / 2
            Integer.parseInt(list[midpoint])
        }

        return ans.toLong()
    }

    fun isValid(cannotBeAfter: MutableMap<String, MutableSet<String>>, task: List<String>) : Boolean {
        var validTask = true
        for (i in 0 until task.size) {
            if (!validTask) break
            for (j in i + 1 until task.size) {
                val earlyTask = task[i]
                val laterTask = task[j]
                val onesThatMustBeBefore = cannotBeAfter.getOrDefault(earlyTask, mutableSetOf())
                if (onesThatMustBeBefore.contains(laterTask)) {
                    validTask = false
                    break
                }
            }
        }
        return validTask
    }

    fun page2(file : File, file2: File): Long {
        val pages = file.parse()
        val tasks = file2.parse().map { it.split(",") }
        val cannotBeAfter = mutableMapOf<String, MutableSet<String>>()
        val mustBeBefore = mutableMapOf<String, MutableSet<String>>()
        pages.forEach { page ->
            // Left must be before right
            val left = page.substring(0,2)
            val right = page.substring(3, 5)
            cannotBeAfter[right] = cannotBeAfter.getOrDefault(right, mutableSetOf()).apply { add(left) }
            mustBeBefore[left] = mustBeBefore.getOrDefault(left, mutableSetOf()).apply { add(right) }
        }

        // Map -> check that number, if any of the tasks after it are in the set, its bad

        val invalidTasks = mutableListOf<MutableList<String>>()

        tasks.forEachIndexed { index, task ->
            if (!isValid(cannotBeAfter, task)) invalidTasks.add(task.toMutableList())
        }

        // To rearrange
        // Find the number that has no dependencies
        val validTasks = mutableListOf<List<String>>()
        invalidTasks.forEach { invalidTask ->
            val arranged = arrange(mutableListOf(), invalidTask.toMutableList(), 0, invalidTask.size, cannotBeAfter, mutableListOf<MutableList<String>>() )

            validTasks.add(arranged.first())
        }

        for (update in invalidTasks) {
            var i = update.size - 1
            while (i >= 0) {
                val page = update[i]

                val rule = cannotBeAfter[page]
                if (rule != null) {
                    for (target in rule) {
                        val targetIdx = update.indexOf(target)
                        if (targetIdx != -1 && targetIdx < i) {
                            val tmp = update[targetIdx]
                            update[targetIdx] = page
                            update[i] = tmp
                            i++ // reconsider this page after swapping
                            break
                        }
                    }
                }
                i--
            }
        }

        val ans = validTasks.sumOf { list ->
            val midpoint = list.size / 2
            Integer.parseInt(list[midpoint])
        }


        return ans.toLong()
    }

    fun arrange(list: MutableList<String>, remainingValues: MutableList<String>, index: Int, targetLength: Int, cannotBeAfter: MutableMap<String, MutableSet<String>>, validAnswers: MutableList<MutableList<String>>) : MutableList<MutableList<String>> {
        if (remainingValues.size == 0 && list.size == targetLength) {
            validAnswers.add(list)
        }

        if (validAnswers.size >= 1) return validAnswers

        for (i in 0 until remainingValues.size) {
            val newList = list.toMutableList().also { it.add(remainingValues[i]) }
            if (isValid(cannotBeAfter, newList)) {
                val newRemainingValues = remainingValues.toMutableList()
                newRemainingValues.removeAt(i)
                 arrange(newList, newRemainingValues, index, targetLength, cannotBeAfter, validAnswers)
            }
        }


        return validAnswers

    }






    fun File.parse() : List<String> {
        return this.readLines()
    }
}

fun main() {
    val soln = Day5()
    val file = File(Day5::class.java.getResource("/AOC2024/day5/input.txt").file)
    val file2 = File(Day5::class.java.getResource("/AOC2024/day5/input2.txt").file)
    println(soln.page(file, file2))
    println(soln.page2(file, file2))

}