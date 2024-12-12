package AOC2024


import java.io.File

class `Day9` {

    data class MemoryBlock(val code: String, val length: Int) {
        fun isBlank() : Boolean = code == "." && length >= 1
    }
    fun p1(file: File) : Long {
        val memory = file.parse()[0]
        var expandedMemory = mutableListOf<MemoryBlock>()
        var ans = 0L

        var freespace = false
        var memoryCounter = 0
        for (i in 0 until memory.length) {
            val length = memory[i].digitToInt()
            if (freespace) {
                expandedMemory.add(MemoryBlock(".", length))
            } else {
                expandedMemory.add(MemoryBlock(memoryCounter.toString(), length))
                memoryCounter++
            }
            freespace = !freespace
        }

        expandedMemory = expandedMemory.filter { it.length > 0 }.toMutableList()

        // Two pointer approach
        var emptyBlockIdx = 0
        var endBlockIdx = expandedMemory.size - 1

        while (endBlockIdx > emptyBlockIdx) {
            while (!expandedMemory[emptyBlockIdx].isBlank()) {
                emptyBlockIdx++
            }

            while (expandedMemory[endBlockIdx].isBlank()) {
                endBlockIdx--
            }

            if (endBlockIdx < emptyBlockIdx) {
                break
            }

            if (expandedMemory[endBlockIdx].length == expandedMemory[emptyBlockIdx].length) {
                expandedMemory[emptyBlockIdx] = expandedMemory[endBlockIdx]
                emptyBlockIdx++
                endBlockIdx--
            } else if (expandedMemory[endBlockIdx].length < expandedMemory[emptyBlockIdx].length) {
                val tmp = expandedMemory[emptyBlockIdx]
                expandedMemory[emptyBlockIdx] = expandedMemory[endBlockIdx]
                val sizeDifference = tmp.length - expandedMemory[endBlockIdx].length
                expandedMemory.add(emptyBlockIdx + 1, MemoryBlock(".", sizeDifference))
                emptyBlockIdx++

            } else if (expandedMemory[endBlockIdx].length > expandedMemory[emptyBlockIdx].length) {
                expandedMemory[emptyBlockIdx] = MemoryBlock(expandedMemory[endBlockIdx].code, expandedMemory[emptyBlockIdx].length)
                val sizeDifference = expandedMemory[endBlockIdx].length - expandedMemory[emptyBlockIdx].length
                expandedMemory[endBlockIdx] = MemoryBlock(expandedMemory[endBlockIdx].code, sizeDifference)
                emptyBlockIdx++

            }
            // Execute the swaps
        }

        var memPoint = 0
        for (i in 0 .. endBlockIdx) {
            if (expandedMemory[i].isBlank()) {
                break
            }
            repeat(expandedMemory[i].length) {
                println("${expandedMemory[i].code} * $memPoint")
                ans += (expandedMemory[i].code.toLong() * memPoint)
                memPoint++
            }

        }

        return ans
    }

    fun p2(file: File) : Long {
        val memory = file.parse()[0]
        var expandedMemory = mutableListOf<MemoryBlock>()
        var ans = 0L

        var freespace = false
        var memoryCounter = 0
        for (i in 0 until memory.length) {
            val length = memory[i].digitToInt()
            if (freespace) {
                expandedMemory.add(MemoryBlock(".", length))
            } else {
                expandedMemory.add(MemoryBlock(memoryCounter.toString(), length))
                memoryCounter++
            }
            freespace = !freespace
        }

        expandedMemory = expandedMemory.filter { it.length > 0 }.toMutableList()

        // Two pointer approach
        var emptyBlockIdx = 0
        var endBlockIdx = expandedMemory.size - 1

        val blockCount = expandedMemory.filter { !it.isBlank() }.size

        while (endBlockIdx >= 0) {
            emptyBlockIdx = 0
            var suitableBlock = false

            while (expandedMemory[endBlockIdx].isBlank()) {
                endBlockIdx--
            }

            while (emptyBlockIdx < endBlockIdx) {
                if (expandedMemory[emptyBlockIdx].isBlank() && expandedMemory[emptyBlockIdx].length >= expandedMemory[endBlockIdx].length) {
                    suitableBlock = true
                    break
                }
                emptyBlockIdx++
            }

            if (suitableBlock) {
                val tmp = expandedMemory[emptyBlockIdx]
                expandedMemory[emptyBlockIdx] = expandedMemory[endBlockIdx]
                expandedMemory[endBlockIdx] = MemoryBlock(".", expandedMemory[endBlockIdx].length)
                val sizeDifference = tmp.length - expandedMemory[endBlockIdx].length
                if (sizeDifference > 0) {
                    expandedMemory.add(emptyBlockIdx + 1, MemoryBlock(".", sizeDifference))
                }

            } else {
                endBlockIdx--
            }



            // Execute the swaps
        }

        var memPoint = 0
        for (i in 0 until expandedMemory.size) {

            repeat(expandedMemory[i].length) {
                println("${expandedMemory[i].code} * $memPoint")
                if (!expandedMemory[i].isBlank()) {
                    ans += (expandedMemory[i].code.toLong() * memPoint)
                }
                memPoint++
            }

        }


        return ans
    }

    fun File.parse() : List<String> {
        return this.readLines()
    }
}

fun main() {
    val soln = Day9()
    val file = File(Day9::class.java.getResource("/AOC2024/day9/input.txt").file)
    //println(soln.p1(file))
    println(soln.p2(file))

}