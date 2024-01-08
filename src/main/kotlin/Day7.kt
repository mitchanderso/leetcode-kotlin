import utils.Utils.distinct
import utils.Utils.rl
import java.io.File

fun main() {
    val challenge = Day7()
    val challengeOther = Day7Other()
    val file = File(Day7::class.java.getResource("/day7/input.txt").file)
    challenge.hands(file)
    println("------------ THEIRS ------------")
    challengeOther.solvePart2(file)

}

val scoreList = listOf('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2', '-')

class Hand(val score: Int, val hand: Pair<String, Int>) : Comparable<Hand> {
    override operator fun compareTo(other: Hand): Int {
        if (this.score < other.score) return -1
        if (this.score > other.score) return 1
        else if (other.score == this.score) {
            val jRemoved = hand.first.replace('J', '-')
            val jRemovedOther = other.hand.first.replace('J', '-')
            for (i in 0 until hand.first.length) {
                var thisChar = jRemoved[i]
                var otherChar = jRemovedOther[i]
                val thisScore = scoreList.indexOf(thisChar)
                val otherScore = scoreList.indexOf(otherChar)
                println("idx == $i")
                if (thisScore < otherScore) {
                    println("This is less")
                    return 1
                }
                if (thisScore > otherScore) {
                    println("This is more ")
                    return -1
                }

            }
            return 0
        } else {
            return 0
        }

    }
}

class Day7Other {


    private fun valueOf(hand: String): Int {
        val uniques = hand.replace("23456789TQKA", "").distinct().count()
        val counts = hand.groupingBy { it }.eachCount()
        val score = when {
            counts.any { it.value == 5 } -> 6
            counts.any { it.value == 4 } -> 5
            uniques == 2 && counts.any { it.value == 3 } -> 4
            counts.any { it.value == 3 } -> 3
            uniques == 3 && counts.any { it.value == 2 } -> 2
            uniques == 4 -> 1
            else -> 0
        }
        return score
    }

    private fun compareHands(hand1: String, hand2: String, order: String) =
        hand1.zip(hand2).find { it.first != it.second }
            ?.let { if (order.indexOf(it.first) < order.indexOf(it.second)) -1 else 1 } ?: 0

    fun solvePart2(input: File): Any {
        val counts = mutableListOf<Int>(0, 0, 0, 0, 0, 0, 0)

        val ans =  input.rl().map { it.split(" ") }.map {
            val sc = valueOf(it[0].map { if (it == 'J') "23456789TQKA" else it.toString() }.joinToString(""))
            counts[sc]++
            Triple(
                it[0],
                it[1],
                sc
            )
        }.sortedWith { left, right ->
            if (left.third != right.third) left.third.compareTo(right.third)
            else compareHands(left.first, right.first, "J23456789TQKA")
        }.map { it.first to it.second.toInt() }

        println("5KIND ${counts[6]}")
        println("4KIND ${counts[5]}")
        println("FULL_HOUSE ${counts[4]}")
        println("3KIND ${counts[3]}")
        println("TWO_PAIR ${counts[2]}")
        println("ONE_PAIR ${counts[1]}")
        println("HIGH_CARD ${counts[0]}")
        println(ans.map { it.first })
        println(ans.mapIndexed { i, it -> (i + 1) * it.second }.sum())
        return ans.mapIndexed { i, it -> (i + 1) * it.second }.sum()
    }
}

class Day7 {
    fun hands(file: File) {
        val hands = mutableListOf<Pair<String, Int>>()
        file.forEachLine { line ->
            val hand = line.subSequence(0, 5)
            val score = line.substring(6)
            hands.add(hand.toString() to score.toInt())
        }

        val counts = mutableListOf<Int>(0, 0, 0, 0, 0, 0, 0)
        val ansHands = mutableListOf<Hand>()
        hands.forEach { hand ->
            val charCounts = hand.first.filter { it != 'J' }.groupingBy { it }.eachCount()
            val max = if (charCounts.isNotEmpty()) charCounts.maxBy { it.value }.key else "J"
            val replacedHand = hand.first.replace("J", max.toString())
            val score = when {
                replacedHand.isFiveOfAKind() -> {
                    counts[0]++
                    6
                }
                replacedHand.isFourOfAKind() -> {
                    counts[1]++
                    5
                }
                replacedHand.isFullHouse() -> {
                    counts[2]++
                    4
                }
                replacedHand.isThreeOfAKind() -> {
                    counts[3]++
                    3
                }
                replacedHand.isTwoPair() -> {
                    counts[4]++
                    2
                }
                replacedHand.isTwoOfAKind() -> {
                    counts[5]++
                    1
                }

                else -> {
                    counts[6]++
                    0
                }
            }
            ansHands.add(Hand(score, hand))

        }

        println("5KIND ${counts[0]}")
        println("4KIND ${counts[1]}")
        println("FULL_HOUSE ${counts[2]}")
        println("3KIND ${counts[3]}")
        println("TWO_PAIR ${counts[4]}")
        println("ONE_PAIR ${counts[5]}")
        println("HIGH_CARD ${counts[6]}")

        var ans = 0L

        ansHands.sorted().forEachIndexed { index, hand ->
            var idx = index + 1
            ans += (hand.hand.second * idx)
        }
        println(ansHands.sorted().map { it.hand.first to it.score})
        println(ans)

    }
}


fun String.isFiveOfAKind(): Boolean {
    val mapOfCounts = this.associateWith { cha -> this.count{ it == cha}  }
    return mapOfCounts.values.contains(5)
}

fun String.isFourOfAKind(): Boolean {
    val mapOfCounts = this.associateWith { cha -> this.count{ it == cha}  }
    return mapOfCounts.values.contains(4)
}

fun String.isFullHouse(): Boolean {
    return isThreeOfAKind() && isTwoOfAKind()
}

fun String.isThreeOfAKind(): Boolean {
    val mapOfCounts = this.associateWith { cha -> this.count{ it == cha}  }
    return mapOfCounts.values.contains(3)
}

fun String.isTwoPair(): Boolean {
    val mapOfCounts = this.associateWith { cha -> this.count{ it == cha}  }
    return mapOfCounts.values.count { it == 2 } == 2
}

fun String.isTwoOfAKind(): Boolean {
    val mapOfCounts = this.associateWith { cha -> this.count{ it == cha}  }
    return mapOfCounts.values.contains(2)
}

