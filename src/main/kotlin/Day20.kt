
import java.io.File
import java.util.*

fun main() {
    val challenge = Day20()
    val file = File(Day20::class.java.getResource("/day20/input.txt").file)
    challenge.p1(file, 10000000000000L)


    val cheat = Cheat()
    val lines = mutableListOf<String>()
    file.forEachLine { line -> lines.add(line) }

        println(cheat.part1(lines,1000))

}

class Day20 {

    enum class NodeType {
        FLIPFLOP, CONJUNCTION, OUTPUT, BROADCASTER
    }

    enum class RecentPulse {
        LOW, HIGH, CALC
    }
    data class PowerNode(val type: NodeType, var power: RecentPulse, val outPuts: MutableList<String>, val inputs: MutableList<String>, var mostRecentPulses: MutableMap<String, RecentPulse>)

    fun parseInput(file: File): Pair<Map<String, PowerNode>,List<String>> {
        val nodeMap = mutableMapOf<String, PowerNode>()
        val broadcasterOutputs = mutableListOf<String>()
        val lines = mutableListOf<String>()
        file.forEachLine { lines.add(it) }


        lines.forEach{ line ->
            if (line.first() == '%' || line.first() == '&') {
                val name = line.substring(1, line.indexOf(' '))
                val outputs = line.substring(line.indexOf('>') + 1).split(",").map { it.trim() }.toMutableList()
                nodeMap[name] = PowerNode( if (line.first() == '%' ) NodeType.FLIPFLOP else NodeType.CONJUNCTION, RecentPulse.LOW, outputs, mutableListOf(), mutableMapOf())
            } else {
                val outputs = line.substring(line.indexOf('>') + 1).split(",").map { it.trim() }
                outputs.forEach { broadcasterOutputs.add(it) }
            }
        }

        nodeMap["output"] = PowerNode(NodeType.OUTPUT, RecentPulse.LOW, mutableListOf(), mutableListOf(), mutableMapOf())
        nodeMap["rx"] = PowerNode(NodeType.OUTPUT, RecentPulse.LOW, mutableListOf(), mutableListOf(), mutableMapOf())
        nodeMap["broadcaster"] = PowerNode(NodeType.BROADCASTER, RecentPulse.LOW, mutableListOf(), mutableListOf(), mutableMapOf())


        nodeMap.forEach { (k, v) ->
            v.inputs.forEach { input ->
                v.mostRecentPulses[input] = RecentPulse.LOW
            }
            val outputs = v.outPuts
            outputs.forEach { output ->
                if (nodeMap.containsKey(output)) {
                    nodeMap[output]!!.inputs.add(k)
                }
            }


        }

        return nodeMap to broadcasterOutputs
    }


    val q = LinkedList<Triple<String, String, RecentPulse>>()


    fun p1(file: File, simulations: Long) {
        val (nodemap, broadcasterOutputs) = parseInput(file)
        var hp = 0L
        var lp = 0L
        for (i in 0 until simulations) {


            q.offer(Triple("button", "broadcaster", RecentPulse.LOW))

            if (simulations % 1000L == 0L) println(i + 1)

            while (q.isNotEmpty()) {
                val (from, to, pulse) = q.poll()
                //println("MINE: ${from} -$pulse-> ${to}")
                if (pulse == RecentPulse.LOW) lp++ else hp++
                if (pulse == RecentPulse.LOW && to == "rx") {
                    println("IT TOOK ${i + 1}")
                    return
                }
                val currentNode = nodemap[to]!!
                when (currentNode.type) {
                    NodeType.FLIPFLOP ->  {
                        if (pulse == RecentPulse.LOW) {
                            currentNode.power = if (currentNode.power == RecentPulse.HIGH) RecentPulse.LOW else RecentPulse.HIGH
                            currentNode.outPuts.forEach {
                                q.offer(Triple(to, it, currentNode.power))
                            }
                        }
                    }
                    NodeType.CONJUNCTION -> {
                        currentNode.mostRecentPulses[from] = pulse
                        val outputSignal = if (currentNode.mostRecentPulses.values.any { it == RecentPulse.LOW }) RecentPulse.HIGH else RecentPulse.LOW
                        currentNode.outPuts.forEach {
                            q.offer(Triple(to, it, outputSignal))
                        }
                    }
                    NodeType.BROADCASTER -> {
                        broadcasterOutputs.forEach {broadcasterOutput ->
                            q.offer(Triple("broadcaster", broadcasterOutput,  RecentPulse.LOW))

                        }
                    }
                    else -> {}
                }

//                if (pulse == RecentPulse.HIGH) {
//                    receiveHighPulse(modified, nodemap, from)
//                } else if (pulse == RecentPulse.LOW){
//                    receiveLowPulse(modified, nodemap, from)
//                }


            }

            //println("After $count simulation there are $hp high pulses and $lp low pulses")
            //println()
        }
        var ans = lp * hp
        println("MINE low = $lp high = $hp")
        println("MINE ans = $ans")
    }

//    fun receiveHighPulse(nodeName: String, nodeMap: Map<String, PowerNode>, from: String) {
//
//        hp++
//        if (nodeMap.containsKey(nodeName)) {
//            val node = nodeMap[nodeName]!!
//            if (node.type == NodeType.CONJUNCTION) {
//                node.mostRecentPulses[from] = RecentPulse.HIGH
//                val allRecentAreHigh = node.mostRecentPulses.values.all { it == RecentPulse.HIGH }
//                if (allRecentAreHigh)  {
//                    node.outPuts.forEach { q.offer((it to nodeName) to RecentPulse.LOW) }
//                } else {
//                    node.outPuts.forEach { q.offer((it to nodeName) to RecentPulse.HIGH) }
//                }
//            }
//        }
//
//
//    }
//
//    fun receiveLowPulse(nodeName: String, nodeMap: Map<String, PowerNode>, from: String) {
//        lp++
//        if (nodeMap.containsKey(nodeName)) {
//            val node = nodeMap[nodeName]!!
//            if (node.type == NodeType.FLIPFLOP) {
//                node.power = if (node.power == RecentPulse.HIGH) RecentPulse.LOW else RecentPulse.HIGH
//                node.outPuts.forEach { q.offer((it to nodeName) to node.power) }
//            } else if (node.type == NodeType.CONJUNCTION) {
//                node.mostRecentPulses[from] = RecentPulse.LOW
//                val allRecentAreHigh = node.mostRecentPulses.values.all { it == RecentPulse.HIGH }
//                if (allRecentAreHigh)  {
//                    node.outPuts.forEach { q.offer((it to nodeName) to RecentPulse.LOW) }
//                } else {
//                    node.outPuts.forEach { q.offer((it to nodeName) to RecentPulse.HIGH) }
//                }
//
//            }
//        }
//    }
}


data class Module(val label: String, val type: Char, var state: String, var children: List<String>, val parents: MutableList<Pair<String, String>>)
class Cheat() {
    fun parseModule(input: String): Module {
        val sides = input.split(" -> ")
        val type = sides[0][0]
        val label = if (type == 'b') sides[0] else sides[0].drop(1)
        val children = sides[1].split(", ")
        return Module(label, type, "low", children, mutableListOf())
    }

    fun connectChildren(modules: List<Module>) {
        for (module in modules) {
            for (child in module.children) {
                val node = modules.find { it.label == child }
                node?.parents?.add(Pair(module.label, "low"))
            }
        }
    }

    fun part1(input: List<String>, simulation: Int): Long {
        val modules = input.map { parseModule(it) }
            .plus(Module("output", 'o', "low", emptyList(), mutableListOf()))
            .plus(Module("rx", 'o', "low", emptyList(), mutableListOf()))
        connectChildren(modules)

        var lows = 0L
        var highs = 0L
        for (i in 0 until simulation) {
            val active = mutableListOf(Triple("button", "broadcaster", "low"))
            while (active.isNotEmpty()) {
                val currentSignal = active.removeFirst()
                if (currentSignal.third == "low") lows++ else highs++
                val currentModule = modules.find { it.label == currentSignal.second }!!
                //println("THEIRS: ${currentSignal.first} -${currentSignal.third}-> ${currentSignal.second}")

                when(currentModule.type) {
                    '%' -> {
                        if (currentSignal.third == "low") {
                            currentModule.state = if (currentModule.state == "low") "high" else "low"
                            for (child in currentModule.children) {
                                active.add(Triple(currentModule.label, child, currentModule.state))
                            }
                        }
                    }
                    '&' -> {
                        val parent = currentModule.parents.find { it.first == currentSignal.first }!!
                        currentModule.parents.remove(parent)
                        currentModule.parents.add(Pair(parent.first, currentSignal.third))
                        val signalOut = if (currentModule.parents.any { it.second == "low" }) "high" else "low"
                        for (child in currentModule.children) {
                            active.add(Triple(currentModule.label, child, signalOut))
                        }
                    }
                    'b' -> {
                        for (child in currentModule.children) {
                            active.add(Triple(currentModule.label, child, currentSignal.third))
                        }
                    }
                }
            }
            //println("After ${i+1} there are $highs highs and $lows lows")
        }

        println("Theirs lows = $lows highs = $highs")
        return lows * highs
    }



}