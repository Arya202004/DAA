import random

class BubbleSort:
    def sort(self, data):
        n = len(data)
        for i in range(n - 1):
            for j in range(n - i - 1):
                if data[j] > data[j + 1]:
                    data[j], data[j + 1] = data[j + 1], data[j]

    def generate_random_numbers(self, size):
        return [random.randint(0, 999999) for _ in range(size)]

if __name__ == "__main__":
    size = 300
    sorter = BubbleSort()
    numbers = sorter.generate_random_numbers(size)
    sorter.sort(numbers)
    print("Array sorted using bubble sort.")
