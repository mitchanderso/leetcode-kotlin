package LC2024

class `1813` {
    fun areSentencesSimilar(sentence1: String, sentence2: String): Boolean {
        val longerSentence = if (sentence1.length >= sentence2.length) sentence1 else sentence2
        val shorterSentence = if (sentence1.length < sentence2.length) sentence1 else sentence2

        val shorterSplit = shorterSentence.split(" ")
        val longerSplit = longerSentence.split(" ")

        val deqLong = ArrayDeque<String>(longerSplit)
        val deqShort = ArrayDeque<String>(shorterSplit)

        while (deqLong.isNotEmpty() && deqShort.isNotEmpty() && deqLong.first() == deqShort.first()) {
            deqShort.removeFirstOrNull()
            deqLong.removeFirstOrNull()
        }

        while (deqLong.isNotEmpty() && deqShort.isNotEmpty() && deqLong.last() == deqShort.last()) {
            deqShort.removeLastOrNull()
            deqLong.removeLastOrNull()
        }

        return deqShort.isEmpty() || deqLong.isEmpty()
    }
}

fun main() {
    val soln = `1813`()
    // println(soln.areSentencesSimilar("my name is haley", "my haley"))
//    println(soln.areSentencesSimilar("of", "a lot of words"))
//    println(soln.areSentencesSimilar("eating right now", "eating"))
    println(soln.areSentencesSimilar("qbaVXO Msgr aEWD v ekcb", "Msgr aEWD ekcb"))
}