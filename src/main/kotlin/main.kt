import kotlin.random.Random

/**
 *  @return a list of random integers of random length.
 */
fun generateTestData(): List<Int> {
    val size = Random.nextInt(10, 100)
    return List(size) { Random.nextInt(0, 100) }
}

/**
 *  Helper function to check [MyQueue] correctness.
 */
fun testQueue() {
    val q = MyQueue<Int>()
    val data = generateTestData()
    data.forEach(q::enqueue)
    data.forEach { elem -> check(q.dequeue() == elem) }

    try {
        q.dequeue()
    } catch (e: NoSuchElementException) {
        println("MyQueue tested, no errors found")
        return
    }
    throw Exception("Dequeue from empty queue didn't throw")
}

/**
 *  Helper function to check [MyStack] correctness.
 */
fun testStack() {
    val s = MyStack<Int>()

    val data = generateTestData()
    data.forEach(s::push)

    val revData = data.reversed()
    revData.forEach { elem -> check(s.pop() == elem) }

    try {
        s.top()
    } catch (e: NoSuchElementException) {
        println("MyStack tested, no errors found")
        return
    }

    throw Exception("Pop from empty stack didn't throw")
}

/**
 *  Helper function to check varargs initializer functions [myQueueOf] and [myStackOf].
 */
fun testInitializers() {
    val qData = listOf(1, 2, 3, 4, 5)
    val q = myQueueOf(1, 2, 3, 4, 5)
    qData.forEach { elem -> check(elem == q.dequeue()) }

    val sData = listOf(5, 4, 3, 2, 1)
    val s = myStackOf(1, 2, 3, 4, 5)
    sData.forEach { elem -> check(elem == s.pop()) }
    print("Vararg initializers tested, no errors found")
}

/**
 *   Entrypoint.
 *   Launches a sequence of correctness tests.
 */
fun main() {
    testQueue()
    testStack()
    testInitializers()
}