import java.util.Random;

public class HeapSort {

    // Method to perform heap sort
    public void heapSort(int[] arr) {
        int n = arr.length;

        // Build a max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Extract elements from heap one by one
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    // Method to heapify a subtree rooted at index i
    void heapify(int[] arr, int n, int i) {
        int largest = i; // Initialize largest as root
        int left = 2 * i + 1; // Left child
        int right = 2 * i + 2; // Right child

        // If left child is larger than root
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        // If right child is larger than largest so far
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected subtree
            heapify(arr, n, largest);
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
        int arraySize = 300000; // Number of elements
        int[] randomNumbers = new int[arraySize];
        
        HeapSort obj = new HeapSort();
        obj.fillArrayWithRandomNumbers(randomNumbers);

        long start = System.nanoTime();
        obj.heapSort(randomNumbers);
        long end = System.nanoTime();

        long elapsedTimeInNanoseconds = end - start;
        double elapsedTimeInMilliseconds = elapsedTimeInNanoseconds / 1_000_000.0;

        // Print the result and time taken
        System.out.println("Array sorted using heap sort.");
        System.out.println("Time taken for heap sort: " + elapsedTimeInMilliseconds + " milliseconds");
    }
}
