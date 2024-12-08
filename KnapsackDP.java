import java.util.Random;
import java.util.Scanner;

public class KnapsackDP {

    // Method to solve 0/1 Knapsack problem using Dynamic Programming
    public int knapsackDP(int[] profits, int[] weights, int capacity) {
        int n = profits.length;

        // DP table where dp[i][w] is the maximum profit using the first i items with capacity w
        int[][] dp = new int[n + 1][capacity + 1];

        // Build table dp[][] in a bottom-up manner
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                // If including item i is possible (i.e., its weight is less than the current capacity)
                if (weights[i - 1] <= w) {
                    // Take the maximum of including or not including the current item
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - weights[i - 1]] + profits[i - 1]);
                } else {
                    // Otherwise, just carry forward the previous item's value
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // Return the maximum profit we can obtain with the given capacity
        return dp[n][capacity];
    }

    // Method to generate random profits and weights
    public void generateProfitAndWeightArrays(int[] profits, int[] weights, int maxProfit, int maxWeight) {
        Random random = new Random();
        for (int i = 0; i < profits.length; i++) {
            profits[i] = random.nextInt(maxProfit) + 1; // Random profit between 1 and maxProfit
            weights[i] = random.nextInt(maxWeight) + 1; // Random weight between 1 and maxWeight
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Taking input from the user for the number of items and capacity
        System.out.print("Enter number of items: ");
        int n = scanner.nextInt();

        System.out.print("Enter capacity of the knapsack: ");
        int capacity = scanner.nextInt();

        // Define the range for random profits and weights
        int maxProfit = 100;  // Maximum possible profit value for an item
        int maxWeight = 50;   // Maximum possible weight for an item

        int[] profits = new int[n];
        int[] weights = new int[n];

        // Create an instance of KnapsackDP and generate random profits and weights
        KnapsackDP knapsackSolver = new KnapsackDP();
        knapsackSolver.generateProfitAndWeightArrays(profits, weights, maxProfit, maxWeight);

        // Solve the 0/1 Knapsack problem using dynamic programming
        long start = System.nanoTime();
        int maxProfitAchieved = knapsackSolver.knapsackDP(profits, weights, capacity);
        long end = System.nanoTime();

        long elapsedTimeInNanoseconds = end - start;
        double elapsedTimeInSeconds = elapsedTimeInNanoseconds / 1_000_000_000.0;

        // Print the result and time taken
        System.out.println("Maximum profit obtained using the 0/1 knapsack approach: " + maxProfitAchieved);
        System.out.println("Time taken for 0/1 knapsack (Dynamic Programming): " + elapsedTimeInSeconds + " seconds");
    }
}
