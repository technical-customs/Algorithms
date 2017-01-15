class DoublyLinkedList<T extends Comparable<T>>{
    /*
    * Doubly Linked List - Nodes connect to each other with prev and next ref
    *
    * Run Times:
    * Insertion - O(1) best case when head and tail maintained
    * Deletion - O(n)
    * Searching - O(n)
    *
    */
    private Node<T> head = null, tail = null;
    private int size = 0;
    
    public DoublyLinkedList(){}
    
    public void add(T value){
        Node<T> node = new Node<>(value);
        
        if(head == null){
            head = node;
            tail = node;
        }else{
            node.setPrev(tail);
            tail.setNext(node);
            tail = node;
        }
        size += 1;
    }
    public boolean contains(T value){
        if(head == null){
            return false;
        }
        Node<T> node = head;
        while(node != null && node.getValue() != value){
            node = node.getNext();
        }
        
        return node != null;
    }
    public boolean remove(T value){
        if(head == null){
            return false;
        }
        
        if(value == head.getValue()){
            if(head == tail){
                head = null;
                tail = null;
            }else{
                head = head.getNext();
                head.setPrev(null);
            }
            size -= 1;
            return true;
        }
        Node<T> node = head.getNext();
        
        while(node != null && value != node.getValue()){
            node = node.getNext();
        }
        if(node == tail){
            tail = tail.getPrev();
            tail.setNext(null);
            size -= 1;
            return true;
        }else if(node != null){
            node.getPrev().setNext(node.getNext());
            node.getNext().setPrev(node.getPrev());
            size -= 1;
            return true;
        }
        return false;
    }
    public void traverse(){
        if(head == null){
            return;
        }
        Node<T> node = head;
        while(node != null){
            System.out.println("Value: " + node.getValue());
            node = node.getNext();
        }
    }
    public void reversetraverse(){
        if(tail == null){
            return;
        }
        Node<T> node = tail;
        while(node != null){
            System.out.println("Value: " + node.getValue());
            node = node.getPrev();
        }
    }
    public int getSize(){
        return this.size;
    }
    
    public static void main(String[] args){
        DoublyLinkedList dll = new DoublyLinkedList();
        dll.traverse();
        dll.add(7);
        dll.add(12);
        dll.add("String");
        dll.traverse();
        dll.remove(7);
        System.out.println(dll.contains(12));
        dll.traverse();
    }
}