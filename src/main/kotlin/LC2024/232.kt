package LC2024

import java.util.Stack


class MyQueue() {

    val stackOne = Stack<Int>()
    val stackTwo = Stack<Int>()
    val currentStack = stackOne
    val otherStack = stackTwo

    fun push(x: Int) {
        currentStack.push(x)
    }

    fun pop(): Int {
        while (currentStack.isNotEmpty()) otherStack.push(currentStack.pop())
        val res = otherStack.pop()
        while (otherStack.isNotEmpty()) currentStack.push(otherStack.pop())
        return res
    }

    fun peek(): Int {
        while (currentStack.isNotEmpty()) otherStack.push(currentStack.pop())
        val res = otherStack.peek()
        while (otherStack.isNotEmpty()) currentStack.push(otherStack.pop())
        return res
    }

    fun empty(): Boolean {
        return currentStack.isEmpty()
    }

}


fun main() {
    val soln = MyQueue()
    val myQueue = MyQueue();
    myQueue.push(1); // queue is: [1]
    myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
    myQueue.peek(); // return 1
    myQueue.pop(); // return 1, queue is [2]
    myQueue.empty(); // return false
}