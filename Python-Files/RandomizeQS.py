import random

class RandomizedQuickSort:
    def quick_sort(self, arr):
        self.randomized_quick_sort(arr, 0, len(arr) - 1)

    def randomized_quick_sort(self, arr, low, high):
        if low < high:
            pivot_index = self.randomized_partition(arr, low, high)
            self.randomized_quick_sort(arr, low, pivot_index - 1)
            self.randomized_quick_sort(arr, pivot_index + 1, high)

    def randomized_partition(self, arr, low, high):
        pivot_index = random.randint(low, high)  # Random pivot index
        arr[high], arr[pivot_index] = arr[pivot_index], arr[high]  # Swap pivot to the end
        return self.partition(arr, low, high)

    def partition(self, arr, low, high):
        pivot = arr[high]  # Last element is the pivot
        i = low - 1
        for j in range(low, high):
            if arr[j] <= pivot:
                i += 1
                arr[i], arr[j] = arr[j], arr[i]  # Swap smaller element to the front
        arr[i + 1], arr[high] = arr[high], arr[i + 1]  # Place the pivot in the correct position
        return i + 1


if __name__ == "__main__":
    arr = [10, 80, 30, 90, 40, 50, 70]
    
    randomized_sort = RandomizedQuickSort()
    print("Original array:", arr)
    randomized_sort.quick_sort(arr)
    print("Sorted array:", arr)
