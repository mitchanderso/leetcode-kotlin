package LC2024

class `796` {
    fun rotateString(s: String, goal: String): Boolean {
        if (s.length != goal.length) return false
        if (s == goal) return true
        val maxRotations = s.length

        var rotated = s.toMutableList()
        val goalList = goal.toMutableList()

        for (i in 0 until maxRotations) {
            rotated.add(rotated[0])
            rotated.removeAt(0)
            if (rotated == goalList) return true

        }

        return false
    }
}

fun main() {
    val soln = `796`()
    println(soln.rotateString("abcde", "cdeab"))
    //println(soln.rotateString("abcde", "abced"))

}