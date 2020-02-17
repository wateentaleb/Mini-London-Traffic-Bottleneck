# Mini London's Traffic Bottleneck

## Overview
Being a city planner that is in charge of vital construction projects such as **traffic intersection maintenance**. The Mayor of the City of Mini-London suspects the most important intersection to maintain is the one for which the most number of vehicles cross, and would like you to determine this intersection.

For simplicity, we will call this the **bottleneck intersection**. All the intersections of Mini-London are numbered 0 to 63, an undirected graph representing the road structure of Mini-London is given below for your reference. There are many factors that go into determining how busy an intersection is and what constitutes a traffic bottleneck, we make a simplifying assumption by considering only the total frequency of vehicles that travel through an intersection. **For this project, a Java program is written that determines a bottleneck intersection for a simulated traffic flow of vehicles.**

<img width="844" alt="map " src="https://user-images.githubusercontent.com/16707828/74684748-a8fa2280-519a-11ea-8a1b-d2f44c00e1f1.png">

The traffic flow is simulated via a path-finding algorithm that performs a depth-first search in the graph. Upon finding a path, the program will update the data storing the number of crossings at an intersection, this data is stored in nodes of an AVL Tree. The following will be implemented:
+ an AVL Tree;
+ a path-finding algorithm (depth-first search).

Traffic was simulated by using The official website of the City of London Traffic Volume webpage, the link can be accessed here: https://www.london.ca/residents/Roads-Transportation/traffic-management/Pages/Traffic-Volumes.aspx


## Classes 

### `Class AVLTree`

This class implements an AVL Tree. 
Each node of this tree is implemented as AVLTreeNode, in the class AVLTreeNode.java; each of these nodes has a key (a positive integer) and a data entry (a positive integer). 
This class should have two instance variables: 
+ (1) an integer size that is the number of records in the tree (number of internal nodes, remember leaves do not records)
+ (2) an AVLTreeNode root, the root of the AVL Tree.

**`Public Methods Implemented`**

**Description:** *A constructor which returns a new **AVLTree** object, it sets **size** to zero
and the **root** to a new leaf node (use one of the constructors for AVLTreeNode).*
`````````````
public AVLTree()
`````````````
---
**Description:** *Given a **node**, sets it as the **root** of the AVL Tree.*
`````````````
public void setRoot(AVLTreeNode node)
`````````````
---

**Description:** *Return the **root** of the AVL Tree.*
`````````````
public AVLTreeNode root()
`````````````
---
**Description:** *Given a **node**, is the node the root of the AVL Tree? Return **true** if node is the root, and return **false** otherwise.*
`````````````
public boolean isRoot(AVLTreeNode node)
`````````````
---
**Description:** *Return the number of elements stored in the tree.*
`````````````
public int getSize()
`````````````
---

**Description:** *Given the root of a binary search tree **node** and a **key**, return the node containing key as its key; otherwise return the leaf node where k should have been in the AVL Tree.*
`````````````
public AVLTreeNode get(AVLTreeNode node, int key)
`````````````
---

**Description:** *Given the root of a binary search tree node, return the node containing the smallest key; return null if the AVLTree has no data stored in it.*
`````````````
public AVLTreeNode smallest(AVLTreeNode node)
`````````````
---

**Description:** *Put method for a binary search tree (will be used by another method for properly inserting data into an AVL Tree). Given the root of a binary search tree node and a key-value pair key and data, return the node storing the the new node containing record (key, data); this method must throw the TreeException if a record with a duplicate key is attempted to be inserted into the tree.* 

`````````````
public AVLTreeNode put(AVLTreeNode node, int key, int data) throws TreeException
`````````````
---

**Description:** *Remove method for a binary search tree. Given the root of a binary search tree node and a key, remove the record with key from the tree. The method must return the node where the removed node used to be. If there is no node storing a record with key, throw the TreeException.* 

`````````````
public AVLTreeNode remove(AVLTreeNode node, int key) throws TreeException
`````````````
---

**Description:** *Return an ArrayList(using Java’s ArrayList class) with AVLTreeNode objects from an inorder traversal. Use the next method to perform the inorder traversal; the nodes in the list have keys of value from smallest to largest (not the data in the nodes).* 

`````````````
public ArrayList<AVLTreeNode> inorder(AVLTreeNode node)
`````````````
---

**Description:** *Given a subtree rooted at node and a list, perform an inorder traversal. The list must contain AVLTreeNode objects in the order delivered by an inorder traversal.* 

`````````````
public void inorderRec(AVLTreeNode node, ArrayList<AVLTreeNode> list)
`````````````
---

**`The public methods you must implement specifically for the AVL Tree.`**

**Description:** *Recomputes the height of node, recall that this was provided in class.* 

`````````````
public void recomputeHeight(AVLTreeNode node)
`````````````
---
**Description:** *This method re-balances the tree and updates the heights of nodes as the method moves up to the root of the tree.* 

`````````````
public void rebalanceAVL(AVLTreeNode r, AVLTreeNode v)
`````````````
---

**Description:** *Given the root of an AVL Tree node and a key-value pair key and data, insert this record into the AVL Tree, re-balance if necessary. It must call the rebalanceAVL method.* 

`````````````
public void putAVL(AVLTreeNode node, int key, int data) throws TreeException
`````````````
---

**Description:** *Given the root of an AVL Tree node and a key, remove the record with key (use your remove method), rebalance whenever necessary. This method must call the rebalanceAVL method.* 

`````````````
public void removeAVL(AVLTreeNode node, int key) throws TreeException
`````````````
---


