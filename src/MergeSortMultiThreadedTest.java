import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.ForkJoinPool;
import java.util.*;

import org.junit.jupiter.api.Test;

class MergeSortMultiThreadedTest {
    
	//Test Case-1:
	//Check with more than 100 positive numbers
	@Test
	void test1() {
		System.out.println("\nTestCase-1: "); 
		int size = 200;
	    ArrayList<Integer> list = new ArrayList<>();//(Arrays.asList(1,3,5,7,2,6,3));

	    System.out.print("\nUnsorted list: ");
	    for (int i = 0; i < size; i++) {
	        int value = (int) (Math.random() * size);
	        list.add(value);
	        System.out.print(" " +list.get(i));
	    } 
	    System.out.println(" ");
	    ArrayList<Integer> slist=list;
	    if (size <= 100) {
            SelectionSort o = new SelectionSort(list);
            o.sortGivenArray();
            System.out.println("\nSorted list: ");
            for (int i = 0; i < size; i++) {
                System.out.print(" "+list.get(i));
                assertEquals(slist.get(i),list.get(i));
            }
        }
	    else { 
		    ListSort<Integer> ob = new ListSort<Integer>(list);
		    ForkJoinPool forkJoinPool = new ForkJoinPool();
	        forkJoinPool.invoke(new ListSort<Integer>(list, 0, list.size() - 1));
	        ArrayList<Integer> arraySorted= ob.getArrayAfterSorting();
	        Collections.sort(slist);
	        System.out.print("\nSorted list: ");
	        for(int i=0;i<arraySorted.size();i++) {
	        	System.out.print(arraySorted.get(i)+" ");
	        	assertEquals(slist.get(i),arraySorted.get(i));
	        }  
	    }
    } 

	
	//Test Case-2: 
	//Check with more than 100 integers
	@Test
	void testtwo() {
		System.out.println("\nTestCase-2: "); 
	    int SIZE = 200;
        ArrayList<Integer> myList = new ArrayList<>();//(Arrays.asList(-1,0,-9,7,2,6,3));
        
        myList.add(-1);
        myList.add(0);
        System.out.print("\nUnsorted list: ");
        for (int i = 0; i < SIZE-2; i++) {
            int value = (int) (Math.random() * SIZE);
            myList.add(value);
            System.out.print(" " +myList.get(i));
        } 
        
        
        System.out.println(" ");
        ArrayList<Integer> slist=myList;

        ListSort<Integer> ob = new ListSort<Integer>(myList);
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        forkJoinPool.invoke(new ListSort<Integer>(myList, 0, myList.size() - 1));
        
        //assertEquals(3,3);
        ArrayList<Integer> arraySorted= ob.getArrayAfterSorting();
        Collections.sort(slist);
        System.out.print("\nSorted list: ");
        for(int i=0;i<arraySorted.size();i++) {
        	
        	 System.out.print(arraySorted.get(i)+" ");
        	assertEquals(slist.get(i),arraySorted.get(i));
        }
	}
	
	//Test Case-3: 
	//Check with less than 100 integers
	@Test
	void testthree() {
		System.out.println("\nTestCase-3: "); 
	    int SIZE = 10;
        ArrayList<Integer> myList = new ArrayList<>();//(Arrays.asList(-1,0,-9,7,2,6,3));
        myList.add(-9);
        myList.add(-2);
        System.out.print("\nUnsorted list: ");
        for (int i = 0; i < SIZE; i++) {
            int value = (int) (Math.random() * SIZE);
            myList.add(value);
            System.out.print(" " +myList.get(i));
        } 
        
        System.out.println(" ");
        ArrayList<Integer> slist=myList;

        ListSort<Integer> ob = new ListSort<Integer>(myList);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(new ListSort<Integer>(myList, 0, myList.size() - 1));
        ArrayList<Integer> arraySorted= ob.getArrayAfterSorting();
        Collections.sort(slist);
        System.out.print("\nSorted list: ");
        for(int i=0;i<arraySorted.size();i++) {
        	
        	System.out.print(arraySorted.get(i)+" ");
        	assertEquals(slist.get(i),arraySorted.get(i));
        }
	}
}