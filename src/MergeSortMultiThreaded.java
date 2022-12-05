import java.util.ArrayList;
import java.util.concurrent.*;

class ListSort<N extends Comparable> extends RecursiveAction {

    int indexStart;
    int indexEnd;
    int arr[];

    private final ArrayList<Integer> arrayToSort;

    public ListSort(ArrayList<Integer> arrayToSort) {
        this.arrayToSort = arrayToSort;
    }

    public ListSort(ArrayList<Integer> arr, int low, int high) {
        this.arrayToSort = arr;
        this.indexStart = low;
        this.indexEnd = high;
    }

    public ArrayList<Integer> getArrayAfterSorting() {
        return arrayToSort;
    }

    @Override
    protected void compute() {
      if (indexStart < indexEnd && (indexEnd - indexStart) >= 1){
	    int middleElement = (indexEnd + indexStart) / 2;
	    ListSort<Integer> left = new ListSort<Integer>(arrayToSort, indexStart, middleElement);
	    ListSort<Integer> right = new ListSort<Integer>(arrayToSort, middleElement + 1, indexEnd);
	    invokeAll(left, right);
	    mergeArrayElements(indexStart, middleElement, indexEnd);
      }
        
    }

    // MERGESORT
    public void mergeArrayElements(int indexStart, int indexMiddle, int indexEnd) 
    {
        ArrayList<Integer> tempArray = new ArrayList<>();

        int getLeftIndex = indexStart;
        int getRightIndex = indexMiddle + 1;

        while (getLeftIndex <= indexMiddle && getRightIndex <= indexEnd) 
        {
            if (arrayToSort.get(getLeftIndex).compareTo((arrayToSort.get(getRightIndex))) <= 0) 
            {
                tempArray.add(arrayToSort.get(getLeftIndex));
                getLeftIndex++;
            } 
            else 
            {
                tempArray.add(arrayToSort.get(getRightIndex));
                getRightIndex++;
            }
        }
        while (getLeftIndex <= indexMiddle) 
        {
            tempArray.add(arrayToSort.get(getLeftIndex));
            getLeftIndex++;
        }
        while (getRightIndex <= indexEnd) 
        {
            tempArray.add(arrayToSort.get(getRightIndex));
            getRightIndex++;
        }
        for (int i = 0; i < tempArray.size(); indexStart++) 
        {
            arrayToSort.set(indexStart, tempArray.get(i++));

        }

    }
    
}

public class MergeSortMultiThreaded{
	
    public static void main(String[] args) {
        
        int size = 100000;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int value = (int) (Math.random() * size);
            list.add(value);
        }

        ListSort<Integer> ob = new ListSort<Integer>(list);

        System.out.println("Array Before Sort: ");

        for (Integer integer : ob.getArrayAfterSorting()) {
            System.out.println(integer);
        }
        if (size <= 100) {
            SelectionSort o = new SelectionSort(list);
            o.sortGivenArray();
            System.out.println("Array After Selection Sort: ");
            for (int i = 0; i < size; i++) {
                System.out.println(list.get(i));
            }
        }

        else {
            long start = System.nanoTime();

            ForkJoinPool forkJoinPool = new ForkJoinPool();

            forkJoinPool.invoke(new ListSort<Integer>(list, 0, list.size() - 1));
            long end = System.nanoTime();

            System.out.println("\nSorted List:");
            for (Integer integer : ob.getArrayAfterSorting()) {
                System.out.print(" "+integer);
            }
            System.out.println(String.format("\nTime taken: %f msec", (end - start) / 1000000.0));
        }
    }
}
