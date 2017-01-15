import java.util.LinkedList;
import java.util.Queue;

class BinarySearchTree{
    /*
    * Binary Search Tree - Left subtree contains nodes of smaller value
    *                      Right subtree contains nodes of equal/greater value
    *
    * Run Times: all operations can obtain O(log n) time if tree is balanced
    * Insertion - O(log n)
    * Deletion - O(log n)
    * Searching - O(log n)
    *
    
    */
    private Node<Integer> root = null;
    private int count = 0;
    
    public BinarySearchTree(){}
    public Node<Integer> getRoot(){
        return root;
    }
    public void add(int value){
        if(root == null){
            root = new Node<>(value);
        }else{
            insertnode(root,value);
        }
        count += 1;
    }
    public void insertnode(Node<Integer> curr, int value){
        if((int)value < (int)curr.getValue()){
            if(curr.getLeftChild() == null){
                curr.setLeftChild(new Node<>(value));
            }else{
                insertnode(curr.getLeftChild(), value);
            }
        }else{
            if(curr.getRightChild() == null){
                curr.setRightChild(new Node<>(value));
            }else{
                insertnode(curr.getRightChild(), value);
            }
        }
    }
    public boolean contains(Node<Integer> root, int value){
        if(root == null){
            return false;
        }
        if((int)root.getValue() == (int)value){
            return true;
        }else if(value < root.getValue()){
            return contains(root.getLeftChild(), value);
        }else{
            return contains(root.getRightChild(), value);
        }
    }
    public boolean remove(int value){
        Node<Integer> node = findnode(root, value);
        if(node == null){
            return false;
        }
        Node<Integer> parent = findparent(root, value);
        if(count == 1){
            root = null; //only node
            count = 0;
        }else if(node.getLeftChild() == null && node.getRightChild() == null){
            if(node.getValue() < parent.getValue()){
                parent.setLeftChild(null);
            }else{
                parent.setRightChild(null);
            }
        }
        else if(node.getLeftChild() == null && node.getRightChild() != null){
            if(node.getValue() < parent.getValue()){
                parent.setLeftChild(node.getRightChild());
            }else{
                parent.setRightChild(node.getRightChild());
            }
        }
        else if(node.getLeftChild() != null && node.getRightChild() == null){
            if(node.getValue() < parent.getValue()){
                parent.setLeftChild(node.getLeftChild());
            }else{
                parent.setRightChild(node.getLeftChild());
            }
        }
        else{
            Node<Integer> largest = node.getLeftChild();
            while(largest.getRightChild() != null){
                largest = largest.getRightChild();
            }
            findparent(root, largest.getValue()).setRightChild(null);
            node.setValue(largest.getValue());
        }
        count -= 1;
        return true;
    }
    
    public void searchpreorder(Node<Integer> root){
        if(root != null){
            System.out.println("Value: " + root.getValue());
            searchpreorder(root.getLeftChild());
            searchpreorder(root.getRightChild());
        }
    }
    public void searchpostorder(Node<Integer> root){
        if(root != null){
            searchpostorder(root.getLeftChild());
            searchpostorder(root.getRightChild());
            System.out.println("Value: " + root.getValue());
        }
    }
    public void searchinorder(Node<Integer> root){
        if(root != null){
            searchinorder(root.getLeftChild());
            System.out.println("Value: " + root.getValue());
            searchinorder(root.getRightChild());
        }
    }
    public void seachbreadthfirst(Node<Integer> root){
        Queue<Node<Integer>> q = new LinkedList<>();
        while(root != null){
            System.out.println("Value: " + root.getValue());
            if(root.getLeftChild() != null){
                q.add(root.getLeftChild());
            }
            if(root.getRightChild() != null){
                q.add(root.getRightChild());
            }
            if(!q.isEmpty()){
                root = q.poll();
            }else{
                root = null;
            }
            
        }
    }
    
    private Node<Integer> findnode(Node<Integer> root, int value){
        if(root == null){
            return null;
        }
        if(root.getValue() == value){
            return root;
        }else if(value < root.getValue()){
            return findnode(root.getLeftChild(), value);
        }else{
            return findnode(root.getRightChild(), value);
        }
    }
    private Node<Integer> findparent(Node<Integer> root, int value){
        if(value == root.getValue()){
            return null;
        }
        if(value < root.getValue()){
            if(root.getLeftChild() == null){
                return null;
            }else if(root.getLeftChild().getValue() == value){
                return root;
            }else{
                return findparent(root.getLeftChild(), value);
            }
        }else{
            if(root.getRightChild() == null){
                return null;
            }else if(root.getRightChild().getValue() == value){
                return root;
            }else{
                return findparent(root.getRightChild(), value);
            }
        }
    }
    private int findmin(Node<Integer> root){
        if(root.getLeftChild() == null){
            return root.getValue();
        }
        return findmin(root.getLeftChild());
    }
    private int findmax(Node<Integer> root){
        if(root.getRightChild() == null){
            return root.getValue();
        }
        return findmax(root.getRightChild());
    }
    
    public static void main(String[] args){
        BinarySearchTree bst = new BinarySearchTree();
        
        bst.add(9);
        bst.searchinorder(bst.getRoot());
        bst.add(5);
        bst.add(19);
        bst.add(3);
        bst.searchinorder(bst.getRoot());
    }
}