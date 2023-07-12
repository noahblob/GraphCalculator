package nz.ac.auckland.se281.datastructures;

/**
 * A doubly linked list is a data structure that consists of nodes that connect to each other via
 * pointers in both directions.
 *
 * <p>This DLL has been implemented to only work for the Queue data structure i.e. it can only
 * remove/add to the start or the end of the list
 *
 * @param <T> the type of data to be stored in the linked list
 */
public class DoublyLinkedList<T> {

  // Create variables that define what a doubly linked list is
  private Node<T> head;
  private Node<T> tail;
  private int size;

  /** Initialise the doubly linked list with a null head and tail. */
  public DoublyLinkedList() {
    this.head = null;
    this.tail = null;
  }

  /**
   * Add a new node to either the start or the end of the list.
   *
   * @param data the value that is to be stored in the new node added
   */
  public void add(T data) {
    // Initialise a new node containing the data we want to add
    Node<T> newNode = new Node<T>(data);
    // If there does not currently exist a head or tail, both the head and tail will become the new
    // node
    if (head == null || tail == null) {
      head = newNode;
      tail = newNode;
      head.setPrev(null);
      tail.setNext(null);
    } else {
      // Otherwise, add the new node to the end of the list
      tail.setNext(newNode);
      newNode.setPrev(tail);
      tail = newNode;
      tail.setNext(null);
    }
    // Increment size
    size++;
  }

  /**
   * Access the data at a specific index in the doubly linked list.
   *
   * @param index the position at which to access the data
   * @return the data at specified index
   */
  public T get(int index) {
    // If we want to access the first element, return the head
    if (index == 0) {
      return head.getData();
    } else {
      // The only other element we are accessing in a queue is the last element, so return the tail
      return tail.getData();
    }
  }

  /**
   * Removes data from the list at either the start or the end.
   *
   * <p>Since we are using the list to operate on a queue, only the first or last node will need to
   * be removed
   *
   * @param index specific index from which to remove
   */
  public void remove(int index) {
    // Initialise a new node to replace the head with
    Node<T> node = head.getNext();
    // If we want to remove first element, simply set head to the new node
    if (index == 0) {
      head = node;
    } else {
      // If we want to remove last element, set the tail to the node before it
      Node<T> newTail = tail.getPrev();
      tail = newTail;
    }
    // Decrement size
    size--;
  }

  /**
   * Access the size of the list.
   *
   * @return size of the list
   */
  public int size() {
    return size;
  }

  /**
   * Check if the list is empty.
   *
   * @return a boolean indicating if the list is empty or not
   */
  public boolean isEmpty() {
    // If the head or tail is null, our list is empty
    return (head == null || tail == null);
  }
}
