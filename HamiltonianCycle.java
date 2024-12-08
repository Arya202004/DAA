import java.util.Random;

public class HamiltonianCycle {

    // Method to check for Hamiltonian Cycle
    public boolean isHamiltonianCycle(int[][] graph, int[] path, int pos) {
        // If all vertices are included in the path, check if there is an edge from the
        // last vertex in path back to the first vertex
        if (pos == graph.length) {
            return graph[path[pos - 1]][path[0]] == 1;
        }

        // Try different vertices as the next candidate in the Hamiltonian Cycle
        for (int v = 1; v < graph.length; v++) {
            // Check if this vertex can be added to Hamiltonian Cycle
            if (isSafe(v, graph, path, pos)) {
                path[pos] = v;

                // Recur to construct rest of the path
                if (isHamiltonianCycle(graph, path, pos + 1)) {
                    return true;
                }

                // If adding vertex v doesn't lead to a solution, remove it
                path[pos] = -1;
            }
        }

        return false;
    }

    // Method to check if the vertex v can be added at index pos in the Hamiltonian Cycle
    public boolean isSafe(int v, int[][] graph, int[] path, int pos) {
        // Check if this vertex is adjacent to the previous vertex
        if (graph[path[pos - 1]][v] == 0) {
            return false;
        }

        // Check if the vertex has already been included in the path
        for (int i = 0; i < pos; i++) {
            if (path[i] == v) {
                return false;
            }
        }

        return true;
    }

    // Method to initialize path array and start the Hamiltonian Cycle check
    public boolean hasHamiltonianCycle(int[][] graph) {
        int[] path = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            path[i] = -1;
        }

        // Let the first vertex in the path be 0
        path[0] = 0;
        return isHamiltonianCycle(graph, path, 1);
    }

    // Method to generate a random graph (adjacency matrix)
    public int[][] generateRandomGraph(int size) {
        Random random = new Random();
        int[][] graph = new int[size][size];

        // Fill the adjacency matrix randomly
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                int edge = random.nextInt(2); // Randomly 0 or 1
                graph[i][j] = edge;
                graph[j][i] = edge; // Make it symmetric (undirected graph)
            }
        }

        return graph;
    }

    public static void main(String[] args) {
        // Taking graph size as input
        int graphSize = 8000; // Number of vertices

        HamiltonianCycle obj = new HamiltonianCycle();
        int[][] graph = obj.generateRandomGraph(graphSize);

        long start = System.nanoTime();
        boolean hasCycle = obj.hasHamiltonianCycle(graph);
        long end = System.nanoTime();

        long elapsedTimeInNanoseconds = end - start;
        double elapsedTimeInMilliseconds = elapsedTimeInNanoseconds / 1_000_000.0;

        // Print the result and time taken
        if (hasCycle) {
            System.out.println("Hamiltonian cycle exists in the graph.");
        } else {
            System.out.println("Hamiltonian cycle does not exist in the graph.");
        }
        System.out.println("Time taken for Hamiltonian cycle check: " + elapsedTimeInMilliseconds + " milliseconds");
    }
}

