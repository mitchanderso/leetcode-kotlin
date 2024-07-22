package LC2024

class `2418` {
    fun sortPeople(names: Array<String>, heights: IntArray): Array<String> {
        return names.zip(heights.asIterable()).sortedByDescending { it.second }.map { it.first }.toTypedArray()
    }
}

fun main() {

}