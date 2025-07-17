> The binary tree has no standard implementation in Java so this is to showcase a simple implementation

# Binary Tree

This is a useless binary tree implementation for Java :3

A binary tree is a data structure where every node
has **at most 2 children** - often referred to as a left and a right node.

Depending on what it is supposed to represent it can have various operations

This tree works with **generic types <T>** which need to extend the **Comparable** class 
in order for it to be properly inserted and ordered

---

## What is it good for

Binary trees are good for a couple of this, namely:
- Fast search, insertion, and deletion (`O(log n)` in a balanced tree)
- Hierarchy representation
- Sorting and traveral

Tho this is only for understanding the implementation of the underlying idea 

## Functions

This binary tree implementation supports some basic functionality

It requires a Comparable T value which it uses to order the nodes

| Function             | Purpose                                                           |
|----------------------|-------------------------------------------------------------------|
| void insert(T value) | Insert a value into the binary tree                               |
| void inOrder()       | Prints out all node values in order                               |
| bool exists(T value) | Checks if the value exists                                        |
| void printTree()     | Prints a pretty tree of the values                                |
| void balance()       | Balances the tree to optimise the depth and thereby traversing it |
| T minValue()         | Gets the minimum value of the tree                                |
| T maxValue()         | Gets the maximum value of the tree                                |
| bool delete(T value) | Delete an element from the binary tree                            |
| int height()         | Returns the distance of the root to the lowest node               |
| int depth(T value)   | Returns the distance between the root and a specific value        |
| int countNodes()     | Returns the number of nodes int the tree                          |
| void forEach(Consumer\<? super T> action)     | Adds a for each function for simple iteration in order                       |
| Iterator\<T> iterator()     | Returns an iterator for the binary tree                          |


This is only a small subset of what functions a binary tree can have.

Other functions that a binary tree may provide are:
- Mirroring (switch all left and right nodes)
- Lowest common ancestor (find the closest parent node of 2 values)
- Serialization and Deserialization
- Visit all tree values level by level


## Nodes

Each node in the binary tree is represented by a `TreeNode<T>`.

A basic class with three fields: a value, a left child, and a right child.


- The value is the actual value being stored
- right/left are the children which create the tree like structure

```java
public class TreeNode<T> {
    T value;
    TreeNode<T> left;
    TreeNode<T> right;

    public TreeNode(T value) {
        this.value = value;
        left = null;
        right = null;
    }
}
```

## Design

- This tree uses recursion for basically all operations that require traversing the tree
- It is not thread save, as it does not utilize any locking of nodes when editing or reading
- The tree requires its values to extend the Comparable class to sort the values

## Binary Tree vs Array / List

| Feature / Operation          | Binary Tree                          | Array / List                                  |
| ---------------------------- | ------------------------------------ | --------------------------------------------- |
| Insertion (avg case)         | `O(log n)` (if balanced)             | `O(1)` at end, `O(n)` elsewhere               |
| Deletion                     | `O(log n)`                           | `O(n)` (due to shifting)                      |
| Search                       | `O(log n)` (BST)                     | `O(n)` linear, or `O(log n)` binary if sorted |
| Sorted Order                 | Maintained automatically             | Requires manual sorting                       |
| Memory layout                | Pointer-based (non-contiguous)       | Contiguous                                    |
| Random access (e.g. arr\[i]) | ❌ Not supported                      | ✅ `O(1)`                                      |
| Cache efficiency             | Lower (pointer chasing)              | High (contiguous in memory)                   |
| Useful for                   | Sorted sets, dynamic data structures | Static or index-heavy datasets                |
