package LC2024

class `1366` {

    data class Team(val rankings: MutableList<Int>, val name: Char)
    fun rankTeams(votes: Array<String>): String {
        val allTeams = mutableListOf<Team>()
        val pos = mutableMapOf<Char, Int>()
        votes.first().forEachIndexed { index, c ->
            allTeams.add(Team(MutableList(votes.first().length) { 0 }, c))
            pos[c] = index
        }

        votes.forEach { voteString ->
            voteString.forEachIndexed { index, team ->
                allTeams[pos[team]!!].rankings[index]++
            }
        }

        var listComparator = compareByDescending<Team> {
           it.rankings.first()
        }
        for (i in 1 until votes.first().length) {
            listComparator = listComparator.thenByDescending { it.rankings[i] }
        }

        listComparator = listComparator.thenBy { it.name }

        allTeams.sortWith(listComparator)


        return allTeams.map { it.name }.joinToString("")


    }
}

fun main() {
    val soln = `1366`()
    println(soln.rankTeams(
        arrayOf(
            "BCA","CAB","CBA","ABC","ACB","BAC"
        )
    ))
}