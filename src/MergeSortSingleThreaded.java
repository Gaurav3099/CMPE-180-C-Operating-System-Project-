import java.util.ArrayList;

public class MergeSortSingleThreaded {
    private final ArrayList<Integer> arrayToSort;

    public MergeSortSingleThreaded(ArrayList<Integer> arrayToSort) {
        this.arrayToSort = arrayToSort;
    }

    public ArrayList<Integer> getArrayAfterSorting() {
        return arrayToSort;
    }

    public void divideArrayElements(int indexStart, int indexEnd) {

        if (indexStart < indexEnd && (indexEnd - indexStart) >= 1) {
            int middleElement = (indexEnd + indexStart) / 2;

            divideArrayElements(indexStart, middleElement);
            divideArrayElements(middleElement + 1, indexEnd);

            mergeArrayElements(indexStart, middleElement, indexEnd);
        }
    }

    public void mergeArrayElements(int indexStart, int indexMiddle, int indexEnd) {

        ArrayList<Integer> tempArray = new ArrayList<>();

        int getLeftIndex = indexStart;
        int getRightIndex = indexMiddle + 1;

        while (getLeftIndex <= indexMiddle && getRightIndex <= indexEnd) {

            if (arrayToSort.get(getLeftIndex) <= arrayToSort.get(getRightIndex)) {

                tempArray.add(arrayToSort.get(getLeftIndex));
                getLeftIndex++;

            } else {

                tempArray.add(arrayToSort.get(getRightIndex));
                getRightIndex++;

            }
        }

        while (getLeftIndex <= indexMiddle) {
            tempArray.add(arrayToSort.get(getLeftIndex));
            getLeftIndex++;
        }

        while (getRightIndex <= indexEnd) {
            tempArray.add(arrayToSort.get(getRightIndex));
            getRightIndex++;
        }


        for (int i = 0; i < tempArray.size(); indexStart++) {
            arrayToSort.set(indexStart, tempArray.get(i++));

        }

    }

    public static void main(String[] args) {
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        int SIZE = 100000;
        long start = System.nanoTime();

        for (int i = 0; i < SIZE; i++) {
            int value = (int) (Math.random() * SIZE);
            integerArrayList.add(value);
        }

        MergeSortSingleThreaded exampleClass1 = new MergeSortSingleThreaded(integerArrayList);

        System.out.println("\nUnsorted List:");
        for (Integer integer : exampleClass1.getArrayAfterSorting()) {
            System.out.print(" "+integer);
        }

        System.out.println();

        exampleClass1.divideArrayElements(0, integerArrayList.size() - 1);
        long end = System.nanoTime();

        System.out.println("\nSorted List:");
        for (Integer integer : exampleClass1.getArrayAfterSorting()) {
            System.out.print(" "+integer);
        }
        System.out.println(String.format("\nTime taken: %f msec", (end - start) / 1000000.0));


    }
}
