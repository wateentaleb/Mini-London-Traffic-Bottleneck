/**
 * A Tree Node for an AVL tree
 * @author: Wateen Taleb
 * @date: Winter 2019
 */
public class AVLTreeNode {

	/**
	 * The key, an integer.
	 */
	private Integer key;
	/**
	 * The data, an integer.
	 */
	private Integer data;
	/**
	 * parent - a parent reference
	 */
	private AVLTreeNode parent;
	/**
	 * left - a left child
	 */
	private AVLTreeNode left;
	/**
	 * right - a right child
	 */
	private AVLTreeNode right;
	/**
	 * height - the height of the node (an integer).  This must be maintained.
	 */
	private int height;
	
	/**
	 * Default Constructor - a constructor designed for a leaf node that has no parent (the root that is a leaf).
	 */
	public AVLTreeNode(){
		this.key = null;
		this.data = null;
		this.parent = null;
		this.left = null;
		this.right = null;
		this.height = 0;
	}
	/**
	 * Constructor - a constructor designed for a leaf node that has a parent. 
	 */
	public AVLTreeNode(AVLTreeNode parent){
		this.key = null;
		this.data = null;
		this.parent = parent;
		this.left = null;
		this.right = null;
		this.height = 0;		
	}
	
	/**
	 * Constructor - a constructor for an internal node where the left & right children are unknown, and parent is unknown; height must be unknown, so it needs to be set later.
	 */
	public AVLTreeNode(int key, int data){
		this.key = key;
		this.data = data;
		this.parent = null;
		this.left = null;
		this.right = null;
		this.height = 0;		
	}
	
	/**
	 * Constructor - a constructor for an internal node where the left & right children are known, and a parent is known; height must be computed manually.
	 */
	public AVLTreeNode(int key, int data, AVLTreeNode left, AVLTreeNode right, AVLTreeNode parent){
		this.key = key;
		this.data = data;
		this.parent = parent;
		this.left = left;
		this.right = right;
		this.height = 0;			
	}
	
	/**
	 * is this node the root?  It must be the root if it has no parent.
	 */
	public boolean isRoot(){
		if(this.parent==null){//does the node have a parent?
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * is this node a leaf?  It must have no children if it is a leaf.
	 */
	public boolean isLeaf(){
		if(this.left == null && this.right == null){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * is this an internal node?  It must have at least one child (a leaf or might be another internal node).
	 */
	public boolean isInternal(){
		if(this.left != null || this.right != null){
			return true;
		}
		else{
			return false;
		}
	}

	/**
	 * Set the key - we are combining the node with its key and value, so be careful.
	 */
	public void setKey(int key){
		this.key = key;
	}
	
	/**
	 * Get the key
	 */
	public int getKey(){
		return this.key;
	}
	
	/**
	 * Get the data.
	 */
	public int getData(){
		return this.data;
	}
	
	/**
	 * Set the data.  Be careful when changing the data here.
	 * @param data
	 */
	public void setData(int data){
		this.data = data;
	}
	
	/**
	 * Increment the value in data by one.  Used specifically for the Graph application.
	 */
	public void incrementData(){
		this.data = this.data + 1;
	}
	
	/**
	 * get the height of the node.
	 */
	public int getHeight(){
		return this.height;
	}
	
	/**
	 * set the height of the node.  This assumes the correct calculation is passed to it from AVLTree.
	 * @param height
	 */
	public void setHeight(int height){
		this.height = height;
	}
	
	/**
	 * get left child
	 */
	public AVLTreeNode getLeft(){
		return this.left;
	}
	
	/**
	 * set left child
	 * @param left - a new left child
	*/
	public void setLeft(AVLTreeNode left){
		this.left = left;
	}

	/**
	 * get right child
	 */
	public AVLTreeNode getRight(){
		return this.right;
	}
	
	/**
	 * set right child
	 * @param right - a new right child
	*/
	public void setRight(AVLTreeNode right){
		this.right = right;
	}
	
	/**
	 * get parent
	 */
	public AVLTreeNode getParent(){
		return this.parent;
	}
	
	/**
	 * set the parent of the node.
	 * @param parent
	 */
	public void setParent(AVLTreeNode parent){
		this.parent = parent;
	}
}
