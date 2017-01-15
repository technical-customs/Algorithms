class SinglyLinkedList<T extends Comparable<T>>{
    /*
    * Singly Linked List - Nodes connect to each other with next ref.
    *
    * Run Times:
    * Insertion - O(1) best case when head and tail maintained
    * Deletion - O(n)
    * Searching - O(n)
    *
    */
    
    
    private Node<T> head = null, tail = null;
    private int size = 0;
    
    public SinglyLinkedList(){}
    
    public void add(T value){
        Node<T> node = new Node<>(value);
        if(head == null){
            head = node;
            tail = node;
        }else{
            tail.setNext(node);
            tail = node;
        }
        size += 1;
    }
    public boolean contains(T value){
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
        Node<T> node = head;
        if(node.getValue() == value){
            if(head == tail){
                head = null;
                tail = null;
            }else{
                head = head.getNext();
            }
            size -= 1;
            return true;
        }
        
        while(node.getNext() != null && node.getNext().getValue() != value){
            node = node.getNext();
        }
        if(node.getNext() != null){
            if(node.getNext() == tail){
                tail = node;
            }
            node.setNext(node.getNext().getNext());
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
            System.out.println("VALUE: " + node.getValue());
            node = node.getNext();
        }
    }
    public void reversetraverse(){
        if(tail != null){
            Node<T> curr = tail;
            while(curr != head){
                Node<T> prev = head;
                while(prev.getNext() != curr){
                    prev = prev.getNext();
                }
                System.out.println("VALUE: " + curr.getValue());
                curr = prev;
            }
            System.out.println("VALUE: " + curr.getValue());
        }
    }
    public int getSize(){
        return this.size;
    }
    
    public static void main(String[] args){
        SinglyLinkedList sll = new SinglyLinkedList();
        
        sll.traverse();
        sll.add(7);
        sll.add(12);
        sll.add("String");
        sll.traverse();
        sll.remove(7);
        System.out.println(sll.contains(12));
        sll.traverse();
    }
    
}