
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class AVLTree<T extends Comparable<T>>{
    /**
     * Binary tree with self balancing properties
     * Run Times: all O(logn)
     */
    
    Node<T> root = null;
    int count = 0;
    
    public Node getRoot(){
        return this.root;
    }
    
    public void add(T value){
        if(root == null){
            root = new Node(value);
        }else{
            insertnode(root,value);
        }
        count += 1;
    }
    public void insertnode(Node node,T value){
        if(node.getValue().compareTo(value) > 0){
            if(node.getLeftChild() == null){
                node.setLeftChild(new Node(value));
            }else{
                insertnode(node.getLeftChild(),value);
            }
        }else{
            if(node.getRightChild() == null){
                node.setRightChild(new Node(value));
            }else{
                insertnode(node.getRightChild(),value);
            }
        }
        checkBalance(node);
    }
    public boolean remove(T value){
        Node nodeToRemove = root;
        Node parent = null;
        Stack path = new Stack();
        path.add(root);
        
        while(nodeToRemove != null && nodeToRemove.getValue() == value){
            parent = nodeToRemove;
            
            if(nodeToRemove.getValue().compareTo(value) > 0){
                nodeToRemove = nodeToRemove.getLeftChild();
            }else{
                nodeToRemove = nodeToRemove.getRightChild();
            }
            path.push(nodeToRemove);
        }
        
        if(nodeToRemove == null){
            return false;
        }
        
        
        
        
        return true;
    }
    public boolean contains(Node root, T value){
        if(root == null){
            return false;
        }
        if(root.getValue() == value){
            return true;
        }else if(root.getValue().compareTo(value) > 0){
            return contains(root.getLeftChild(), value);
        }else{
            return contains(root.getRightChild(), value);
        }
    }
    
    
    private void checkBalance(Node<T> node){
        int lh = -1, rh = -1;
        calculateHeight(node);
        
        lh = (node.getLeftChild() != null) ? calculateHeight(node.getLeftChild()) : -1;
        rh = (node.getRightChild() != null) ? calculateHeight(node.getRightChild()) : -1;
        
        System.out.println("LH - " + lh + " RH - " + rh);
        System.out.println("Dif: " + (lh - rh));
        
        if((lh - rh) > 0){
            //left child is bigger
            System.out.println("Left Side Bigger");
            
            //if height(node.left.left) - height(node.left.right) > 0
            //  right rotation node
            //else
            //  leftandright rotation node
            int ll = -1, lr = -1;
            
            ll = (node.getLeftChild().getLeftChild() != null) ? calculateHeight(node.getLeftChild().getLeftChild()): -1;
            lr = (node.getLeftChild().getRightChild() != null) ? calculateHeight(node.getLeftChild().getRightChild()): -1;
            
            System.out.println("LL - LR = " + (ll-lr));
            if(ll - lr > 0){
                //left heavy
                System.out.println("Right Rotation");
                rightRotation(node);
            }else{
                //right heavy
                System.out.println("Left Right Rotation");
                leftRightRotation(node);
            }
        }else if((lh - rh) < 0){
            //right child is bigger
            System.out.println("Right Side Bigger");
            
            //if height(node.right.left) - height(node.right.right) < 0
            //  left rotation node
            //else
            //  rightandleft rotation node
            
            int rl = -1, rr = -1;
            
            rl = (node.getRightChild().getLeftChild() != null) ? calculateHeight(node.getRightChild().getLeftChild()): -1;
            rr = (node.getRightChild().getRightChild() != null) ? calculateHeight(node.getRightChild().getRightChild()): -1;
            
            if(rl - rr < 0){
                System.out.println("Left Rotation");
                
                leftRotation(node);
            }else{
                System.out.println("Right Left Rotation");
                rightLeftRotation(node);
            }
        }else if((lh - rh) == 0){
            //trees are balanced
            if(lh != -1 && rh != -1){
                checkBalance(node.getLeftChild());
                checkBalance(node.getRightChild());
            }
        }
    }
    private int calculateHeight(Node<T> node){
        int rh = -1, lh = -1;
        
        //has right child
        if(node.getRightChild() != null){
            //System.out.println("Has Right :");
            
            calculateHeight(node.getRightChild());
            
            rh = node.getRightChild().getHeight();
        }
        
        //has left child
        if(node.getLeftChild() != null){
            //System.out.println("Has Left Child:");
            
            calculateHeight(node.getLeftChild());
            
            lh = node.getLeftChild().getHeight();
        }
        
        //System.out.println("Right Child Height: " + rh);
        //System.out.println("Left Child Height: " + lh);
        if(lh == -1 && rh == -1){
            //has no child
            //System.out.println("No Children");
            
            node.setHeight(0);
        }else{ 
            //tests if height of either side is greater than 1
            
            if(Math.abs(lh - rh) > 1){
                
            }
            
            //set node height to biggest child height +1
            if(lh > rh){
                node.setHeight(1 + lh);
            }else if (lh < rh){
                node.setHeight(1 + rh);
            }
        }
        
        //System.out.println("Node Height: " + node.getHeight());
        //System.out.println();
        return node.getHeight();
    }
    
    private Node<T> leftRotation(Node<T> node){
        //nodes right child becomes parent, node becomes left child of right child
        //returns root
        if(node.getRightChild() == null){
            return null;
        }
        
        Node<T> rightnode = node.getRightChild();
        node.setRightChild(rightnode.getLeftChild());
        rightnode.setLeftChild(node);
        
        calculateHeight(node);
        calculateHeight(rightnode);
        
        return rightnode;
    }
    private Node<T> rightRotation(Node<T> node){
        //returns root
        if(node.getLeftChild() == null){
            return null;
        }
        
        Node<T> leftnode = node.getLeftChild();
        node.setLeftChild(leftnode.getRightChild());
        leftnode.setRightChild(node);
        
        calculateHeight(node);
        calculateHeight(leftnode);
        
        return leftnode;
    }
    
    private void leftRightRotation(Node<T> node){
        if(node.getLeftChild() == null){
            return;
        }
        Node<T> leftnode = node.getLeftChild();
        leftnode.setRightChild(rightRotation(leftnode.getRightChild()));
        node.setLeftChild(leftRotation(leftnode));
    }
    private void rightLeftRotation(Node<T> node){
        if(node.getRightChild() == null){
            return;
        }
        Node<T> rightnode = node.getRightChild();
        rightnode.setLeftChild(leftRotation(rightnode.getLeftChild()));
        node.setRightChild(rightRotation(rightnode));
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
        AVLTree<String> avltree = new AVLTree<>();
        Node n1 = new Node<>("8");
        
        Node<String> n2 = new Node<>("4");
        Node<String> n3 = new Node<>("18");
        Node<String> n4 = new Node<>("6");
        Node<String> n5 = new Node<>("5");
        Node<String> n6 = new Node<>("Node 6");
        Node<String> n7 = new Node<>("Node 7");
        Node<String> n8 = new Node<>("Node 8");
        
        n1.setLeftChild(n2);
        n1.setRightChild(n3);
        n2.setRightChild(n4);
        n4.setLeftChild(n5);
        
        n6.setLeftChild(n7);
        n7.setLeftChild(n8);
        
        avltree.calculateHeight(n1);
        avltree.calculateHeight(n2);
        avltree.calculateHeight(n3);
        avltree.calculateHeight(n4);
        avltree.calculateHeight(n5);
        
        //
        //*
        System.out.println("8: lc - " + n1.getLeftChild() + " rc - " + n1.getRightChild());
        System.out.println("4: lc - " + n2.getLeftChild() + " rc - " + n2.getRightChild());
        System.out.println("18: lc - " + n3.getLeftChild() + " rc - " + n3.getRightChild());
        System.out.println("6: lc - " + n4.getLeftChild() + " rc - " + n4.getRightChild());
        System.out.println("5: lc - " + n5.getLeftChild() + " rc - " + n5.getRightChild());
        
        avltree.checkBalance(n1);
        
        
        System.out.println("8: lc - " + n1.getLeftChild() + " rc - " + n1.getRightChild());
        System.out.println("4: lc - " + n2.getLeftChild() + " rc - " + n2.getRightChild());
        System.out.println("18: lc - " + n3.getLeftChild() + " rc - " + n3.getRightChild());
        System.out.println("6: lc - " + n4.getLeftChild() + " rc - " + n4.getRightChild());
        System.out.println("5: lc - " + n5.getLeftChild() + " rc - " + n5.getRightChild());
        //*/
        
        /*
        System.out.println("N6: lc - " + n6.getLeftChild() + " rc - " + n6.getRightChild());
        System.out.println("N7: lc - " + n7.getLeftChild() + " rc - " + n7.getRightChild());
        System.out.println("N8: lc - " + n8.getLeftChild() + " rc - " + n8.getRightChild());
        
        avltree.checkBalance(n6);
        
        System.out.println("N6: lc - " + n6.getLeftChild() + " rc - " + n6.getRightChild());
        System.out.println("N7: lc - " + n7.getLeftChild() + " rc - " + n7.getRightChild());
        System.out.println("N8: lc - " + n8.getLeftChild() + " rc - " + n8.getRightChild());
        */
    }
}