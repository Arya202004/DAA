import java.util.Random;

public class RandomizeQS {

    // Method to perform quick sort
    public void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // Partition the array using a randomized pivot
            int pi = randomizedPartition(arr, low, high);

            // Recursively sort elements before and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // Method to partition the array with a random pivot
    public int randomizedPartition(int[] arr, int low, int high) {
        // Pick a random index between low and high
        int randomPivotIndex = new Random().nextInt(high - low + 1) + low;

        // Swap the randomly chosen pivot element with the last element
        int temp = arr[randomPivotIndex];
        arr[randomPivotIndex] = arr[high];
        arr[high] = temp;

        // Proceed with the usual partition function
        return partition(arr, low, high);
    }

    // Standard partition method
    public int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; // Pivot element
        int i = (low - 1); // Index of the smaller element

        for (int j = low; j < high; j++) {
            // If the current element is smaller than the pivot
            if (arr[j] < pivot) {
                i++;

                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swap arr[i + 1] and arr[high] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1; // Return the partitioning index
    }

    // Method to fill the array with random numbers
    public void fillArrayWithRandomNumbers(int[] array) {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(1000000); // Random numbers between 0 and 999999
        }
    }

    public static void main(String[] args) {
        // Taking array size as input
        int arraySize = 1000000; // Number of elements
        int[] randomNumbers = new int[arraySize];
        
        QuickSort obj = new QuickSort();
        obj.fillArrayWithRandomNumbers(randomNumbers);

        long start = System.nanoTime();
        obj.quickSort(randomNumbers, 0, randomNumbers.length - 1);
        long end = System.nanoTime();

        long elapsedTimeInNanoseconds = end - start;
        double elapsedTimeInMilliseconds = elapsedTimeInNanoseconds / 1_000_000.0;

        // Print the result and time taken
        System.out.println("Array sorted using randomized quick sort.");
        System.out.println("Time taken for randomized quick sort: " + elapsedTimeInMilliseconds + " milliseconds");
    }
}
