/**
 * Simple stack. Implements pushing, popping and top element lookup.
 */
class MyStack<T> {
    private var head: Node<T>? = null

    /**
     * Push element to the top of the stack.
     * @param elem - value to be pushed.
     */
    fun push(elem: T) {
        val new = Node(elem)
        if (head == null) {
            head = new
        } else {
            new.next = head
            head = new
        }
    }

    /**
     * Remove element from the top of the stack and return it.
     * @return value from the top of the stack.
     * @throws NoSuchElementException if stack is empty.
     */
    fun pop(): T {
        val ans = top()
        head = head?.next
        return ans
    }

    /**
     *  Lookup of the top of the stack.
     *  @return value from the top of the stack.
     *  @throws NoSuchElementException if stack is empty.
     */
    fun top(): T = head?.elem ?: throw NoSuchElementException("Stack is empty")
}

/**
 *  Helper function to initialize [MyStack] with a variable amount of arguments.
 *  @param data - list of arguments
 */
fun <T> myStackOf(vararg data: T): MyStack<T> {
    val s = MyStack<T>()
    data.forEach(s::push)
    return s
}