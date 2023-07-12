package nz.ac.auckland.se281.datastructures;

/**
 * A node is a data structure that stores data and a reference to its next and previous nodes.
 *
 * @param <T> The type of data that will be stored in the node
 */
public class Node<T> {

  // Create variables that define what a Node is
  private T data;
  private Node<T> next;
  private Node<T> prev;

  /** A default constructor in the case no data is supplied to the Node. */
  public Node() {}

  /**
   * Constructs a node from give data.
   *
   * @param data the data to be stored in the node
   */
  public Node(T data) {
    this.data = data;
  }

  /**
   * Access the data from the node.
   *
   * @return the data stored in the node
   */
  public T getData() {
    return data;
  }

  /**
   * Set the data in the node.
   *
   * @param data the new data to be stored in the node
   */
  public void setData(T data) {
    this.data = data;
  }

  /**
   * Access the next node connected to the current node.
   *
   * @return the next node
   */
  public Node<T> getNext() {
    return next;
  }

  /**
   * Set the next node which is connected to the current node.
   *
   * @param next the node that we will be replacing next node with
   */
  public void setNext(Node<T> next) {
    this.next = next;
  }

  /**
   * Access the previous node connected to the current node.
   *
   * @return the previous node
   */
  public Node<T> getPrev() {
    return prev;
  }

  /**
   * Set the previous node which is connected to the current node.
   *
   * @param prev the node that we will be replacing the previous node with
   */
  public void setPrev(Node<T> prev) {
    this.prev = prev;
  }

  /**
   * Converts the data in the node to a string form.
   *
   * @return the data in the node as a string
   */
  @Override
  public String toString() {
    return data.toString();
  }
}
