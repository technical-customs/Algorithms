class Heap<T extends Comparable<T>>{
    /**
     * Required Expressions:
     * (index - 1)/2 (parent node)
     * 2 * index + 1 (left child)
     * 2 * index + 2 (right child)
     * 
     * 
     * Run Times:
     * Insertion - O(log n)
     * Deletion -
     * Searching -
     * 
     */
    
    private int size = 0;
    public Node<T>[] heap = new Node[8];
    public Heap(){}
    
    public void add(T value){
        heap[size] = new Node(value);
        size += 1;
        minheapify();
    }
    public boolean remove(T value){
        int index = findindex(heap, value);
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        
        if(index < 0){
            return false;
        }
        size -= 1;
        heap[index] = null;
        heap[index] = heap[size];
        
        while(left < size && (heap[index].getValue().compareTo(heap[left].getValue()) > 0 || heap[index].getValue().compareTo(heap[right].getValue()) > 0) ){
            if(heap[left].getValue().compareTo(heap[right].getValue()) < 0){
                swap(heap[right], heap[index]);
                index = right;
            }
        }
        
        return true;
    }
    private int findindex(Node<T>[] heap, T value){
        if(size <= 0){
            
        }
        for(Node<T> n: heap){
            if(n.getValue() == value){
                return n.getIndex();
            }
        }
        return -1;
    }
    public void minheapify(){
        int i = size - 1;
        
        while(i > 0 && heap[i].getValue().compareTo(heap[(i-1)/2].getValue()) < 0){
            swap(heap[i], heap[(i-1)/2]);
            i = (i - 1)/2;
        }
    }
    public void maxheapify(){
        int i = size - 1;
        
        while(i > 0 && heap[i].getValue().compareTo(heap[(i-1)/2].getValue()) > 0){
            swap(heap[i], heap[(i-1)/2]);
            i = (i - 1)/2;
        }
    }
    
    public int getSize(){
        return this.size;
    }
    private void swap(Node<T> nodea, Node<T> nodeb){
        //swap index portion, need to swap position in heap
        int ti = nodea.getIndex();
        nodea.setIndex(nodeb.getIndex());
        nodeb.setIndex(ti);
        
        //heap[nodea] = heap[nodeb];
        
    }
    public static void main(String[] args){
        Heap heap = new Heap();
        
        heap.add(8);
        heap.add(5);
        heap.add(13);
        
        for(Node<Integer> n: heap.heap){
            System.out.println("Value: " + n.getIndex() + ". " + n.getValue());
        }
    }
}