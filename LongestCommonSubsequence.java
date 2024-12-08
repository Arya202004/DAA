public class LongestCommonSubsequence {

    // Function to compute the LCS length using Dynamic Programming
    public static int lcsLength(String X, String Y) {
        int m = X.length();
        int n = Y.length();
        int[][] dp = new int[m + 1][n + 1];
        
        // Fill the dp table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n]; // Return the length of LCS
    }
    
    // Function to calculate time complexity in terms of number of operations
    public static long calculateTimeComplexity(int m, int n) {
        return (long) m * n;
    }

    public static void main(String[] args) {
        // Test cases: (m, n)
        int[][] testCases = {
            {100, 200},
            {500, 600},
            {1000, 2000},
            {5000, 6000},
            {10000, 20000},
            {50000, 60000},
            {100000, 200000}
        };

        for (int[] testCase : testCases) {
            int m = testCase[0];
            int n = testCase[1];

            // Generate sample strings of lengths m and n
            String X = "A".repeat(m); // String of length m
            String Y = "B".repeat(n); // String of length n

            // Measure the LCS computation and print the result
            long startTime = System.currentTimeMillis();
            int lcsLength = lcsLength(X, Y); // Calculate LCS length
            long endTime = System.currentTimeMillis();

            long elapsedTimeMs = endTime - startTime; // Time taken in milliseconds
            long timeComplexity = calculateTimeComplexity(m, n);

            System.out.println("For m = " + m + " and n = " + n);
            System.out.println("LCS Length: " + lcsLength);
            System.out.println("Time Taken (in milliseconds): " + elapsedTimeMs);
            System.out.println();
        }
    }
}
