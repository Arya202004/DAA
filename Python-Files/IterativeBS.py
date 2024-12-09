class BinarySearchIterative:
    def search(self, arr, target):
        low, high = 0, len(arr) - 1
        while low <= high:
            mid = (low + high) // 2
            if arr[mid] == target:
                return mid
            elif arr[mid] < target:
                low = mid + 1
            else:
                high = mid - 1
        return -1


if __name__ == "__main__":
    array = [10, 20, 30, 40, 50, 60]
    target = 40
    searcher = BinarySearchIterative()
    result = searcher.search(array, target)
    
    if result != -1:
        print(f"Element {target} found at index {result}.")
    else:
        print(f"Element {target} not found in the array.")
