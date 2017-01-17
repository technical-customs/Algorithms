package algorithms;

class MergeSort<T extends Comparable<T>>{
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
    
    //Linked List
    public Node<T> merge(Node<T> nodea, Node<T> nodeb){
        //merges two lists in one
       Node<T> node = null;
       
       if(nodea == null){
           return nodeb;
       }
       if(nodeb == null){
           return nodea;
       }
       
       if(nodea.getValue().compareTo(nodeb.getValue()) < 0){
           node = nodea;
           node.setNext(merge(nodea.getNext(),nodeb));
       }else{
           node = nodeb;
           node.setNext(merge(nodea,nodeb.getNext()));
       }
       
       return node;
    }
    private Node<T> sort(Node<T> node){
        Node n = node;
        
	// find the length of the linkedlist
	int mid = getLength(node) / 2;
        if (node.getNext() == null){
            return node;
        }
	    
	// set one pointer to the beginning of the list and another at the next
	// element after mid
        while (mid - 1 > 0) {
	    n = n.getNext();
	    mid--;
	}
	Node nn = n.getNext();
        // make nn points to the beginning of
        // the second half of the list
	n.setNext(null);// break the list
        
	n = node;// make one pointer points at the beginning of the first 
        // half of the list
        
        Node t1 = sort(n);//make recursive calls 
        Node t2 = sort(nn);
        
        return merge(t1, t2); // merge the sorted lists
    }
    private SinglyLinkedList<T> sort(SinglyLinkedList list){
        if(list.getHead() != null){
            Node<T> node = sort(list.getHead());
            SinglyLinkedList l = new SinglyLinkedList();
            while(node != null){
                l.add(node.getValue());
                node = node.getNext();
            }
            
            return l;
        }
        return null;
    }
    private int getLength(Node<T> node){
        int count = 0;
        Node<T> n = node;
        
        while(n != null){
            count++;
            n = n.getNext();
        }
        return count;
    }
    
    
    //array
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
        MergeSort ms = new MergeSort();
        int[] arr = new int[6];
        
        arr[0] = 9;
        arr[1] = 2;
        arr[2] = 4;
        arr[3] = 5;
        arr[4] = 3;
        arr[5] = 7;
        
        SinglyLinkedList ll = new SinglyLinkedList();
        ll.add(9);
        ll.add(2);
        ll.add(4);
        ll.add(5);
        ll.add(3);
        ll.add(7);
        
        //ll.traverse();
        
        //ll = ms.sort(ll);
        
        //ll.traverse();
        
        for(int i: arr){
            System.out.println(i);
        }
        
        ms.sort(arr,0,arr.length-1);
        
        for(int i: arr){
            System.out.println(i);
        }
        
    }
            
}