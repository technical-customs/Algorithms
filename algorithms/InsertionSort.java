package algorithms;

class InsertionSort{
    /**
     * Picks one array element and places into already sorted subarray
     * In place comparison sort
     * Stable - Order of elements with same value not changed
     * Online sorting algorithm, can sort list as and when it receives it
     * 
     * Time Complexity: O(n^2) worst and average, O(n) linear
     * Space Complexity: O(1)
     * 
     */
    public void sort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        
        int n = arr.length;
        
        for(int x = 1; x < n; x++){
            int tmp = arr[x];
            int y = x-1;
            
            while(y >= 0 && tmp < arr[y]){
                arr[y+1] = arr[y];
                y--;
            }
            arr[y+1] = tmp;
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