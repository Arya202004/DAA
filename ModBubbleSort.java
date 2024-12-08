import java.util.Random;

public class ModBubbleSort {

    // Method to perform bubble sort with a swapped flag
    public void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap arr[j] and arr[j + 1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            // If no two elements were swapped in the inner loop, then break
            if (!swapped) {
                break;
            }
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
        
        BubbleSort obj = new BubbleSort();
        obj.fillArrayWithRandomNumbers(randomNumbers);

        long start = System.nanoTime();
        obj.bubbleSort(randomNumbers);
        long end = System.nanoTime();

        long elapsedTimeInNanoseconds = end - start;
        double elapsedTimeInMilliseconds = elapsedTimeInNanoseconds / 1_000_000.0;

        // Print the result and time taken
        System.out.println("Array sorted using bubble sort.");
        System.out.println("Time taken for bubble sort: " + elapsedTimeInMilliseconds + " milliseconds");
    }
}

