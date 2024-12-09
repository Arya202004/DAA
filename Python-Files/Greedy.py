import heapq

class GreedyKnapsackMaxHeap:
    def knapsack(self, weights, values, capacity):
        # Calculate value-to-weight ratio and store in max heap
        max_heap = []
        for i in range(len(weights)):
            ratio = values[i] / weights[i]
            heapq.heappush(max_heap, (-ratio, weights[i], values[i]))

        total_value = 0
        while capacity > 0 and max_heap:
            ratio, weight, value = heapq.heappop(max_heap)
            weight_taken = min(weight, capacity)
            total_value += (-ratio) * weight_taken
            capacity -= weight_taken

        return total_value


if __name__ == "__main__":
    weights = [10, 20, 30]
    values = [60, 100, 120]
    capacity = 50

    knapsack_solver = GreedyKnapsackMaxHeap()
    max_value = knapsack_solver.knapsack(weights, values, capacity)
    print(f"Maximum value in the knapsack: {max_value}")
