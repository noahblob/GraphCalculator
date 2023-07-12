package nz.ac.auckland.se281.datastructures;

/**
 * A stack is a data structure that follows the FILO (First In Last Out) order.
 *
 * <p>First in last out means that the first data element to be added to the stack is the last to be
 * removed from the stack when using remove method
 *
 * @param <T> the data type stored in the stack
 */
public class Stack<T> {

  // Create variables that define what a stack is
  protected Node<T> head;
  protected Node<T> tail;
  protected int size;

  /** A constructor to initialise the head and tail data as null in the stack. */
  public Stack() {
    head = null;
    tail = null;
  }

  /**
   * Add (push) data to the top of the stack.
   *
   * @param val the data to be added
   */
  public void push(T val) {
    // Initialsie a new node containing the desired data
    Node<T> newNode = new Node<T>(val);

    // If the head is null, both the head and the tail will become the new node
    if (head == null) {
      head = newNode;
      tail = newNode;
    } else {
      // Otherwise, insert the new node at the front of the stack
      newNode.setNext(head);
      head = newNode;
    }
    // Increment size
    size++;
  }

  /**
   * Remove (pop) the first/top data on the stack.
   *
   * @return the popped data from the stack
   */
  public T pop() {
    // Initialise a variable to set the new head of the stack
    Node<T> newHead = head.getNext();
    // Get the top data from the stack and set the new top of the stack to the data just underneath
    T out = head.getData();
    head = newHead;
    // Decrement size and return the popped data
    size--;
    return out;
  }

  /**
   * Gets the first element of the stack without removing it from the stack.
   *
   * @return the first element of the stack
   */
  public T peek() {
    return head.getData();
  }

  /**
   * Gets the size of the stack.
   *
   * @return the size of the stack
   */
  public int size() {
    return size;
  }

  /**
   * Checks if the stack is empty.
   *
   * @return a boolean indicating if the stack is empty or not
   */
  public boolean isEmpty() {
    return size == 0;
  }
}
