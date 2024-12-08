import java.util.Random;

public class LinearSearch {

    // Method to perform linear search
    public int linearSearch(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

    // Method to fill the array with random numbers
    public void fillArrayWithRandomNumbers(int[] array) {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(10000); // Random numbers between 0 and 9999
        }
    }

    public static void main(String[] args) {
        int arraySize = 5000000; // Size of the array
        int[] randomNumbers = new int[arraySize];

        LinearSearch obj = new LinearSearch();
        obj.fillArrayWithRandomNumbers(randomNumbers);

        int target = 5000; // Value to search for, can be modified for testing

        long start = System.nanoTime();
        int result = obj.linearSearch(randomNumbers, target);
        long end = System.nanoTime();

        long elapsedTimeInNanoseconds = end - start;
        double elapsedTimeInMilliseconds = elapsedTimeInNanoseconds / 1_000_000.0;

        if (result != -1) {
            System.out.println("Element found at index: " + result);
        } else {
            System.out.println("Element not found in the array.");
        }

        System.out.println("Elapsed Time in milliseconds: " + elapsedTimeInMilliseconds);
    }
}
