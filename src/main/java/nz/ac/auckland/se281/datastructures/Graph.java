package nz.ac.auckland.se281.datastructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * A graph that is composed of a set of verticies and edges.
 *
 * <p>You must NOT change the signature of the existing methods or constructor of this class.
 *
 * @param <T> The type of each vertex, that have a total ordering.
 */
public class Graph<T extends Comparable<T>> {

  // Create variables that define what a graph is
  private Set<T> vertices;
  private ArrayList<Edge<T>> edges;
  private HashMap<T, SinglyLinkedList<T>> adjacencyList;

  /**
   * Constructs a graph from a set of verticies and edges.
   *
   * <p>Initialises a hashmap of verticies and their adjacent edges
   *
   * @param verticies a set of all vertices of the graph
   * @param edges a set of all edges of the graph
   */
  public Graph(Set<T> verticies, Set<Edge<T>> edges) {
    // Initialise the graph
    this.vertices = verticies;
    this.edges = new ArrayList<>(edges);
    adjacencyList = new HashMap<T, SinglyLinkedList<T>>();
    // Add all vertices and their adjacent edges to the hashmap
    for (T vertex : vertices) {
      SinglyLinkedList<T> adjacentVertices = new SinglyLinkedList<>();
      for (Edge<T> edge : edges) {
        if (edge.getSource() == vertex) {
          adjacentVertices.add(edge.getDestination());
        }
      }
      // Ensure the list of adjacent vertices is sorted in ascending order
      adjacentVertices.sortAscending();
      adjacencyList.put(vertex, adjacentVertices);
    }
  }

  /**
   * Returns the set of root vertices in the graph.
   *
   * <p>A root vertex is a vertex with no incoming edges (or only a self loop), or a vertex that is
   * the smallest in its equivalence class
   *
   * @return a set of all root vertices in the graph
   */
  public Set<T> getRoots() {
    // Initialise a list of all roots to later be converted into a set to return in numeric order
    ArrayList<T> roots = new ArrayList<T>();
    // Iterate over each vertex in the graph and check if it has any incoming edges or self loops
    for (T vertex : vertices) {
      int inDegree = 0;
      for (Edge<T> edge : edges) {
        // Check if vertex has any incmoing edges
        if (edge.getDestination() == vertex) {
          inDegree++;
        }
      }

      // If vertex is the smallest in its equivalence class, add it to roots
      List<Integer> equivalenceClassNums = new ArrayList<>();
      // First convert the equivalence class to a list of integers to compare in numeric ordering
      // rather than lexicographic ordering
      for (T v : getEquivalenceClass(vertex)) {
        equivalenceClassNums.add(Integer.parseInt((String) v));
      }
      if (isEquivalence()
          && Integer.parseInt((String) vertex) == Collections.min(equivalenceClassNums)) {
        roots.add(vertex);
      }
      // If no incoming edges, add the edge to roots
      if (inDegree == 0) {
        roots.add(vertex);
      }
    }
    // sort roots in numeric order
    for (int i = 0; i < roots.size(); i++) {
      for (int j = i + 1; j < roots.size(); j++) {
        if (Integer.parseInt((String) roots.get(i)) > Integer.parseInt((String) roots.get(j))) {
          T temp = roots.get(i);
          roots.set(i, roots.get(j));
          roots.set(j, temp);
        }
      }
    }

    // Convert roots to a set to return
    LinkedHashSet<T> rootsSet = new LinkedHashSet<T>(roots);
    return rootsSet;
  }

  /**
   * Returns if all vertices in the graph are reflexive.
   *
   * <p>A vertex is reflexive if it has a self-loop
   *
   * @return a boolean representing if the graph is reflexive
   */
  public boolean isReflexive() {
    // Iterate over each vertex in the graph and check if it has a self loop
    for (T vertex : vertices) {
      if (!adjacencyList.get(vertex).contains(vertex)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Returns if all vertices in the graph are symmetric.
   *
   * <p>A vertex is symmetric if for every edge (u, v), there is an edge (v, u)
   *
   * @return a boolean representing if the graph is symmetric
   */
  public boolean isSymmetric() {
    // Iterate over each vertex and return false on the first occurrence of a non-symmetric edge
    for (T vertex1 : vertices) {
      for (T vertex2 : vertices) {
        if (adjacencyList.get(vertex1).contains(vertex2)
            && !adjacencyList.get(vertex2).contains(vertex1)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Returns if all vertices in the graph are transitive.
   *
   * <p>A vertex is transitive if for every edge (u, v) and (v, w), there is an edge (u, w)
   *
   * @return a boolean representing if the graph is transitive
   */
  public boolean isTransitive() {
    // Iterate over each vertex and return false on the first occurrence of a non-transitive edge
    for (T vertex : vertices) {
      for (Edge<T> edge1 : edges) {
        if (edge1.getSource() == vertex) {
          for (Edge<T> edge2 : edges) {
            // If first edges destination is the source of the last edge, but the initial vertex is
            // not adjacent with the transitive edge, return false
            if (edge2.getSource() == edge1.getDestination()
                && !adjacencyList.get(vertex).contains(edge2.getDestination())) {
              return false;
            }
          }
        }
      }
    }
    return true;
  }

  /**
   * Returns if all vertices in the graph are anti-symmetric.
   *
   * <p>A vertex is anti-symmetric if for every edge (u, v), there is an edge (v, u) such that u = v
   *
   * @return a boolean representing if the graph is anti-symmetric
   */
  public boolean isAntiSymmetric() {
    for (Edge<T> edge1 : edges) {
      // If an edge is symmetric, but the two vertices are not equal, then the edge is not
      // antisymmetric, and therefore the entire graph is not antisymmetric
      for (Edge<T> edge2 : edges) {
        if (edge1.getDestination() == edge2.getSource()
            && edge1.getSource() == edge2.getDestination()
            && edge1.getSource() != edge1.getDestination()) {
          return false;
        }
      }
    }

    // If all edges are antisymmetric, return true
    return true;
  }

  /**
   * Returns if the graph is an equivalence relation.
   *
   * <p>A graph is an equivalence relation if every vertex in the graph has the reflexive, symmetric
   * and transitive properties
   *
   * @return a boolean representing if the graph is an equivalence relation
   */
  public boolean isEquivalence() {
    // Return if the entire graph is reflexive, symmetric and transitive
    return isReflexive() && isSymmetric() && isTransitive();
  }

  /**
   * Returns the equivalence class of a vertex.
   *
   * <p>An equivalence class is every vertex that is symmetric, transitive and reflexive to the
   * input vertex
   *
   * @param vertex the vertex we will calculate the equivalence class of
   * @return the equivalence class of the vertex
   */
  public Set<T> getEquivalenceClass(T vertex) {
    // Initialise a hashset of the equivalence class to return from the function
    Set<T> equivalenceClass = new HashSet<T>();

    if (isEquivalence()) {
      // If the graph is an equivalence relation, then the equivalence class of a vertex is the set
      // of all vertices that are connected to it
      for (Edge<T> edge : edges) {
        if (edge.getSource().equals(vertex)) {
          equivalenceClass.add(edge.getDestination());
        }
      }
      equivalenceClass.add(vertex);
    }

    // In the case that the graph is not an equivalence relation, this will return an empty set
    return equivalenceClass;
  }

  /**
   * Performs an iterative BFS on the graph.
   *
   * <p>a BFS or breadth first search is a graph traversal algorithm that starts traversing the
   * graph from root vertex and explores all the neighbouring vertices. Then, it selects the nearest
   * vertex and explores all the unexplored vertices. The algorithm follows the same process for
   * each of the nearest vertices until all vertices have been visited.
   *
   * @return a list of all vertices in the graph in BFS order
   */
  public List<T> iterativeBreadthFirstSearch() {
    // Initialise a list of all visited vertices and a queue of all vertices to visit
    List<T> visited = new ArrayList<>();
    Queue<T> queue = new Queue<>();

    // Iterate over each root (in case the graph is disconnected)
    for (T vertex : getRoots()) {
      // Add the root to the queue and visited array
      queue.enqueue(vertex);
      visited.add(vertex);
      while (!queue.isEmpty()) {
        // While the queue is not empty, dequeue the first element and find all adjacent elements
        T current = queue.dequeue();
        SinglyLinkedList<T> adjacentVertices = adjacencyList.get(current);
        adjacentVertices.sortAscending();
        // Add each adjacent element to the queue and visited array if it has not already been
        // visited
        for (int i = 0; i < adjacentVertices.size(); i++) {
          if (!visited.contains(adjacentVertices.get(i))) {
            queue.enqueue(adjacentVertices.get(i));
            visited.add(adjacentVertices.get(i));
          }
        }
      }
    }
    return visited;
  }

  /**
   * Performs an iterative DFS on the graph.
   *
   * <p>a DFS or depth first search is a graph traversal algorithm that starts traversing the graph
   * from root vertex and explores as far as possible along each branch before backtracking.
   *
   * @return a list of all vertices in the graph in DFS order
   */
  public List<T> iterativeDepthFirstSearch() {
    // Initialise a result array to store output, visited array to store visited vertices and a
    // stack to perform DFS on
    List<T> result = new ArrayList<>();
    List<T> visited = new ArrayList<>();
    Stack<T> stack = new Stack<>();
    // Iterate over all the roots
    for (T vertex : getRoots()) {
      // push the first vertex to the stack, and visit it
      stack.push(vertex);
      visited.add(vertex);
      result.add(vertex);
      while (!stack.isEmpty()) {
        // While the stack is not empty, pop the first element, add it to our result, and find all
        // adjacent elements
        T s = stack.pop();
        // If the adjacent element has not been visited, add it to the visited array and result
        if (!visited.contains(s)) {
          visited.add(s);
          result.add(s);
        }
        SinglyLinkedList<T> adjacentVertices = adjacencyList.get(s);
        for (int i = adjacentVertices.size() - 1; i >= 0; i--) {
          if (!visited.contains(adjacentVertices.get(i))) {
            // If the adjacent element has not been visited, add it to the stack
            stack.push(adjacentVertices.get(i));
          }
        }
      }
    }

    return result;
  }

  /**
   * Calls the recursiveBFSUtil method to perform a recursive BFS on the graph.
   *
   * @return a list of all vertices in the graph in BFS order
   */
  public List<T> recursiveBreadthFirstSearch() {
    // Initialise a list of all visited vertices and a queue of all vertices to visit
    List<T> visited = new ArrayList<>();
    Queue<T> queue = new Queue<>();

    // Iterate over each root (in case the graph is disconnected)
    for (T vertex : getRoots()) {
      // Add the root to the queue and visited array, then perform a recursive BFS
      visited.add(vertex);
      queue.enqueue(vertex);
      recursiveBreadthFirstSearchUtil(visited, queue);
    }

    // return the result of the recursive BFS
    return visited;
  }

  /**
   * Performs a recursife BFS on the graph.
   *
   * @param visited anw arraylist of all the visited vertices
   * @param queue a queue of all the vertices to visit
   */
  public void recursiveBreadthFirstSearchUtil(List<T> visited, Queue<T> queue) {
    // If queue is empty, we have visited all vertices
    if (queue.isEmpty()) {
      return;
    }

    // Dequeue the first element and find all adjacent elements
    T current = queue.dequeue();
    SinglyLinkedList<T> adjacentVertices = adjacencyList.get(current);

    // Add each adjacent element to the queue and visited array if it has not already been visited
    for (int i = 0; i < adjacentVertices.size(); i++) {
      if (!visited.contains(adjacentVertices.get(i))) {
        visited.add(adjacentVertices.get(i));
        queue.enqueue(adjacentVertices.get(i));
      }
    }

    // Recursively call the function until all vertices have been visited
    recursiveBreadthFirstSearchUtil(visited, queue);
  }

  /**
   * Calls the recursiveDFSUtil method to perform a recursive DFS on the graph.
   *
   * @return a list of all vertices in the graph in DFS order
   */
  public List<T> recursiveDepthFirstSearch() {
    // Initialise a list of all visited vertices and a stack of all vertices to visit
    List<T> visited = new ArrayList<>();
    Stack<T> stack = new Stack<>();

    // Iterate over each root (in case the graph is disconnected)
    for (T vertex : getRoots()) {
      // Add the root to the stack and visited array, then perform a recursive DFS
      visited.add(vertex);
      stack.push(vertex);
      recursiveDepthFirstSearchUtil(visited, stack);
    }

    // return the result of the recursive DFS
    return visited;
  }

  /**
   * Performs a recursive DFS on the graph.
   *
   * @param visited an arraylist of all the visited vertices
   * @param stack a stack of all the vertices to visit
   */
  public void recursiveDepthFirstSearchUtil(List<T> visited, Stack<T> stack) {
    // If stack is empty, we have visited all vertices
    if (stack.isEmpty()) {
      return;
    }

    // Pop the first element and find all adjacent elements
    T current = stack.pop();
    SinglyLinkedList<T> adjacentVertices = adjacencyList.get(current);

    // Add each adjacent element to the stack and visited array if it has not already been visited
    for (int i = 0; i < adjacentVertices.size(); i++) {
      if (!visited.contains(adjacentVertices.get(i))) {
        visited.add(adjacentVertices.get(i));
        stack.push(adjacentVertices.get(i));
        // Recursively call the function until all vertices have been visited
        recursiveDepthFirstSearchUtil(visited, stack);
      }
    }
  }
}
