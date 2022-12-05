import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;


class ListSortq<T extends Comparable> extends RecursiveAction {
    private List<T> data;
    private int left;
    private int right;
 
    public ListSortq(List<T> data){
        this.data = data;
        this.left = 0;
        this.right = data.size() - 1;
    }
 
    public ListSortq(List<T> data, int left, int right){
        this.data = data;
        this.left = left;
        this.right = right;
    }
 
    @Override
    protected void compute() 
    {
        if (left < right){
            int pivotIndex = left + ((right - left)/2);
            pivotIndex = partition(pivotIndex);
            invokeAll(new ListSortq(data, left, pivotIndex-1), new ListSortq(data, pivotIndex+1, right));
        }
    }
 
    private int partition(int pivotIndex)
    {
        T pivotValue = data.get(pivotIndex);
 
        swap(pivotIndex, right);
 
        int storeIndex = left;
        for (int i=left; i<right; i++)
        {
            if (data.get(i).compareTo(pivotValue) < 0)
            {
                swap(i, storeIndex);
                storeIndex++;
            }
        }
 
        swap(storeIndex, right);
        return storeIndex;
    }
 
    private void swap(int i, int j){
        if (i != j){
            T iValue = data.get(i);
 
            data.set(i, data.get(j));
            data.set(j, iValue);
        }
    }
    
    
}

public class QuickSortMultiThreaded {
	public static void main(String[] args) {
        final int size = 100000 ;
 
        List<Integer> list = new ArrayList<Integer>(size);
        System.out.print("\nUnsorted list: ");
        for (int i=0; i<size; i++){
            int value = (int) (Math.random() * 500);
            list.add(value);
            System.out.print(" " + list.get(i));
        }
        System.out.println();
        if(size<=100) {
        	long start = System.nanoTime();
        	SelectionSort o = new SelectionSort(list);
        	o.sortGivenArray();
        	long end = System.nanoTime();
        	
        	System.out.print("\nSorted list: ");
        	for (int i=0; i<size; i++){
        		System.out.print(" " + list.get(i));
        	}	
        	System.out.println(String.format("\nTime taken: %f msec", (end - start) / 1000000.0));
        }
        else {
            long start = System.nanoTime();
	        ForkJoinPool pool = new ForkJoinPool();
	        pool.invoke(new ListSortq<Integer>(list));
            long end = System.nanoTime();
            System.out.print("\nSorted list: ");
	        for (int i=0; i<size; i++){
	            System.out.print(" "+list.get(i));
	        }
	        System.out.println(String.format("\nTime taken: %f msec", (end - start) / 1000000.0));

        }
    }
}
