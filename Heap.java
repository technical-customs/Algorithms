package algorithms;

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
    public T[] heap = (T[]) new Comparable[8];
    private boolean min = true;
    public Heap(){}
    public Heap(boolean min){ this.min = min;}
    public Heap(int min){
        if(min > 0){
            this.min = false;
        }
    }
    
    
    public void add(T value){
        heap[size] = value;
        size += 1;
        if(min){
            minheapify();
        }else{
            maxheapify();
        }
        
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
        
        while(left < size && (heap[index].compareTo(heap[left]) > 0 || heap[index].compareTo(heap[right]) > 0) ){
            if(heap[left].compareTo(heap[right]) < 0){
                swap(right, index);
                index = right;
            }
        }
        
        return true;
    }
    private int findindex(T[] heap, T value){
        if(size <= 0){
            return -1;
        }
        for(int x = 0; x < heap.length; x++){
            if(heap[x] == value){
                return x;
            }
        }
        return -1;
    }
    public void minheapify(){
        int i = size - 1;
        
        while(i > 0 && heap[i].compareTo(heap[(i-1)/2]) < 0){
            swap(i,(i-1)/2);
            i = (i - 1)/2;
        }
    }
    public void maxheapify(){
        int i = size - 1;
        
        while(i > 0 && heap[i].compareTo(heap[(i-1)/2]) > 0){
            swap(i, (i-1)/2);
            i = (i - 1)/2;
        }
    }
    
    public int getSize(){
        return this.size;
    }
    private void swap(int a, int b){
        //swap index portion, need to swap position in heap
        T t = heap[a];
        
        heap[a] = heap[b];
        heap[b] = t;
    }
    public static void main(String[] args){
        Heap heap = new Heap();
        
        heap.add(13);
        heap.add(8);
        //heap.add(5);
       
        
        int count = 0;
        
        
        for(int x = 0; x < heap.heap.length; x++){
            if(heap.heap[x] == null) continue;
            System.out.println("Value: " + x + ". " + heap.heap[x].toString());
        }
    }
}