import java.util.PriorityQueue;
import java.util.Random;

public class Greedy {

    // Class to represent items with profit-to-weight ratio
    static class Item {
        int profit;
        int weight;
        double ratio;

        Item(int profit, int weight) {
            this.profit = profit;
            this.weight = weight;
            this.ratio = (double) profit / weight;
        }
    }

    // Method to generate random profits and weights
    public void generateProfitAndWeightArrays(int[] profits, int[] weights, int maxProfit, int maxWeight) {
        Random random = new Random();
        for (int i = 0; i < profits.length; i++) {
            profits[i] = random.nextInt(maxProfit) + 1; // Random profit between 1 and maxProfit
            weights[i] = random.nextInt(maxWeight) + 1; // Random weight between 1 and maxWeight
        }
    }

    // Method to perform the greedy knapsack using max heap
    public double knapsack(int[] profits, int[] weights, int capacity) {
        int n = profits.length;

        // Create a max heap based on the profit-to-weight ratio
        PriorityQueue<Item> maxHeap = new PriorityQueue<>((a, b) -> Double.compare(b.ratio, a.ratio));

        // Add items to the heap
        for (int i = 0; i < n; i++) {
            maxHeap.add(new Item(profits[i], weights[i]));
        }

        double totalProfit = 0.0;
        int currentCapacity = 0;

        // Extract items from the heap until the knapsack is full
        while (!maxHeap.isEmpty() && currentCapacity < capacity) {
            Item currentItem = maxHeap.poll();

            // If adding the full item won't exceed capacity
            if (currentCapacity + currentItem.weight <= capacity) {
                totalProfit += currentItem.profit;
                currentCapacity += currentItem.weight;
            } else {
                // If we can't add the full item, add the fractional part
                int remainingCapacity = capacity - currentCapacity;
                totalProfit += currentItem.ratio * remainingCapacity;
                currentCapacity += remainingCapacity;
                break; // The knapsack is now full
            }
        }

        return totalProfit;
    }

    public static void main(String[] args) {
        int n = 2000000; // Number of items
        int maxProfit = 1000; // Maximum possible profit value for an item
        int maxWeight = 50; // Maximum possible weight for an item
        int capacity = 500; // Maximum weight capacity of the knapsack

        int[] profits = new int[n];
        int[] weights = new int[n];

        Greedy knapsackSolver = new Greedy();
        knapsackSolver.generateProfitAndWeightArrays(profits, weights, maxProfit, maxWeight);

        long start = System.nanoTime();
        double maxProfitAchieved = knapsackSolver.knapsack(profits, weights, capacity);
        long end = System.nanoTime();

        long elapsedTimeInNanoseconds = end - start;
        double elapsedTimeInMilliseconds = elapsedTimeInNanoseconds / 1_000_000.0;

        // Print the result and time taken
        System.out.println("Maximum profit obtained using the greedy knapsack approach: " + maxProfitAchieved);
        System.out.println("Time taken for greedy knapsack: " + elapsedTimeInMilliseconds + " milliseconds");
    }
}

