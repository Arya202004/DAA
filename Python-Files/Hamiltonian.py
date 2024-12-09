class HamiltonianCycle:
    def is_safe(self, graph, path, pos, v):
        # Check if this vertex is an adjacent vertex of the previously added vertex.
        if graph[path[pos - 1]][v] == 0:
            return False
        
        # Check if the vertex has already been included in the path
        if v in path:
            return False
        
        return True

    def hamiltonian_cycle_util(self, graph, path, pos):
        # Base case: If all vertices are included in the path
        if pos == len(graph):
            # Check if there is an edge from the last vertex to the first vertex
            if graph[path[pos - 1]][path[0]] == 1:
                return True
            return False

        # Try different vertices as the next candidate in the Hamiltonian Cycle.
        for v in range(1, len(graph)):
            if self.is_safe(graph, path, pos, v):
                path[pos] = v

                # Recur to construct the rest of the path
                if self.hamiltonian_cycle_util(graph, path, pos + 1):
                    return True

                # Backtrack if adding vertex v doesn't lead to a solution
                path[pos] = -1

        return False

    def find_hamiltonian_cycle(self, graph):
        path = [-1] * len(graph)

        # Start the cycle from vertex 0
        path[0] = 0

        if not self.hamiltonian_cycle_util(graph, path, 1):
            return None

        return path


if __name__ == "__main__":
    graph = [
        [0, 1, 0, 1],
        [1, 0, 1, 1],
        [0, 1, 0, 1],
        [1, 1, 1, 0]
    ]

    hamiltonian_solver = HamiltonianCycle()
    cycle = hamiltonian_solver.find_hamiltonian_cycle(graph)

    if cycle is None:
        print("No Hamiltonian Cycle found")
    else:
        print(f"Hamiltonian Cycle found: {cycle}")
