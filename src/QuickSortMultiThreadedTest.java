import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.ForkJoinPool;
import java.util.*;

import org.junit.jupiter.api.Test;

class QuickSortMultiThreadedTest {
     
	//Test Case-1:
	//Check with more than 100 integers
	@Test
	void test() {
		final int SIZE = 101;
	    System.out.println("\n\nTest case-1:");
	    List<Integer> myList = new ArrayList<Integer>(SIZE);
	    System.out.print("\nUnsorted list: ");
	    for (int i=0; i<SIZE; i++){
	        int value = (int) (Math.random() * 100);
	        myList.add(value);
	        System.out.print(" "+myList.get(i));
	    } 
	    List<Integer> sortlist=myList;
	    
	    if(SIZE<=100) {
	    	SelectionSort o = new SelectionSort(myList);
	    	o.sortGivenArray();
	    	System.out.println("\nSorted List:");
	    	for (int i=0; i<SIZE; i++){
	    		System.out.print(" " + myList.get(i));
	    	}	
	    }
	    else { 
	    	Collections.sort(sortlist);
	        long start = System.nanoTime();
	        ListSortq<Integer> quickSort = new ListSortq<Integer>(myList);
		    ForkJoinPool pool = new ForkJoinPool();
		    pool.invoke(quickSort);
	        long end = System.nanoTime();
	        System.out.print("\nSorted List:");
		    for (int i=0; i<SIZE; i++){
		        System.out.print(" "+myList.get(i));
		        assertEquals(sortlist.get(i), myList.get(i));
		    }
		    System.out.println(String.format("\nTime taken: %f msec", (end - start) / 1000000.0));
	    }
    } 
	
	//Test Case-2:
	//Check with less than 100 numbers
	@Test
	void testTwo() {
	      final int SIZE = 50;
	      System.out.println("\n\nTest case-2:");
	        List<Integer> myList = new ArrayList<Integer>(SIZE);
	        System.out.print("\nUnsorted list:");
	        for (int i=0; i<SIZE; i++){
	            int value = (int) (Math.random() * 100);
	            myList.add(value);
	            System.out.print(" "+myList.get(i));
	        } 
	        List<Integer> sortlist=myList;
	        
	        if(SIZE<=100) {
	        	Collections.sort(sortlist);
	        	long start = System.nanoTime();
	        	SelectionSort o = new SelectionSort(myList);
	        	o.sortGivenArray();
	        	long end = System.nanoTime();
	        	System.out.print("\nSorted List:");
	        	for (int i=0; i<SIZE; i++){
	        		System.out.print(" " + myList.get(i));
	        	}
	        	System.out.println(String.format("\nTime taken: %f msec", (end - start) / 1000000.0));

	        }
	        else { 
	        	Collections.sort(sortlist);
	            long start = System.nanoTime();

	            ListSortq<Integer> quickSort = new ListSortq<Integer>(myList);
		        ForkJoinPool pool = new ForkJoinPool();
		        pool.invoke(quickSort);
	            long end = System.nanoTime();
                System.out.println("\nSorted list");
		        for (int i=0; i<SIZE; i++){
		            System.out.print(" "+ myList.get(i));
		            assertEquals(sortlist.get(i), myList.get(i));
		        }
		        System.out.println(String.format("\nTime taken: %f msec", (end - start) / 1000000.0));

	        }
	}
	
}