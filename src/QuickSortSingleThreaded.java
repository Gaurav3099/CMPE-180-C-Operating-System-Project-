import java.io.*;

class QuickSortSingleThreaded{
	
	static void swap(int[] arr, int i, int j)
	{
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	
	static int partition(int[] arr, int low, int high)
	{
		
		int pivot = arr[high];
		int i = (low - 1);
		for(int j = low; j <= high - 1; j++)
		{
			if (arr[j] < pivot)
			{
				i++;
				swap(arr, i, j);
			}
		}
		swap(arr, i + 1, high);
		return (i + 1);
	}
	
	static void quickSort(int[] arr, int low, int high)
	{
		if (low < high)
		{
			int pi = partition(arr, low, high);
	
			quickSort(arr, low, pi - 1);
			quickSort(arr, pi + 1, high);
		}
	}
	
	static void printArray(int[] arr, int size)
	{
		for(int i = 0; i < size; i++)
			System.out.print(arr[i] + " ");
			
		System.out.println();
	}
	
	public static void main(String[] args)
	{
		int SIZE = 100000;
	
		int[] arr = new int[SIZE] ;
		for (int i = 0; i < SIZE; i++) {
	        int value = (int) (Math.random() * 500);
	        arr[i] = value;
	    }
		int n = arr.length;
	    long start = System.nanoTime();
	
		quickSort(arr, 0, n - 1);
	    long end = System.nanoTime();
	
		System.out.println("Sorted array: ");
		printArray(arr, n);
		System.out.println(String.format("\nTime taken: %f msec", (end - start) / 1000000.0));
	
	}
}

