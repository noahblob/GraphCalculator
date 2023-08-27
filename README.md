# GraphCalculator
This is the repository for a project I completed at university.

## Description
Graph Calculator is a command-line based tool built in Java for the purpose of calculating graph properties. <br>
It has the ability to calcualte whether a graph is reflexive, transitive, symmetrical and antisymmetrical <br>
It can perform depth first search and breadth first search iteratively or recursively on graphs in numeric order <br>
For the purposes of type safety, the graph nodes can store any type of data <br>
This project also contains implementations of commonly used data structures including: Singly Linked List, Doubly Linked List, Queue and Stack <br>
Implementing these from scratch has helped me gain a better understanding of data structures

## Example
**Example Input:** <br>
```
digraph testgraph {
 0->2
 0->1
 0->4
 0->3
 1->5
 1->6
 5->7
 5->8
 5->15
 7->15
 2->9
 4->10
 10->11
 11->12
 12->13
 4->13
 20->23
 20->22
 23->22
 22->21
 23->21
 20->21
 22->15
 15->23
 7->20
}
```
<br>
**Visual Representation:**<br>
![image](https://github.com/noahblob/GraphCalculator/assets/107817272/1be8041e-084a-4bce-93d6-65968a9825f5)
<br>
**Output of Breadth First Search** <br>
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24]
<br>
**Output of Depth First Search** <br>
[0, 1, 3, 7, 15, 19, 22, 24, 8, 4, 9, 16, 20, 23, 10, 2, 5, 11, 17, 21, 12, 6, 13, 18, 14]
