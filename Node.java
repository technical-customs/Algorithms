class Node<T extends Comparable<T>> implements Comparable<T>{
    /*
    * Node for Linked Lists, Search Trees,
    */
    
    private T value = null;
    private Node<T> prev = null;
    private Node<T> next = null;
    private int index;
    private int height;
    public int balance;
    private Node<T> parent = null;
    private Node<T> leftChild = null;
    private Node<T> rightChild = null;

    public Node(){}
    public Node(T value){
        this.value = value;
    }
    @Override
    public int compareTo(T data)
    {
        return data.compareTo(data);
    }
    
    public void setValue(T value){
        this.value = value;
    }
    public T getValue(){
        return this.value;
    }
    
    //Linked List Properties
    public void setNext(Node<T> next){
        this.next = next;
    }
    public Node<T> getNext(){
        return this.next;
    }
    public void setPrev(Node<T> prev){
        this.prev = prev;
    }
    public Node<T> getPrev(){
        return this.prev;
    }
    
    //Heap Properties
    public void setIndex(int index){
        this.index = index;
    }
    public int getIndex(){
        return this.index;
    }
    
    
    //Tree Properties
    public void setHeight(int height){
        this.height = height;
    }
    public int getHeight(){
        return this.height;
    }
    
    public void setParent(Node<T> parent){
        this.parent = parent;
    }
    public Node<T> getParent(){
        return this.parent;
    }
    public void setLeftChild(Node<T> leftChild){
        this.leftChild = leftChild;
        //leftChild.setParent(this);
    }
    public Node<T> getLeftChild(){
        return this.leftChild;
    }
    public void setRightChild(Node<T> rightChild){
        this.rightChild = rightChild;
        //rightChild.setParent(this);
    }
    public Node<T> getRightChild(){
        return this.rightChild;
    }
    
    @Override
    public String toString(){
        return (value!=null)?value.toString():"NULL";
    }
    public static void main(String[] args){
        
    }
}