/**
 * Simple queue. Implements enqueuing and dequeuing.
 */
class MyQueue<T> {
    private var head: Node<T>? = null
    private var tail: Node<T>? = null

    /**
     * Enqueues element.
     * @param elem - value to be enqueued
     */
    fun enqueue(elem: T) {
        val new = Node(elem)
        if (head == null) {
            head = new
        } else {
            tail!!.next = new
        }

        tail = new
    }

    /**
     * Dequeues element.
     * @throws NoSuchElementException if queue is empty.
     * @return dequeued value.
     */
    fun dequeue(): T {
        val ans = head?.elem ?: throw NoSuchElementException("Queue is empty")
        val nextNode = head?.next
        if (nextNode == null) {
            head = null
            tail = null
        } else {
            head = nextNode
        }
        return ans
    }
}

/**
 *  Helper function to initialize [MyQueue] with a variable amount of arguments.
 *  @param data - list of arguments
 */
fun <T> myQueueOf(vararg data: T): MyQueue<T> {
    val q = MyQueue<T>()
    data.forEach(q::enqueue)
    return q
}