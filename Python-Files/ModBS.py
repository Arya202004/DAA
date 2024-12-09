class ModifiedBubbleSort:
    def sort(self, arr):
        n = len(arr)
        for i in range(n - 1):
            swapped = False
            for j in range(n - i - 1):
                if arr[j] > arr[j + 1]:
                    arr[j], arr[j + 1] = arr[j + 1], arr[j]
                    swapped = True
            if not swapped:
                break


if __name__ == "__main__":
    array = [64, 34, 25, 12, 22, 11, 90]
    sorter = ModifiedBubbleSort()
    sorter.sort(array)
    print("Sorted array using Modified Bubble Sort:", array)
