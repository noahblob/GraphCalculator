package nz.ac.auckland.se281.datastructures;

/**
 * An edge in a graph that connects two verticies.
 *
 * <p>You must NOT change the signature of the constructor of this class.
 *
 * @param <T> The type of each vertex.
 */
public class Edge<T> {

  // Create variables that define what an Edge is
  private T source;
  private T destination;

  /**
   * Initialise the edge with its relative source and destination.
   *
   * @param source the source data of the edge
   * @param destination the destination data of the edge
   */
  public Edge(T source, T destination) {
    this.source = source;
    this.destination = destination;
  }

  /**
   * Access the source of the edge.
   *
   * @return the source of the edge
   */
  public T getSource() {
    return source;
  }

  /**
   * Access the destination of the edge.
   *
   * @return the destination of the edge
   */
  public T getDestination() {
    return destination;
  }
}
