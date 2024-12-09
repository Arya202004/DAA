class HeapSort:
    def sort(self, arr):
        n = len(arr)
        for i in range(n // 2 - 1, -1, -1):
            self._heapify(arr, n, i)

        for i in range(n - 1, 0, -1):
            arr[i], arr[0] = arr[0], arr[i]
            self._heapify(arr, i, 0)

    def _heapify(self, arr, n, i):
        largest = i
        left = 2 * i + 1
        right = 2 * i + 2

        if left < n and arr[left] > arr[largest]:
            largest = left

        if right < n and arr[right] > arr[largest]:
            largest = right

        if largest != i:
            arr[i], arr[largest] = arr[largest], arr[i]
            self._heapify(arr, n, largest)


if __name__ == "__main__":
    array = [38, 27, 43, 3, 9, 82, 10]
    sorter = HeapSort()
    sorter.sort(array)
    print("Sorted array using Heap Sort:", array)
