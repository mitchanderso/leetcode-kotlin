
import java.io.File
import java.util.*

fun main() {
    val challenge = Day16()
    val file = File(Day16::class.java.getResource("/day16/input.txt").file)
    challenge.p1(file)

}



class Day16 {

    enum class Heading(val disp: Pair<Int, Int>) {
        NORTH(0 to -1),
        SOUTH(0 to 1),
        EAST(1 to 0),
        WEST(-1 to 0)
    }

    data class Beam(val x: Int, val y: Int, var heading: Heading)

    fun visitedThisHeading(visited: MutableList<MutableList<Pair<Boolean, MutableList<Heading>>>>, x: Int, y: Int, heading: Heading) : Boolean {
        return visited[y][x].first == true && visited[y][x].second.contains(heading)
    }

    fun p1(file: File) {
        val map = mutableListOf<String>()
        val goneTo = mutableSetOf<Pair<Int, Int>>()
        val visited = mutableSetOf<Beam>()
        file.forEachLine { line ->
            map.add(line)
        }

        val height = map.size
        val width = map[0].length

        val beamQ = LinkedList<Beam>()
        beamQ.add(Beam(-1, 0, Heading.EAST))

        while (beamQ.isNotEmpty()) {
            val currentBeam = beamQ.poll()
            println("Visiting ${currentBeam.x} , ${currentBeam.y}")

            goneTo.add(currentBeam.x to currentBeam.y)

            val newX = currentBeam.x + currentBeam.heading.disp.first
            val newY = currentBeam.y + currentBeam.heading.disp.second

            if (newX >= 0 && newY >= 0 && newX < width && newY < height) {
                if (map[newY][newX] == '.') {
                    val newBeam = Beam(newX, newY, currentBeam.heading)
                    if (newBeam !in visited) {
                        visited.add(newBeam)
                        beamQ.add(newBeam)
                    }
                } else if (map[newY][newX] == '/') {
                    when (currentBeam.heading) {
                        Heading.EAST -> {
                            val newBeam = Beam(newX, newY, Heading.NORTH)
                            if (newBeam !in visited) {
                                visited.add(newBeam)
                                beamQ.add(newBeam)
                            }
                        }
                        Heading.WEST -> {
                            val newBeam = Beam(newX, newY, Heading.SOUTH)
                            if (newBeam !in visited) {
                                visited.add(newBeam)
                                beamQ.add(newBeam)
                            }
                        }
                        Heading.SOUTH -> {
                            val newBeam = Beam(newX, newY, Heading.WEST)
                            if (newBeam !in visited) {
                                visited.add(newBeam)
                                beamQ.add(newBeam)
                            }
                        }
                        Heading.NORTH -> {
                            val newBeam = Beam(newX, newY, Heading.EAST)
                            if (newBeam !in visited) {
                                visited.add(newBeam)
                                beamQ.add(newBeam)
                            }
                        }
                    }
                } else if (map[newY][newX] == '\\') {
                    when (currentBeam.heading) {
                        Heading.EAST -> {
                            val newBeam = Beam(newX, newY, Heading.SOUTH)
                            if (newBeam !in visited) {
                                visited.add(newBeam)
                                beamQ.add(newBeam)
                            }
                        }
                        Heading.WEST -> {
                            val newBeam = Beam(newX, newY, Heading.NORTH)
                            if (newBeam !in visited) {
                                visited.add(newBeam)
                                beamQ.add(newBeam)
                            }
                        }
                        Heading.SOUTH -> {
                            val newBeam = Beam(newX, newY, Heading.EAST)
                            if (newBeam !in visited) {
                                visited.add(newBeam)
                                beamQ.add(newBeam)
                            }
                        }
                        Heading.NORTH -> {
                            val newBeam = Beam(newX, newY, Heading.WEST)
                            if (newBeam !in visited) {
                                visited.add(newBeam)
                                beamQ.add(newBeam)
                            }
                        }
                    }
                } else if (map[newY][newX] == '-') {
                    when (currentBeam.heading) {
                        Heading.EAST, Heading.WEST -> {
                            val newBeam = Beam(newX, newY, currentBeam.heading)
                            if (newBeam !in visited) {
                                visited.add(newBeam)
                                beamQ.add(newBeam)
                            }
                        }
                        Heading.SOUTH,  Heading.NORTH-> {
                            var newBeam = Beam(newX, newY, Heading.EAST)
                            if (newBeam !in visited) {
                                visited.add(newBeam)
                                beamQ.add(newBeam)
                            }
                            newBeam = Beam(newX, newY, Heading.WEST)
                            if (newBeam !in visited) {
                                visited.add(newBeam)
                                beamQ.add(newBeam)
                            }
                        }

                    }
                } else if (map[newY][newX] == '|') {
                    when (currentBeam.heading) {
                        Heading.SOUTH,  Heading.NORTH -> {
                            val newBeam = Beam(newX, newY, currentBeam.heading)
                            if (newBeam !in visited) {
                                visited.add(newBeam)
                                beamQ.add(newBeam)
                            }
                        }
                        Heading.EAST, Heading.WEST-> {
                            var newBeam = Beam(newX, newY, Heading.NORTH)
                            if (newBeam !in visited) {
                                visited.add(newBeam)
                                beamQ.add(newBeam)
                            }
                            newBeam = Beam(newX, newY, Heading.SOUTH)
                            if (newBeam !in visited) {
                                visited.add(newBeam)
                                beamQ.add(newBeam)
                            }
                        }

                    }
                }
            } else {
               // println("Dead beam")
            }
        }




    val seenAsPoints = visited.map { it.x to it.y }.toSet()
        println("ans is ${seenAsPoints.size}")
}



}
