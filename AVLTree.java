
import java.util.LinkedList;
import java.util.Queue;

class AVLTree<T>{
    /**
     * Binary tree with self balancing properties
     * Run Times: all O(logn)
     */
    
    Node<T> root = null;
    int count = 0;
    
    public Node<T> getRoot(){
        return this.root;
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
    
    
    private void checkBalance(Node<T> node){
        int lh = -1, rh = -1;
        //calculateHeight(node);
        
        lh = (node.getLeftChild() != null) ? calculateHeight(node.getLeftChild()) : -1;
        rh = (node.getRightChild() != null) ? calculateHeight(node.getRightChild()) : -1;
        
        System.out.println("LH - " + lh + " RH - " + rh);
        System.out.println("Dif: " + (lh - rh));
        int dif = (lh - rh);
        
        if(dif > 1){
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
        }else if(dif < -1){
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
                leftRotation(node);
            }else{
                
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
    
    private void leftRotation(Node<T> node){
        //nodes right child becomes parent, node becomes left child of right child
        if(node.getRightChild() == null){
            return;
        }
        
        Node<T> rightnode = node.getRightChild();
        if(rightnode.getLeftChild() != null){
            //if rightnode has a left child, set it to nodes right child
            node.setRightChild(rightnode.getLeftChild());
        }
        rightnode.setLeftChild(node);
    }
    private void rightRotation(Node<T> node){
        if(node.getLeftChild() == null){
            return;
        }
        Node<T> leftnode = node.getLeftChild();
        if(leftnode.getRightChild() != null){
            //if leftnode has a right child, set it to nodes left child
            node.setLeftChild(leftnode.getRightChild());
        }
        leftnode.setRightChild(node);
    }
    private void leftRightRotation(Node<T> node){
        if(node.getLeftChild() == null){
            return;
        }
        leftRotation(node.getLeftChild());
        rightRotation(node);
    }
    private void rightLeftRotation(Node<T> node){
        if(node.getRightChild() == null){
            return;
        }
        rightRotation(node.getRightChild());
        leftRotation(node);
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
        Node n1 = new Node<>("Node 1");
        
        Node<String> n2 = new Node<>("Node 2");
        Node<String> n3 = new Node<>("Node 3");
        Node<String> n4 = new Node<>("Node 4");
        Node<String> n5 = new Node<>("Node 5");
        
        n1.setLeftChild(n2);
        n1.setRightChild(n3);
        n2.setRightChild(n4);
        n4.setLeftChild(n5);
        
        avltree.calculateHeight(n1);
        avltree.calculateHeight(n2);
        avltree.calculateHeight(n3);
        avltree.calculateHeight(n4);
        avltree.calculateHeight(n5);
        
        avltree.checkBalance(n1);
        
        //System.out.println(n1.getHeight());
    }
}