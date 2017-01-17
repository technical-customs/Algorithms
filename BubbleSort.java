package algorithms;

class BubbleSort{
    /**
     * In place comparison sort, compares adjacent values and swaps
     * 
     * Time Complexity: O(n^2) worst and average, O(n) when sorted
     * Space Complexity: O(1)
     */
    public BubbleSort(){
        
    }
    
    public void sort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        
        int n = arr.length;
        boolean swapped = true;
        
        while(swapped){
            swapped = false;
            
            for(int x = 1; x < n; x++){
                if(arr[x-1] > arr[x]){
                    swap(arr, x-1, x);
                    swapped = true;
                }
            }
            n--;
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