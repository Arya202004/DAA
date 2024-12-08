import java.util.Arrays;
import java.util.Random;

public class RecursiveBinarySearch {

    // Recursive method to perform binary search
    public int recursiveBinarySearch(int[] arr, int low, int high, int x) {
        if (low <= high) {
            int mid = low + (high - low) / 2;

            // If x is greater, ignore left half
            if (arr[mid] < x) {
                return recursiveBinarySearch(arr, mid + 1, high, x);
            }

            // If x is smaller, ignore right half
            else if (arr[mid] > x) {
                return recursiveBinarySearch(arr, low, mid - 1, x);
            }

            // x is present at mid
            else {
                return mid;
            }
        }

        // x is not present in arr
        return -1;
    }

    // Method to fill the array with random numbers
    public void fillArrayWithRandomNumbers(int[] array) {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(1000000); // Random numbers between 0 and 999999
        }
    }

    public static void main(String[] args) {
        int arraySize = 5000000; // Number of elements
        int[] randomNumbers = new int[arraySize];
        
        RecursiveBinarySearch obj = new RecursiveBinarySearch();
        obj.fillArrayWithRandomNumbers(randomNumbers);

        // Binary search requires a sorted array
        Arrays.sort(randomNumbers);

        int target = new Random().nextInt(1000000); // Random integer to search for

        long start = System.nanoTime();
        int result = obj.recursiveBinarySearch(randomNumbers, 0, randomNumbers.length - 1, target);
        long end = System.nanoTime();

        long elapsedTimeInNanoseconds = end - start;
        double elapsedTimeInMilliseconds = elapsedTimeInNanoseconds / 1_000_000.0;

        // Print the result and time taken
        if (result != -1) {
            System.out.println("Element found at index: " + result);
        } else {
            System.out.println("Element not found in array.");
        }

        System.out.println("Time taken for recursive binary search: " + elapsedTimeInMilliseconds + " milliseconds");
    }
}
