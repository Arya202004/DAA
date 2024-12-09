class SortAlgorithms:
    INSERTION_SORT_THRESHOLD = 10

    def quick_sort(self, arr):
        self._quick_sort_iterative(arr, 0, len(arr) - 1)

    def _quick_sort_iterative(self, arr, low, high):
        stack = [(low, high)]
        while stack:
            low, high = stack.pop()
            if high - low < self.INSERTION_SORT_THRESHOLD:
                self._insertion_sort(arr, low, high)
                continue

            pivot_index = self._partition(arr, low, high)
            if pivot_index - 1 > low:
                stack.append((low, pivot_index - 1))
            if pivot_index < high:
                stack.append((pivot_index, high))

    def _partition(self, arr, low, high):
        pivot = arr[low + (high - low) // 2]
        i, j = low, high
        while i <= j:
            while arr[i] < pivot:
                i += 1
            while arr[j] > pivot:
                j -= 1
            if i <= j:
                arr[i], arr[j] = arr[j], arr[i]
                i += 1
                j -= 1
        return i

    def _insertion_sort(self, arr, low, high):
        for i in range(low + 1, high + 1):
            key = arr[i]
            j = i - 1
            while j >= low and arr[j] > key:
                arr[j + 1] = arr[j]
                j -= 1
            arr[j + 1] = key

    def merge_sort(self, arr):
        n = len(arr)
        temp = [0] * n
        size = 1
        while size < n:
            for left_start in range(0, n, 2 * size):
                mid = min(left_start + size - 1, n - 1)
                right_end = min(left_start + 2 * size - 1, n - 1)
                self._merge(arr, temp, left_start, mid, right_end)
            size *= 2

    def _merge(self, arr, temp, left_start, mid, right_end):
        i, j, k = left_start, mid + 1, left_start
        while i <= mid and j <= right_end:
            if arr[i] <= arr[j]:
                temp[k] = arr[i]
                i += 1
            else:
                temp[k] = arr[j]
                j += 1
            k += 1

        while i <= mid:
            temp[k] = arr[i]
            i += 1
            k += 1

        while j <= right_end:
            temp[k] = arr[j]
            j += 1
            k += 1

        for i in range(left_start, right_end + 1):
            arr[i] = temp[i]

    def generate_sorted_numbers(self, size):
        return list(range(size))


if __name__ == "__main__":
    array_sizes = [1000, 5000, 10000, 25000, 50000, 75000, 100000, 1000000, 5000000]
    sorter = SortAlgorithms()

    for size in array_sizes:
        sorted_array = sorter.generate_sorted_numbers(size)

        quick_sort_array = sorted_array[:]
        sorter.quick_sort(quick_sort_array)
        print(f"Array of size {size} sorted using Quick Sort.")

        merge_sort_array = sorted_array[:]
        sorter.merge_sort(merge_sort_array)
        print(f"Array of size {size} sorted using Merge Sort.")
