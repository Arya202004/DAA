class BinarySearchRecursive:
    def search(self, arr, target):
        return self._binary_search(arr, target, 0, len(arr) - 1)

    def _binary_search(self, arr, target, low, high):
        if low > high:
            return -1
        mid = (low + high) // 2
        if arr[mid] == target:
            return mid
        elif arr[mid] < target:
            return self._binary_search(arr, target, mid + 1, high)
        else:
            return self._binary_search(arr, target, low, mid - 1)


if __name__ == "__main__":
    array = [10, 20, 30, 40, 50, 60]
    target = 40
    searcher = BinarySearchRecursive()
    result = searcher.search(array, target)
    
    if result != -1:
        print(f"Element {target} found at index {result}.")
    else:
        print(f"Element {target} not found in the array.")
