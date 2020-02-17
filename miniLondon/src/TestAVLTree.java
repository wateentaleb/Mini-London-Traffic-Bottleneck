import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *  TestAVLTree - the test program for AVL Tree.  It accepts are arguments specific tests to run
 *  eg. java TestAVLTree 1 3 7 8 will run tests 1, 3, 7, and 8.  If no command line arguments are provided, it runs all tests.
 *  I advise drawing pictures of the trees in each test.
 * @author Wateen Taleb
 * @date Winter 2019
*/
public class TestAVLTree {
	public static void main(String[]args) throws Exception{

		//This will read the command line arguments, puts them into an ArrayList.
		ArrayList<String> arguments = new ArrayList<String>();
		if(args.length>0){
			for(int i =0;i<args.length;i++){
				arguments.add(args[i]);
			}
		}
//		//Test 1 - create an empty tree, it must have a leaf node only in it.
		if(args.length==0 || arguments.contains("1")){
			try{
				AVLTree tree = new AVLTree();
				if(tree.root().isLeaf()){
					System.out.println("***Test 1 passed");
				}
				else{
					System.out.println("***Test 1 failed");
					if(tree.root()==null){
						System.out.println("-----Test 1 Message: an empty tree has a leaf node.");
					}
				}
			}
			catch(Exception e){
				System.out.println("***Test 1 failed: " + e.getMessage());
			}
		}
		//Test 2 - 3 put operations, checks the size and sees that the keys are entered correctly.
		if(args.length==0 || arguments.contains("2")){
			try{
				AVLTree tree = new AVLTree();
				AVLTreeNode node = tree.put(tree.root(), 3, 0);
				AVLTreeNode left = tree.put(tree.root(), 2, 0);
				AVLTreeNode right = tree.put(tree.root(), 4, 0);


				if(tree.getSize()==3 && tree.root()==node && tree.root().getLeft() == left && tree.root().getRight()==right && !left.isLeaf() && !right.isLeaf()){
					System.out.println("***Test 2 passed");
				}
				else{
					System.out.println("***Test 2 failed");
				}

			}
			catch(Exception e){
				System.out.println("***Test 2 failed: " + e.getMessage());
			}
		}

		//Test 3 - smallest: inserts 5 records, then computes the smallest.
		if(args.length==0 || arguments.contains("3")){
			try{
				AVLTree tree = new AVLTree();
				tree.put(tree.root(),6,0);
				tree.put(tree.root(),3,0);
				tree.put(tree.root(),2,0);
				tree.put(tree.root(),1,0);
				tree.put(tree.root(),4,0);
				AVLTreeNode small = tree.smallest(tree.root());
				if(small.getKey()==1 && tree.getSize()==5){
					System.out.println("***Test 3 passed");
				}
				else if(tree.getSize()!=5){
					System.out.println("***Test 3 failed: " + "some nodes were not inserted properly");
				}
				else if(small.getKey()!=1){
					System.out.println("***Test 3 failed: " + "the smallest is node 1, smallest didn't return the node containing 1.");
				}
			}
			catch(Exception e){
				System.out.println("***Test 3 failed: " + e.getMessage());
			}
		}

		//Test 4 - checking to make sure put throws the TreeException on a duplicate key.
		if(args.length==0 || arguments.contains("4")){
			try{
				AVLTree tree = new AVLTree();
				tree.put(tree.root(),6,0);
				tree.put(tree.root(),7,0);
				tree.put(tree.root(),5,0);
				tree.put(tree.root(),4,0);
				try{
					tree.put(tree.root(),6,0);
					System.out.println("***Test 4 failed: " + "encountered a duplicate key, should be throwing TreeException");
				}
				catch(TreeException e){
					System.out.println("***Test 4 passed");
				}
			}
			catch(Exception e){
				System.out.println("***Test 4 failed");
			}
		}

		//Test 5 - test two calls of get operation, one where it should return a null, another time where it should return nothing valuable.
		if(args.length==0 || arguments.contains("5")){

			try{
				AVLTree tree = new AVLTree();
				tree.put(tree.root(), 3, 0);
				tree.put(tree.root(), 6, 0);
				AVLTreeNode nFound = tree.put(tree.root(), 9, 0);
				tree.put(tree.root(), 2, 0);
				AVLTreeNode nodeFound = tree.get(tree.root(), 9);
				AVLTreeNode nodeNotFound = tree.get(tree.root(), 8);

				if(nodeFound == nFound && !nodeFound.isLeaf() && nodeNotFound.isLeaf()){
					System.out.println("***Test 5 passed");
				}
				else{
					System.out.println("***Test 5 failed");
				}
			}
			catch(Exception e){
				System.out.println("***Test 5 failed: " + e.getMessage());
			}
		}

		//Test 6 - do some put operations, then remove node 4 (the right child of the root will be node 5, case 1 of remove is applied).
		if(args.length==0 || arguments.contains("6")){
			try{
				AVLTree tree = new AVLTree();
				AVLTreeNode nodeRoot = tree.put(tree.root(), 3, 0);
				tree.put(tree.root(), 4, 0);
				AVLTreeNode nodeReplaced = tree.put(tree.root(), 5, 0);
				tree.remove(tree.root(), 4);
				if(tree.root().getRight() == nodeReplaced){
					System.out.println("***Test 6 passed");
				}
				else{
					System.out.println("***Test 6 failed");
				}
			}
			catch(Exception e){
				System.out.println("***Test 6 failed: " + e.getMessage());
			}
		}

		//Test 7 - put in some items into the tree, remove the root and check if root is updated, put one more element in, then remove to call case 2 of remove.
		if(args.length==0 || arguments.contains("7")){
			try{
				AVLTree tree = new AVLTree();
				tree.put(tree.root(), 4, 0);
				AVLTreeNode nodeOne = tree.put(tree.root(), 3, 0);
				AVLTreeNode nodeTwo = tree.put(tree.root(),  2,  0);
				AVLTreeNode nodeThree = tree.put(tree.root(), 1, 0);
				tree.remove(tree.root(),4);//this will remove the root, it needs to re-assign the root
				if(tree.root() != nodeOne){
					System.out.println("***Test 7 failed");
				}
				else{
					//now we will put 4 back in, to get case 2 to check.
					tree.put(tree.root(), 4, 0);
					tree.remove(tree.root(), 3);
					//the root should be 4 again.
					if(tree.root().getKey()!=4){
						System.out.println("***Test 7 failed");
					}
					else{
						System.out.println("***Test 7 passed");
					}
				}
			}
			catch(Exception e){
				System.out.println("***Test 7 failed: " + e.getMessage());
			}
		}

		//Test 8 - put some data into the tree, apply case 2 of remove.
		if(args.length==0 || arguments.contains("8")){
			try{
				AVLTree tree = new AVLTree();
				tree.put(tree.root(), 3, 0);
				tree.put(tree.root(), 4, 0);
				tree.put(tree.root(), 2, 0);
				tree.put(tree.root(), 7, 0);
				tree.put(tree.root(), 5, 0);
				tree.put(tree.root(), 9, 0);
				tree.put(tree.root(), 8, 0);
				tree.remove(tree.root(), 7);//case 2 is applied
				if(tree.getSize()==6 && tree.root().getRight().getRight().getKey()==8 && tree.root().getRight().getRight().getLeft().getKey()==5 && tree.root().getRight().getRight().getRight().getKey()==9){
					System.out.println("***Test 8 passed");
				}
				else if(tree.getSize()!=6){
					System.out.println("***Test 8 failed: " + "The getSize() didn't give size 6.  Make sure you decrement the size only in case 1, case 2 will apply case 1.");
				}
				else{
					System.out.println("***Test 8 failed: " + "Case 2 may have not been done correctly");
				}
			}
			catch(Exception e){
				System.out.println("***Test 8 failed: " + e.getMessage());
			}

		}

		//Test 9 - inorder traversal
		if(args.length==0 || arguments.contains("9")){
			try{
				AVLTree tree = new AVLTree();
				tree.put(tree.root(), 8, 0);
				tree.put(tree.root(), 5, 0);
				tree.put(tree.root(), 9, 0);
				tree.put(tree.root(), 10, 0);
				tree.put(tree.root(), 2, 0);
				tree.put(tree.root(), 6, 0);
				tree.put(tree.root(), 1, 0);
				tree.put(tree.root(), 3, 0);
				tree.put(tree.root(), 7, 0);
				ArrayList<AVLTreeNode> inorderList = tree.inorder(tree.root());
				boolean matching=true;
				int[] correctArray = {1,2,3,5,6,7,8,9,10};
				for(int i = 0;i<correctArray.length;i++){
					if(inorderList.get(i).getKey()!=correctArray[i]){
						matching=false;//one doesn't match!
					}
				}
				if(matching){
					System.out.println("***Test 9 passed");
				}
				else{
					System.out.println("***Test 9 failed: " + "number of nodes in the inorder traversal is " + inorderList.size() + ", it should be 9.");
				}
			}
			catch(Exception e){
				System.out.println("***Test 9 failed: " + e.getMessage());
		}
	}

		//Test 10 - use putAVL once, check that the height is updated properly.
		if(args.length==0 || arguments.contains("10")){
			try{
				AVLTree tree = new AVLTree();
				tree.putAVL(tree.root(), 8, 0);
				//the height of the root is 1, and it has two leaf children.
				if(tree.root().getHeight()==1 && tree.root().getLeft().getHeight()==0 && tree.root().getRight().getHeight()==0){
					System.out.println("***Test 10 passed");
				}
				else{
					System.out.println("***Test 10 failed: " + "the height of the root here should be 1");
				}
			}
			catch(Exception e){
				System.out.println("***Test 10 failed: " + e.getMessage());
			}
	}

		//Test 11 - putAVL, a RR rotation; a pretty simple tree.
		if(args.length==0 || arguments.contains("11")){
			try{
				AVLTree tree = new AVLTree();
				tree.putAVL(tree.root(),1,0);
				if(tree.root().getHeight()!=1){
					System.out.println("***Test 11 failed: " + "when (1,0) was put in the tree, height of root was not updated");
				}
				else{
					tree.putAVL(tree.root(),2,0);
					if(tree.root().getHeight()!=2){
						System.out.println("***Test 11 failed: " + "when (2,0) was put in the tree, height of root was not updated");
					}
					else{
						tree.putAVL(tree.root(),3,0);
						//RR rotation happens, the resulting tree should have 2 as the root, 1 as the left child, 3 as the right child!
						if(tree.root().getKey()==2 && tree.root().getHeight()==2 && tree.root().getLeft().getKey()==1 && tree.root().getRight().getKey()==3){
							System.out.println("***Test 11 passed");
						}
						else{
							System.out.println("***Test 11 failed: " + "RR-rotation should happen here, make sure you updated all reference (including the root, if needed).");
						}
					}
				}

			}
			catch(Exception e){
				System.out.println("***Test 11 failed: " + e.getMessage());
			}
		}

		//Test 12 - putAVL, LR-rotation happens here; a pretty simple tree.
		if(args.length==0 || arguments.contains("12")){
			try{
				AVLTree tree = new AVLTree();
				tree.putAVL(tree.root(), 8, 0);
				if(tree.root().getHeight()!=1){
					System.out.println("***Test 12 failed: " + "when (8,0) was put in the tree, the heights were not updated");
				}
				else{
					tree.putAVL(tree.root(), 5, 0);
					if(tree.root().getHeight()!=2){
						System.out.println("***Test 12 failed: " + "when (5,0) was put into the tree, the heights were not updated");
					}
					else{
						tree.putAVL(tree.root(),7,0);
						//LR rotation happens here.
						if(tree.root().getKey()==7 && tree.root().getLeft().getKey()==5 && tree.root().getRight().getKey()==8 && tree.root().getHeight()==2 && tree.root().getLeft().getHeight()==1 && tree.root().getRight().getHeight()==1){
							System.out.println("***Test 12 passed");
						}
						else{
							System.out.println("***Test 12 failed: " + "LR-rotation happens, make sure everything is updated appropriately");
						}
					}
				}
			}
			catch(Exception e){
				System.out.println("***Test 12 failed: " + e.getMessage());
			}
		}

		//Test 13 - putAVL then removeAVL; it does several rotations, RL-rotation & LL-rotation(s); not as simple of a tree (there will be less information provided here, so be careful).
		if(args.length==0 || arguments.contains("13")){
			try{
				AVLTree tree = new AVLTree();
				tree.putAVL(tree.root(), 8, 0);
				tree.putAVL(tree.root(), 10, 0);
				tree.putAVL(tree.root(), 9, 0);
				//RL rotation will happen
				tree.putAVL(tree.root(), 12, 0);
				tree.putAVL(tree.root(), 7, 0);
				tree.putAVL(tree.root(), 6, 0);
				//LL rotation will happen
				tree.putAVL(tree.root(), 5, 0);
				tree.removeAVL(tree.root(), 12);
				//LL rotation will happen
				if(tree.root().getKey()==7 && tree.root().getLeft().getKey() == 6 && tree.root().getRight().getKey() == 9 && tree.root().getLeft().getLeft().getKey()==5 && tree.root().getHeight()==3){
					System.out.println("***Test 13 passed");
				}
				else{
					System.out.println("***Test 13 failed");
				}
			}
			catch(Exception e){
				System.out.println("***Test 13 failed: " + e.getMessage());
			}
		}

		//Test 14 - removeAVL, carries out the example of removeAVL I gave in class (LR rotation, then RL rotation).
		if(args.length==0 || arguments.contains("14")){
			try{
				AVLTree tree = new AVLTree();
				//construct a tree... no rotations happen.
				tree.putAVL(tree.root(), 30, 0);
				tree.putAVL(tree.root(), 20, 0);
				tree.putAVL(tree.root(), 55, 0);
				tree.putAVL(tree.root(), 10, 0);
				tree.putAVL(tree.root(), 45, 0);
				tree.putAVL(tree.root(), 25, 0);
				tree.putAVL(tree.root(), 60, 0);
				tree.putAVL(tree.root(), 15, 0);
				tree.putAVL(tree.root(), 40, 0);
				tree.putAVL(tree.root(), 50, 0);
				tree.putAVL(tree.root(), 65, 0);
				tree.putAVL(tree.root(), 35, 0);
				tree.removeAVL(tree.root(),20);
				//LR rotation, then a RL rotation happens...
				if(tree.root().getHeight()==4 && tree.root().getKey()==45 && tree.root().getLeft().getKey() == 30 && tree.root().getLeft().getHeight()==3 && tree.root().getRight().getKey()==55 && tree.root().getRight().getHeight()==3 && tree.root().getLeft().getLeft().getKey()==15 && tree.root().getLeft().getLeft().getHeight()==2 && tree.root().getRight().getRight().getKey()==60 && tree.root().getRight().getRight().getHeight()==2){
					System.out.println("***Test 14 passed");
				}
				else{
					System.out.println("***Test 14 failed");
				}


			}
			catch(Exception e){
				System.out.println("***Test 14 failed: " + e.getMessage());
			}
		}

	}

}
