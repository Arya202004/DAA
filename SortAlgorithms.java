public class SortAlgorithms {

    private static final int INSERTION_SORT_THRESHOLD = 10; // Threshold for switching to insertion sort

    // Method to perform quick sort with hybrid approach
    public void quickSort(int[] arr) {
        quickSortIterative(arr, 0, arr.length - 1);
    }

    private void quickSortIterative(int[] arr, int low, int high) {
        int[] stack = new int[high - low + 1];
        int top = -1;
        stack[++top] = low;
        stack[++top] = high;

        while (top >= 0) {
            high = stack[top--];
            low = stack[top--];

            if (high - low < INSERTION_SORT_THRESHOLD) {
                insertionSort(arr, low, high);
                continue;
            }

            int pi = partition(arr, low, high);

            if (pi - 1 > low) {
                stack[++top] = low;
                stack[++top] = pi - 1;
            }

            if (pi + 1 < high) {
                stack[++top] = pi + 1;
                stack[++top] = high;
            }
        }
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[low + (high - low) / 2]; // Median-of-three or random pivot selection
        int i = low;
        int j = high;

        while (i <= j) {
            while (arr[i] < pivot) {
                i++;
            }
            while (arr[j] > pivot) {
                j--;
            }
            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        return i;
    }

    private void insertionSort(int[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= low && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // Iterative version of merge sort
    public void mergeSort(int[] arr) {
        int n = arr.length;
        int[] temp = new int[n];
        for (int currSize = 1; currSize < n; currSize *= 2) {
            for (int leftStart = 0; leftStart < n - 1; leftStart += 2 * currSize) {
                int mid = Math.min(leftStart + currSize - 1, n - 1);
                int rightEnd = Math.min(leftStart + 2 * currSize - 1, n - 1);
                merge(arr, temp, leftStart, mid, rightEnd);
            }
        }
    }

    private void merge(int[] arr, int[] temp, int leftStart, int mid, int rightEnd) {
        int i = leftStart, j = mid + 1, k = leftStart;

        while (i <= mid && j <= rightEnd) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= rightEnd) {
            temp[k++] = arr[j++];
        }
        for (i = leftStart; i <= rightEnd; i++) {
            arr[i] = temp[i];
        }
    }

    // Method to fill the array with sorted numbers
    public void fillArrayWithSortedNumbers(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = i; // Fill array with sorted sequence [0, 1, 2, ..., array.length - 1]
        }
    }

    public static void main(String[] args) {
        int[] arraySizes = {1000, 5000, 10000, 25000, 50000, 75000, 100000, 1000000, 5000000, 10000000, 15000000, 20000000, 25000000, 30000000, 35000000, 40000000, 45000000, 50000000}; // Different input sizes

        SortAlgorithms obj = new SortAlgorithms();

        for (int size : arraySizes) {
            int[] sortedNumbers = new int[size];
            obj.fillArrayWithSortedNumbers(sortedNumbers);

            // Test Quick Sort
            int[] quickSortArray = sortedNumbers.clone();
            long start = System.nanoTime();
            obj.quickSort(quickSortArray);
            long end = System.nanoTime();
            long elapsedTimeInNanoseconds = end - start;
            double elapsedTimeInMilliseconds = elapsedTimeInNanoseconds / 1_000_000.0;
            System.out.println("Array size: " + size);
            System.out.println("Time taken for quick sort: " + elapsedTimeInMilliseconds + " milliseconds");

            // Test Merge Sort
            int[] mergeSortArray = sortedNumbers.clone();
            start = System.nanoTime();
            obj.mergeSort(mergeSortArray);
            end = System.nanoTime();
            elapsedTimeInNanoseconds = end - start;
            elapsedTimeInMilliseconds = elapsedTimeInNanoseconds / 1_000_000.0;
            System.out.println("Time taken for merge sort: " + elapsedTimeInMilliseconds + " milliseconds");
            System.out.println();
        }
    }
}
