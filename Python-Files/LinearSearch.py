class LinearSearch:
    def search(self, arr, target):
        for index, value in enumerate(arr):
            if value == target:
                return index
        return -1


if __name__ == "__main__":
    array = [10, 20, 30, 40, 50, 60]
    target = 40
    searcher = LinearSearch()
    result = searcher.search(array, target)
    
    if result != -1:
        print(f"Element {target} found at index {result}.")
    else:
        print(f"Element {target} not found in the array.")
