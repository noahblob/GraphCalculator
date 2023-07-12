package nz.ac.auckland.se281.datastructures;

/**
 * A singly linked list is a data structure that consists of nodes that each connect to the next
 * node in the list via pointers in one direction.
 *
 * @param <T> the type of data to be stored in the singly linked list
 */
public class SinglyLinkedList<T> {

  // Create variables that define what a singly linekd list is
  private Node<T> head;
  private int size;

  /** A constructore to initialise the head of the linked list. */
  public SinglyLinkedList() {
    this.head = null;
  }

  /**
   * Adds data to the end of the list.
   *
   * @param data the data to be added
   */
  public void add(T data) {
    // Initialise the new node to add to the list
    Node<T> newNode = new Node<T>(data);

    // If the head does not exist already, set it to the new node
    if (head == null) {
      head = newNode;
      size++;
      return;
    }

    // If the head does exist, iterate through the linked list until reaching the last index, then
    // insert the new node
    Node<T> current = head;
    while (current.getNext() != null) {
      current = current.getNext();
    }
    // After the whole list has been iterated, set the last element to the new node
    current.setNext(newNode);
    // Increment size
    size++;
  }

  /**
   * Access an element of the list at a specified index.
   *
   * @param index the index of the list where which we want to access data
   * @return the data at the specified index
   */
  public T get(int index) {
    // If the index is 0, simply return the data in the head
    if (index == 0) {
      return head.getData();
    } else {
      // Iterate over the list until we have reached the desired index of data, then return that
      // data
      Node<T> current = head;
      int count = 0;
      while (count < index) {
        current = current.getNext();
        count++;
      }
      return current.getData();
    }
  }

  /**
   * Remove an element from the list at a specific index.
   *
   * @param index the index at which we want to remove data
   */
  public void remove(int index) {
    // If the index is 0, set the head to the concurrent node
    Node<T> node = head.getNext();
    if (index == 0) {
      head = node;
    } else {
      // Otherwise, iterate to the specific index we want to remove
      Node<T> current = head;
      int count = 0;
      while (count < index) {
        Node<T> newNode = current;
        current = current.getNext();
        current.setPrev(newNode);
        count++;
      }
      // Remove the data at specific index
      Node<T> prev = current.getPrev();
      prev.setNext(current.getNext());
    }
    // Decrement size
    size--;
  }

  /**
   * Access the size of the list.
   *
   * @return the size of the list
   */
  public int size() {
    return size;
  }

  /**
   * Check if the list is empty.
   *
   * @return a boolean representing if the list is empty or not
   */
  public boolean isEmpty() {
    return (head == null);
  }

  /**
   * Check if the list contains a node with specific data.
   *
   * @param data the data which we wish to check the list contains
   * @return a boolean representing if the list contais the specific data
   */
  public boolean contains(T data) {
    // Initialise the head to later iterate over the list
    Node<T> node = head;
    // If the head is null, our list has no data therefore it does not contain specific data
    if (head == null) {
      return false;
    }
    // If the first element is the data we wish to find, return true
    if (head.getData().equals(data)) {
      return true;
    } else {
      // Iterate over the list and return true if we find the data
      while (node != null) {
        if (node.getData() == data) {
          return true;
        }
        node = node.getNext();
      }
    }
    // If the data was not found, return false
    return false;
  }

  /**
   * Sort the list in ascending order to perform Depth First Search and Breadth First Search in
   * numeric order.
   */
  public void sortAscending() {
    // Initialise nodes used to sort list
    Node<T> current = head;
    // Create a temporary variable
    T temp;

    // If head is null, our list does not need to be sorted so we can simply return
    if (head == null) {
      return;
    } else {
      // Iterate over the entire list
      while (current != null) {
        Node<T> index = current.getNext();
        while (index != null) {
          // If the value of current node is greater than index node, swap these nodes
          if (current.getData().hashCode() > index.getData().hashCode()) {
            temp = current.getData();
            current.setData(index.getData());
            index.setData(temp);
          }
          index = index.getNext();
        }
        current = current.getNext();
      }
    }
  }
}
