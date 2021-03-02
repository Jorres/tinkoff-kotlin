/**
 * Generalized node of a linked list.
 * @param elem - value that a node holds.
 * @param next - reference to the next node.
 */
data class Node<T>(val elem: T, var next: Node<T>? = null)