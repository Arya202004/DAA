class QuickSort:
    def sort(self, arr):
        self._quick_sort_iterative(arr, 0, len(arr) - 1)

    def _quick_sort_iterative(self, arr, low, high):
        stack = [(low, high)]
        while stack:
            low, high = stack.pop()
            if low < high:
                pivot_index = self._partition(arr, low, high)
                stack.append((low, pivot_index - 1))
                stack.append((pivot_index + 1, high))

    def _partition(self, arr, low, high):
        pivot = arr[high]
        i = low - 1
        for j in range(low, high):
            if arr[j] < pivot:
                i += 1
                arr[i], arr[j] = arr[j], arr[i]
        arr[i + 1], arr[high] = arr[high], arr[i + 1]
        return i + 1


if __name__ == "__main__":
    array = [38, 27, 43, 3, 9, 82, 10]
    sorter = QuickSort()
    sorter.sort(array)
    print("Sorted array using Quick Sort:", array)
