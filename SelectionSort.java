package algorithms;

class SelectionSort{
    /**
     * In place comparison sort, divides array in two: sorted and to be sorted
     * 
     * Time Complexity: O(n^2) worst and average, O(n) when sorted
     * Space Complexity: O(1)
     */
    public void sort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        
        int n = arr.length;
        
        for(int x = 0; x < n - 1; x++){
            int minIndex = x;
            
            for(int y = x + 1; y < n; y++){
                if(arr[minIndex] > arr[y]){
                    minIndex = y;
                }
            }
            
            if(x != minIndex && arr[x] != arr[minIndex]){
                swap(arr, x, minIndex);
            }
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