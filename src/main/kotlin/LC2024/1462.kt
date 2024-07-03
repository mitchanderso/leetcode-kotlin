package LC2024

class `1462` {
    fun checkIfPrerequisite(numCourses: Int, prerequisites: Array<IntArray>, queries: Array<IntArray>): List<Boolean> {
        val indegree = mutableMapOf<Int, Int>()

        for (i in 0 until numCourses) indegree[i] = 0

        val prerequisitesMap = mutableMapOf<Int, MutableSet<Int>>()
        prerequisites.forEach { (pre, course) ->
            val prev = prerequisitesMap.getOrDefault(course, mutableSetOf())
            prev.add(pre)
            prerequisitesMap[course] = prev
            indegree[course] = indegree[course]!! + 1
        }

        var zeroIndegree = indegree.filter { (k, v) -> v == 0}
        while (zeroIndegree.isNotEmpty()) {
            zeroIndegree.forEach { (node, _) ->
                prerequisitesMap.forEach { (key, vvalue) ->
                    if (node in vvalue) {
                        prerequisitesMap[key] = (prerequisitesMap[key]!! + prerequisitesMap.getOrDefault(node, mutableSetOf())).toMutableSet()
                        indegree[key] = indegree[key]!! - 1
                    }
                }
                indegree.remove(node)
            }

            zeroIndegree = indegree.filter { (k, v) -> v == 0}

        }



        return queries.map { (isNeededFor, course) ->
            val prereq = prerequisitesMap.getOrDefault(course, mutableSetOf())
            prereq.contains(isNeededFor)
        }
    }
}

fun main() {
    val soln = `1462`()
    println(soln.checkIfPrerequisite(
        4,
        arrayOf(
            intArrayOf(0,1),
            intArrayOf(1,2),
            intArrayOf(2,3),
        ),
        arrayOf(
            intArrayOf(0, 3),
            intArrayOf(1, 2)
        )

    ))
}