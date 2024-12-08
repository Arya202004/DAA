import java.util.Random;

public class MergeSort {

    // Method to perform merge sort
    public void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;

            // Recursively sort the left and right halves
            mergeSort(arr, left, middle);
            mergeSort(arr, middle + 1, right);

            // Merge the sorted halves
            merge(arr, left, middle, right);
        }
    }

    // Method to merge two sorted halves
    public void merge(int[] arr, int left, int middle, int right) {
        // Find the sizes of the two subarrays to be merged
        int n1 = middle - left + 1;
        int n2 = right - middle;

        // Create temporary arrays
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Copy data to temporary arrays
        for (int i = 0; i < n1; ++i) {
            L[i] = arr[left + i];
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = arr[middle + 1 + j];
        }

        // Merge the temporary arrays

        // Initial indexes of the first and second subarrays
        int i = 0, j = 0;

        // Initial index of the merged subarray
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy the remaining elements of L[], if any
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copy the remaining elements of R[], if any
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Method to fill the array with random numbers
    public void fillArrayWithRandomNumbers(int[] array) {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(1000000); // Random numbers between 0 and 999999
        }
    }

    public static void main(String[] args) {
        int arraySize = 300; // Number of elements
        int[] randomNumbers = new int[arraySize];
        
        MergeSort obj = new MergeSort();
        obj.fillArrayWithRandomNumbers(randomNumbers);

        long start = System.nanoTime();
        obj.mergeSort(randomNumbers, 0, randomNumbers.length - 1);
        long end = System.nanoTime();

        long elapsedTimeInNanoseconds = end - start;
        double elapsedTimeInMilliseconds = elapsedTimeInNanoseconds / 1_000_000.0;

        // Print the result and time taken
        System.out.println("Array sorted using merge sort.");
        System.out.println("Time taken for merge sort: " + elapsedTimeInMilliseconds + " milliseconds");
    }
}
