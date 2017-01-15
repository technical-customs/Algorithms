class HeapSort{
    /**
     * In place comparison sort
     * Not Stable - Does not ensure order of elements
     * 
     * Steps:
     * 1.Build Max Heap - convert array to maxheap
     * 2.Sort array using max heap
     * 
     * Time Complexity: O(nlogn)
     * Space Complexity: O(1)
     */
    
    private void maxheapify(int[] arr, int curIndex, int heapSize){
        int leftchild = 2*curIndex + 1;
        int rightchild = 2*curIndex + 2;
        int largest = curIndex;
        
        if(leftchild < heapSize && arr[leftchild] > arr[curIndex]){
            largest = leftchild;
        }
        
        if(rightchild < heapSize && arr[rightchild] > arr[curIndex]){
            largest = rightchild;
        }
        
        if(largest != curIndex){
            swap(arr, curIndex, largest);
        }
    }
    private void buildMaxHeap(int[] arr, int heapSize){
        int lastElementIndex = arr.length -1;
        int parentIndex = (lastElementIndex - 1)/2;
        for(int x = parentIndex; x >= 0; x--){
            maxheapify(arr, x, heapSize);
        }
    }
    public void sort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        
        buildMaxHeap(arr, arr.length);
        int heapSize = arr.length;
        
        for(int x = arr.length - 1; x > 0; x--){
            swap(arr, 0, x);
            heapSize = heapSize - 1;
            maxheapify(arr, 0, heapSize);
        }
    }
    
    private void swap(int[] arr, int x, int y){
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
        
    }
    public static void main(String[] args){
        
    }
}