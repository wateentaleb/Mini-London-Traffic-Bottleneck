/**
 * @author Wateen Taleb
 * @date Winter 2019
 *
 **/

import java.util.ArrayList;
import java.util.*;

public class AVLTree implements AVLTreeADT {

    private AVLTreeNode root;
    private int size;
    private int height;
    private int balance =0;
    private AVLTreeNode newNode;

    // AVLTree constructor, creates a new node and sets the size of the tree to 0
    // since it its empty at this point in time.
    public AVLTree(){
        this.root = new AVLTreeNode();
        this.size = 0;
        this.height = 0;
    }

    public void setRoot(AVLTreeNode node){
        this.root = node;
        node.setParent(null);
    }
    public boolean isRoot(AVLTreeNode node){
        if(node == root) return true;
        else return false;
    }



    public AVLTreeNode root(){
        return root;
    }

    public int getSize(){
        return size;
    }

    public AVLTreeNode get(AVLTreeNode node, int key){
        if(node == null) return null;
        if(node.isLeaf() || node.getKey() == key) return node;
        if(node.getKey() > key) return get(node.getLeft(),key);
        else return get(node.getRight(),key);
    }

    public AVLTreeNode smallest(AVLTreeNode node){
        AVLTreeNode current = node;
        while(current.getLeft()!=null && !current.getLeft().isLeaf()){
            current = current.getLeft();
        }

        return current;
    }

    public AVLTreeNode put(AVLTreeNode node, int data, int key) throws TreeException{
;
        // if it's an empty tree than we just input the node.
        if(node.getHeight() == 0){
            newNode = new AVLTreeNode(key,data,new AVLTreeNode(newNode),new AVLTreeNode((newNode)),null);
            incSize();
            incHeight(newNode);
            return newNode;

        }
        else {
            AVLTreeNode newNode = get(node,key);
            if(newNode.isLeaf() || newNode == null){
                AVLTreeNode parent = newNode.getParent();
                newNode = new AVLTreeNode(key,data,new AVLTreeNode(newNode),new AVLTreeNode(newNode),parent);
                boolean left = getParentSide(newNode);
               if(left) parent.setLeft(newNode);
               else parent.setRight(newNode);
               incSize();
               incHeight(newNode);
               return newNode;
            }
            throw new TreeException("Key already exists");
        }

        }

        public AVLTreeNode remove(AVLTreeNode node, int key) throws TreeException {
            AVLTreeNode check = get(node, key);
            if (check == null) throw new TreeException("Node doesn't exist, can't remove");
            else {
                AVLTreeNode successor = getSucessor(node, key);
                // Case 1

                if (check.getRight().isLeaf() && check.getLeft().isLeaf()) {
                    AVLTreeNode parent = check.getParent();
                    boolean left = getParentSide(check);
                    if (left) parent.setLeft(new AVLTreeNode(parent));
                    else parent.setRight(new AVLTreeNode(parent));
                }
                // if we are trying to remove the root

                AVLTreeNode right = check.getRight();
                AVLTreeNode left = check.getLeft();

                if (node.isRoot() && right.isLeaf()) {
                    left.setParent(null);
                    setRoot(left);
                    decSize();
                    return root();
                }

                if (check.isRoot()) {
                    setRoot(successor);
                    successor.setParent(null);
                    successor.setRight(new AVLTreeNode(successor));
                    decSize();
                    return root();

                }
                AVLTreeNode parent = check.getParent();
                if (check.getKey() > node.getKey()) {
                    parent.getRight().setKey(successor.getKey());
                    successor.getLeft().setParent(null);
                    successor.setLeft(null);
                    decSize();
                    return check;
                } else {
                    AVLTreeNode max = findMax(left);

                    node = new AVLTreeNode(max.getKey(),max.getData(),max.getLeft(),max.getRight(),max.getParent());

                    AVLTreeNode newLeft = node.getLeft();
                    AVLTreeNode newRight = node.getRight();

                    left.setParent(node);
                    right.setParent(node);

                    if(node.getKey() <node.getParent().getKey())
                        node.getParent().setLeft(node);
                    else node.getParent().setRight(node);

                    if(max.getKey() > max.getParent().getKey())
                        max.getParent().setRight(new AVLTreeNode(max.getParent()));
                    else max.getParent().setLeft(new AVLTreeNode(max.getParent()));

                    return max;


                }

            }



        }

        public void putAVL(AVLTreeNode node, int key, int data) throws TreeException{
            AVLTreeNode v = put(node,key,data);

            rebalanceAVL(node,v);
        }


    public void removeAVL(AVLTreeNode node, int key) throws TreeException {
        AVLTreeNode v = remove(node,key);


        rebalanceAVL(node,v);
    }

    public void rebalanceAVL(AVLTreeNode r, AVLTreeNode v) {

        boolean onLeft;


        while(v!=root()){
            v = v.getParent();
            this.balance = v.getLeft().getHeight() - v.getRight().getHeight();
            if (this.balance <-1 || this.balance > 1) {

                onLeft = (v.getLeft().getHeight() - v.getRight().getHeight() > 1) ? true : false;
                AVLTreeNode y = taller(v, onLeft);
                AVLTreeNode x = taller(y, onLeft);
                rotation(v, y, x);

            }
            recomputeHeight(v);
        }



    }

    public void recomputeHeight(AVLTreeNode node) {

        int height = 0;
        if (node.isLeaf()) node.setHeight(0);
        else {
            height = max(node.getLeft().getHeight(),node.getRight().getHeight())+1;
            node.setHeight(height);
        }

    }

    private AVLTreeNode rotateLeft(AVLTreeNode node){

        AVLTreeNode y = node.getRight();
        AVLTreeNode T2 = y.getLeft();
        AVLTreeNode parent = node.getParent();

        y.setLeft(node);
        y.setParent(node.getParent());
        if(node.isRoot()) setRoot(y);
        else if (parent.getKey() < node.getKey()) y.getParent().setRight(y);
        else y.getParent().setLeft(y);

        node.setParent(y);
        node.setRight(T2);
        T2.setParent(node);
        recomputeHeight(y);
        recomputeHeight(node);
        return y;
    }

    private AVLTreeNode rotateRight(AVLTreeNode node){

        AVLTreeNode x = node.getLeft();
        AVLTreeNode T2 = x.getRight();
        AVLTreeNode parent = node.getParent();

        x.setRight(node);
        x.setParent(node.getParent());
        if(node.isRoot()) setRoot(x);
        else if (parent.getKey() < node.getKey()) parent.setRight(x);
        else parent.setLeft(x);
        node.setParent(x);
        node.setLeft(T2);
        T2.setParent(node);
        recomputeHeight(node);
        recomputeHeight(x);
        return x;
    }

    public AVLTreeNode rotation(AVLTreeNode z, AVLTreeNode y, AVLTreeNode x){


        // if this is the case then, we need to do a LL rotation,
        if (balance > 1 && x.getKey()<y.getKey())
            return rotateRight(z);

            // this is the RR case
        else if(balance < -1 && x.getKey() > y.getKey())
            return rotateLeft(z);

            // this is the LR case;
        else if(balance > 1 && x.getKey() > y.getKey()){
            z.setLeft(rotateLeft(y));
            return rotateRight(z);
        }

        // RL case
        else if(balance <-1 && x.getKey() < y.getKey()){
            z.setRight(rotateRight(y));
            return rotateLeft(z);
        }

        return z;
    }




    public AVLTreeNode taller(AVLTreeNode node, boolean onLeft){
        if (node.isLeaf()) return node;
        else{
            if(node.getLeft().getHeight()>node.getRight().getHeight()) return node.getLeft();
            else if(node.getLeft().getHeight()<node.getRight().getHeight()) return node.getRight();
            else {
                if(onLeft) return node.getLeft();
                else return node.getRight();
            }
        }
    }


    public ArrayList<AVLTreeNode> inorder(AVLTreeNode node) {

        ArrayList<AVLTreeNode> array = new ArrayList<AVLTreeNode>();

        if (node.isLeaf()) return array;
        else  inorderRec(node,array);

        return array;



    }


    public void inorderRec(AVLTreeNode node, ArrayList<AVLTreeNode> list) {

        if (node.isLeaf()) return;

        inorderRec(node.getLeft(),list);
        list.add(node);
        inorderRec(node.getRight(),list);
    }


        private void incSize(){
        this.size+=1;
        }

        private void decSize(){
        this.size-=1;
        }

        private void incHeight(AVLTreeNode node){
        node.setHeight(1);
        }

        // helper function to help what side the node is to the parent node,
        // just to keep the code clean
        // returns true for Left and false for right.
        private boolean getParentSide(AVLTreeNode node){
        if(node.getParent().getKey() < node.getKey()) return false;
        return true;
        }

        private AVLTreeNode getSucessor(AVLTreeNode node, int key){
            if (node == null) return null;
            else {
                AVLTreeNode p = new AVLTreeNode();
                AVLTreeNode  p_prime = new AVLTreeNode();
                p = get(node,key);


                if (p.isInternal() && !p.getRight().isLeaf()){ // Case 1
                    return smallest(p.getRight());
                }
                else if(p.isRoot() && p.getRight().isLeaf()){
                    return p.getLeft();
                }
                else { // Case 2
                    p_prime = p.getParent();
                    while (p != node && p == p_prime.getRight()){
                        p = p_prime;
                        p_prime = p.getParent();
                    }
                    if (p == node) return null;
                    else return p_prime;
                }
            }
        }

        private AVLTreeNode findMax(AVLTreeNode node){
        if(node.isLeaf())
            return node.getParent();
            AVLTreeNode result = node;
            AVLTreeNode left = findMax(node.getLeft());
            AVLTreeNode right = findMax(node.getRight());

            if(left.getKey() > right.getKey())
                result = left;
            else result = right;
            return result;

        }

    private int max(int a, int b){
        return (a>b)? a: b;
    }


}
