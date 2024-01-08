import java.io.File

fun main() {
    val challenge = Day8()
    val file = File(Day8::class.java.getResource("/day8/input.txt").file)
//    challenge.p1(file)
    challenge.p2(file)

}

class Day8 {
    val steps = "LRRRLRRLRRLRLRRLRRRLLRRLLRRLRRRLRLRRLLRRLRRLRLRRRLRRRLRLRLRLRRRLRRLRRRLRLRRLLLRLRLLRLRRRLRLRRRLRRRLLLRRLRLRRLRRRLLRRLRRLRRLRRRLRRLRRLRRLRLRRLRLRLRLRLRLRRRLRRLRLLLRRRLRLRRRLRRRLLRRLRRRLRRLRRRLRRRLRLRRRLRRLRLLRRLLRLRRLRLRLLRRLLRRLLRRLRRLRRRLRLRRLRLRRRLRRRLLRLRRLLLLRRRLLRLLLRRLRRRLRRRLRRRLRLRRRLRRRLRRRLRLRRRR"
//    val steps = "LR"
    fun p1(file: File) {
        val map = mutableMapOf<String, Pair<String, String>>()
        file.forEachLine { line ->
            val origin = line.subSequence(0, 3)
            val (left, right) = line.subSequence(7, 15).split(",")
            map[origin.toString()] = left.trim() to right.trim()
        }

        var idx = 0
        var ans = 0
        var location = "AAA"
        while (location != "ZZZ") {
//            println("Location is now $location")
            location = if (steps[idx] == 'L') map[location]!!.first else map[location]!!.second
            ans++
            idx++
            if (idx >= steps.length) idx = 0
        }

        println("It takes $ans steps")
    }


    fun p2(file: File) {
        val map = mutableMapOf<String, Pair<String, String>>()
        file.forEachLine { line ->
            val origin = line.subSequence(0, 3)
            val (left, right) = line.subSequence(7, 15).split(",")
            map[origin.toString()] = left.trim() to right.trim()
        }

        var idx = 0
        var ans = 1L
        var locations = map.keys.filter { it[2] == 'A' }
        val needToFinish = locations.size
        val timeTakenToFinish = mutableListOf<Long>()
        while (timeTakenToFinish.size != needToFinish) {
            locations = locations.map { if (steps[idx] == 'L') map[it]!!.first else map[it]!!.second }
            locations.filter { it[2] == 'Z' }.forEach { timeTakenToFinish.add(ans) }
            locations = locations.filter { it[2] != 'Z' }
            ans++
            idx++
            if (idx >= steps.length) idx = 0

        }

        fun String.solveForNode(): Long {
            var cur = this
            var i = 0
            while (cur.endsWith('Z') == false) {
                val inst = steps[i++ % steps.length]
                cur = if (inst == 'L') map[cur]!!.first else map[cur]!!.second
            }
            return i.toLong()
        }


        val ans2 = map.keys.filter { it.last() == 'A' }.map { it.solveForNode() }.reduce(::lcm)

        println("It takes $ans steps")
        println("GCF is ${gcf(timeTakenToFinish)}")
        val lcm = findLCMOfListOfNumbers(timeTakenToFinish)
        println("ANs is $lcm")
        println("ANs2 is $ans2")
    }

    fun lcm(a: Long, b: Long) = a*b/gcd(a, b)

    fun gcd(a: Long, b: Long): Long {
        var a = a
        var b = b
        if (a < 0 || b < 0 || a + b <= 0) {
            throw IllegalArgumentException("GCD Error: a=$a, b=$b")
        }

        while (a > 0 && b > 0) {
            if (a >= b) {
                a %= b
            } else {
                b %= a
            }
        }

        return maxOf(a, b)
    }

    fun findLCMOfListOfNumbers(numbers: List<Long>): Long {
        var result = numbers[0]
        for (i in 1 until numbers.size) {
            result = findLCM(result, numbers[i])
        }
        return result
    }

    fun findLCM(a: Long, b: Long): Long {
        val larger = if (a > b) a else b
        val maxLcm = a * b
        var lcm = larger
        while (lcm <= maxLcm) {
            if (lcm % a == 0L && lcm % b == 0L) {
                return lcm
            }
            lcm += larger
        }
        return maxLcm
    }



    fun gcf(list: List<Long>) : Long {
        val max = list.max()
        for (divisor in max downTo 1) {
            var works = true
            for (i in 0 until list.size) {
                if (list[i] % divisor != 0L) works = false
            }
            if (works) return divisor
        }
        return 1
    }


}

