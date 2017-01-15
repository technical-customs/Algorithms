class MergeSort<T>{
    /**
     * Comparison based
     * Divides array into equal halves and combines in sorted manner
     * 
     * Time Complexity: Worst-case O(n log n)
     * Space Complexity: O(n)
     * 
     */
    public MergeSort(){
        
    }
    
    public void merge(int[] arr, int start, int mid, int end){
        int n1 = mid - start + 1;
        int n2 = end - mid;
        
        int[] tmp1 = new int[n1];
        int[] tmp2 = new int[n2];
        
        for(int x = 0; x < n1; x++){
            tmp1[x] = arr[start + x];
        }
        for(int y = 0; y < n2; y++){
            tmp2[y] = arr[mid + y + 1];
        }
        
        int x = 0, y = 0, z = start;
        
        while(x < n1 && y < n2){
            if(tmp1[x] <= tmp2[y]){
                arr[z] = tmp1[x];
                x++;
            }else{
                arr[z] = tmp2[y];
                y++;
            }
            z++;
        }
        
        while(x < n1){
            arr[z] = tmp1[x];
            x++;
            z++;
        }
        while(y < n2){
            arr[z] = tmp2[y];
            y++;
            z++;
        }
    }    
    private void sort(int[] arr, int start, int end){
        if(start < end){
            int mid = (start+end)/2;
            sort(arr, start, mid);
            sort(arr, mid+1, end);
            merge(arr, start, mid, end);
        }
    }
    
    public static void main(String[] args){
        
    }
            
}