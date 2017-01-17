package algorithms;


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
    
    public Node<T> add(T value){
        if(root == null){
            count += 1;
            root = new Node(value);
            return root;
        }else{
            count += 1;
            return insertnode(root,value);
        }
    }
    private Node<T> insertnode(Node node,T value){
        Node<T> n = new Node<>(value);
        if(node.getValue().compareTo(value) > 0){
            System.out.println(value + " - Value Smaller");
            if(node.getLeftChild() == null){
                node.setLeftChild(n);
                checkBalance(n);
                return n;
            }else{
                insertnode(node.getLeftChild(),value);
            }
        }else if(node.getValue().compareTo(value) < 0){
            System.out.println(value + " - Value Bigger");
            if(node.getRightChild() == null){
                node.setRightChild(n);
                checkBalance(n);
                return n;
            }else{
                insertnode(node.getRightChild(),value);
            }
        }
        return null;
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
    
    public void checkBalance(Node<T> node){
        int lh = (node.getLeftChild() != null) ? calculateHeight(node.getLeftChild()) : -1;
        int rh = (node.getRightChild() != null) ? calculateHeight(node.getRightChild()) : -1;
        
        System.out.println("LH - " + lh + " RH - " + rh);
        System.out.println("Dif: " + (lh - rh));
        
        if(node.getLeftChild() == null & node.getRightChild() == null){
            node.setHeight(-1);
        }else{
            node.setHeight(Math.max(lh, rh) + 1 );
        }
        
        if((lh - rh) > 1){
            //left child is bigger
            System.out.println("Left Side Bigger");
            
            int balance;
            int ll, lr;
            
            ll = (node.getLeftChild().getLeftChild() != null) ? calculateHeight(node.getLeftChild().getLeftChild()): -1;
            lr = (node.getLeftChild().getRightChild() != null) ? calculateHeight(node.getLeftChild().getRightChild()): -1;
            
            System.out.println("LL - LR = " + (ll-lr));
            if(ll - lr > 1){
                //left left
                
                //left heavy
                System.out.println("Right Rotation");
                rightRotation(node.getLeftChild());
            }else if(ll - lr < 1){
                //left right
                
                //right heavy
                System.out.println("Left Right Rotation");
                leftRightRotation(node.getLeftChild());
            }
            else{
                
            }
        }else if((lh - rh) < -1){
            //right child is bigger
            System.out.println("Right Side Bigger");
            
            int rl, rr;
            
            rl = (node.getRightChild().getLeftChild() != null) ? calculateHeight(node.getRightChild().getLeftChild()): -1;
            rr = (node.getRightChild().getRightChild() != null) ? calculateHeight(node.getRightChild().getRightChild()): -1;
            
            if(rl - rr < 0){
                leftRotation(node.getRightChild());
            }else if(rl - rr > 0){
                rightLeftRotation(node.getRightChild());
            }
        }else{
            //trees are balanced
            System.out.println("Balanced");
        }
    }
    private int calculateHeight(Node<T> node){
        //calculates height for node and entire subtree
        int lh = ((node.getLeftChild() == null)? 0 : 1);
        int rh = ((node.getRightChild() == null)? 0 : 1);
        return Math.max(lh, rh)+1;
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
        node.setLeftChild(leftRotation(node.getLeftChild()));
        rightRotation(node);
    }
    private void rightLeftRotation(Node<T> node){
        if(node.getRightChild() == null){
            return;
        }
        node.setRightChild(rightRotation(node.getRightChild()));
        leftRotation(node);
    }
    
    public void searchpreorder(){
        if(root != null){
            searchpreorder(root);
        } 
        
    }
    public void searchpreorder(Node<T> root){
        if(root != null){
            System.out.println("Value: " + root.getValue());
            searchpreorder(root.getLeftChild());
            searchpreorder(root.getRightChild());
        }
    }
    public void searchpostorder(){
        if(root != null){
            searchpostorder(root);
        } 
        
    }
    public void searchpostorder(Node<T> root){
        if(root != null){
            searchpostorder(root.getLeftChild());
            searchpostorder(root.getRightChild());
            System.out.println("Value: " + root.getValue());
        }
    }
    public void searchinorder(){
        if(root != null){
            searchinorder(root);
        } 
        
    }
    public void searchinorder(Node<T> root){
        if(root != null){
            searchinorder(root.getLeftChild());
            System.out.println("Value: " + root.getValue());
            searchinorder(root.getRightChild());
        }
    }
    
    public void seachbreadthfirst(Node<T> root){
        Queue<Node<T>> q = new LinkedList<>();
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
    
    private Node<T> findnode(Node<T> root, T value){
        if(root == null){
            return null;
        }
        if(root.getValue() == value){
            return root;
        }else if(root.getValue().compareTo(value) > 0){
            return findnode(root.getLeftChild(), value);
        }else{
            return findnode(root.getRightChild(), value);
        }
    }
    private Node<T> findparent(Node<T> root, T value){
        if(value == root.getValue()){
            return null;
        }
        if(root.getValue().compareTo(value) > 0){
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
    
    private T findmin(Node<T> root){
        if(root.getLeftChild() == null){
            return root.getValue();
        }
        return findmin(root.getLeftChild());
    }
    private T findmax(Node<T> root){
        if(root.getRightChild() == null){
            return root.getValue();
        }
        return findmax(root.getRightChild());
    }
    
    
    
    public static void main(String[] args){
        AVLTree avltree = new AVLTree<>();
        Node n1 = new Node<>("8");
        
        Node<String> n2 = new Node<>("4");
        Node<String> n3 = new Node<>("18");
        Node<String> n4 = new Node<>("6");
        Node<String> n5 = new Node<>("5");
        Node<String> n6 = new Node<>("Node 6");
        Node<String> n7 = new Node<>("Node 7");
        Node<String> n8 = new Node<>("Node 8");
        
        Node z1 = avltree.add(8);
        //avltree.searchinorder();
        
        Node z2 = avltree.add(4);
        //avltree.searchinorder();
        
        Node z3 = avltree.add(18);
        //avltree.searchinorder();
        
        Node z4 = avltree.add(6);
        //avltree.searchinorder();
        
        Node z5 = avltree.add(5);
        avltree.searchpostorder();
        
        /*
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
        */
        
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