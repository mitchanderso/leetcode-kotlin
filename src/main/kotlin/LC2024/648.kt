package LC2024


class `648` {
    fun replaceWords(dictionary: List<String>, sentence: String): String {
        val sentenceSplit = sentence.split(" ").toMutableList()
        for (s in 0 until sentenceSplit.size) {
            val word = sentenceSplit[s]
            var mutableDictionary = dictionary.map { it to 0 }.toMutableList()
            var found = false
            for (i in 0 until word.length) {
                if (found) break
                var allBad = true
                for (j in 0 until mutableDictionary.size) {
                    if (i < mutableDictionary[j].first.length && mutableDictionary[j].first[i] == word[i] && mutableDictionary[j].second == i) {
                        mutableDictionary[j] = mutableDictionary[j].first to mutableDictionary[j].second + 1
                        allBad = false
                    }
                    if (mutableDictionary[j].second == mutableDictionary[j].first.length) {
                        //println("Word is ${mutableDictionary[j].first}")
                        sentenceSplit[s] = mutableDictionary[j].first
                        found = true
                        break
                    }

                }
                if (allBad) break
            }
        }
        return sentenceSplit.joinToString(" ")
    }



}

fun main() {
    val soln = `648`()
    println(soln.replaceWords(
        listOf("catt", "cat", "rat", "bat"),
        "cattle"
    ))
}