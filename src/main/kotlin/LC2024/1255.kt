package LC2024

import java.lang.Math.max


class `1255` {

    val ans = mutableListOf<MutableList<String>>()
    fun maxScoreWords(words: Array<String>, letters: CharArray, score: IntArray): Int {
        subsets(words, letters.toMutableList(), 0, mutableListOf())
        val scores = mutableMapOf<String, Int>()
        words.forEach { word ->
            var current = 0
            word.forEach { letter ->
                current += score[letter - 'a']
            }
            scores[word] = current
        }

        var max = 0
        ans.forEach { set ->
            var runningScore = 0
            set.forEach { it -> runningScore += scores[it]!! }
            max = max(runningScore, max)
        }
        println(ans)
        return max
    }

    fun subsets(words: Array<String>, letters: MutableList<Char>, pos: Int, workingList: MutableList<String>) {
        if (pos >= words.size) {
            ans.add(workingList.toMutableList())
            return
        }

        // Take
        if (letters.canMakeWord(words[pos])) {
            workingList.add(words[pos])
            val tempLetters = letters.toMutableList()
            words[pos].forEach { letter ->
                tempLetters.removeAt(tempLetters.indexOf(letter))
            }
            subsets(words, tempLetters, pos + 1, workingList)
            workingList.removeAt(workingList.size - 1)
        }

        subsets(words, letters, pos + 1, workingList)
        // Dont Take
    }

    fun MutableList<Char>.canMakeWord(s: String): Boolean {
        val used = IntArray(this.size) { 0 }
        var count = s.length
        s.forEach { cha ->
            for (i in 0 until this.size) {
                if (this[i] == cha && used[i] == 0) {
                    count--
                    used[i] = 1
                    break
                }
            }

        }
        return count == 0
    }





}




fun main() {
    val soln = `1255`()
//    println( mutableListOf('a', 'b', 'c', 'c').canMakeWord("abccc"))
    println(soln.maxScoreWords(
        arrayOf("dog","cat","dad","good"),
        charArrayOf('a','a','c','d','d','d','g','o','o'),
        intArrayOf(1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0)
    ))



}