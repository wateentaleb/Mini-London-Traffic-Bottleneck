import java.util.ArrayList;
import java.util.ArrayList;

/**
 * An ADT for an AVL Tree.
 * @author: Wateen Taleb
 * @date: Winter 2019
 */
public interface AVLTreeADT {

		
		/**
		 * get operation: given a key, return the node with that key, return null otherwise.
		 * @param node - root of a binary search tree.
		 * @param key
		 * @return node containing key k
		 */
		public AVLTreeNode get(AVLTreeNode node, int key);
		
		/**
		 * Get the node with the smallest key, it is exists (null if it does not exist).
		 * @param node - when called, give a root of a tree.
		 * @return the internal node with the smallest key. 
		 */
		public AVLTreeNode smallest(AVLTreeNode node);
		
		/**
		 * put operation of a BST.
		 * @param node - node provided, assume the root is provided.
		 * @param key - key of new record
		 * @param data - data of new record.
		 * @return the node storing the new record.
		 */
		public AVLTreeNode put(AVLTreeNode node, int key, int data) throws TreeException;
		
		/**
		 * Remove a record with a given key in a tree rooted at a given node.
		 * @param node - the tree rooted at node.
		 * @param key - key of node we need to remove.
		 * @return the parent of the node.
		 */
		public AVLTreeNode remove(AVLTreeNode node, int key) throws TreeException;
		
		/**
		 * Insert a record (key,data) into the tree, re-balance the tree as necessary.
		 * @param node - root of the tree
		 * @param key - a key
		 * @param data - a data item
		 * @throws TreeException - if a duplicate key is found.
		 */
		public void putAVL(AVLTreeNode node, int key, int data) throws TreeException;
		
		/**
		 * Remove a record with key k, re-balances if necessary.
		 * @param node - root of tree
		 * @param key - key meant to be removed.
		 * @throws TreeException - if the key does not exist in the tree.
		 */
		public void removeAVL(AVLTreeNode node, int key) throws TreeException;
		
		/**
		 * Updates the height of a node (i.e. its height is maintained when it is changed, 1+max(height of left child, height of right child)).
		 * @param node
		 */
		public void recomputeHeight(AVLTreeNode node);
		
		/**
		 * This method rebalances the tree, and updates the heights of nodes as subtrees are rotated (moving upwards in the tree).
		 * @param r - root of AVL Tree
		 * @param v - where we first consider 
		 */
		public void rebalanceAVL(AVLTreeNode r, AVLTreeNode v);
		
		/**
		 * inorder traversal, gives a list.
		 * @param node - tree rooted at node.
		 * @return list containing nodes
		 */
		public ArrayList<AVLTreeNode> inorder(AVLTreeNode node);
		
		/**
		 * inorder traversal.
		 * @param node - tree rooted at node.
		 * @return an ArrayList containing inorder traversal.
		 */
		public void inorderRec(AVLTreeNode node, ArrayList<AVLTreeNode> list);
		
		/**
		 * get number of elements stored in the tree.
		 * @return size
		 */
		public int getSize();
		
		/**
		 * Is the provided node the root?
		 * @param node - some node of an AVLTree
		 * @return true if it is the root, false otherwise.
		 */
		public boolean isRoot(AVLTreeNode node);
		
		/**
		 * Normally we do not want to expose the root, but oh well.
		 * @return root
		 */
		public AVLTreeNode root();
		
		/**
		 * Set the root
		 * @param node - set the root
		 */
		public void setRoot(AVLTreeNode node);

}
