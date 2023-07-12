package nz.ac.auckland.se281.datastructures;

/**
 * A queue is a data structure that follows the FIFO (First In First Out) order.
 *
 * <p>First In First Out means that the first element to enter the queue is the also the first
 * element that is removed from the queue when using the remove method
 *
 * @param <T> the data type which will be stored in the queue
 */
public class Queue<T> {

  // Create variables that define what a queue is
  protected int count;
  protected DoublyLinkedList<T> queueData;

  /**
   * A constructor to initialise the queue with a count (which bit of data we wish to access) and a
   * doubly linked list to store the data.
   */
  public Queue() {
    this.count = 0;
    this.queueData = new DoublyLinkedList<T>();
  }

  /**
   * Add data to the start of the queue.
   *
   * @param data the data to be added to the start of the queue
   */
  public void enqueue(T data) {
    queueData.add(data);
    // Increment count so we can access the correct elements later
    count++;
  }

  /**
   * Remove data from the last element of the queue to follow the first in first out order.
   *
   * @return the removed element from the queue
   */
  public T dequeue() {
    // Initialise the output data
    T out = queueData.get(queueData.size() - count);
    // remove from the last index of the queue
    queueData.remove(queueData.size() - count);
    // Decement count as the queue will become smaller
    count--;
    return out;
  }

  /**
   * Check the element at the front of the queue without removing ot from the queue.
   *
   * @return the data found at the front of the queue
   */
  public T peek() {
    return queueData.get(queueData.size() - count);
  }

  /**
   * Check if the queue is empty.
   *
   * @return a boolean representing if the queue is empty or not
   */
  public boolean isEmpty() {
    return queueData.isEmpty();
  }
}
