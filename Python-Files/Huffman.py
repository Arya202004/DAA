import heapq

class HuffmanNode:
    def __init__(self, char, freq):
        self.char = char
        self.freq = freq
        self.left = None
        self.right = None

    def __lt__(self, other):
        return self.freq < other.freq


class HuffmanCoding:
    def build_huffman_tree(self, char_freq):
        min_heap = [HuffmanNode(char, freq) for char, freq in char_freq.items()]
        heapq.heapify(min_heap)

        while len(min_heap) > 1:
            left = heapq.heappop(min_heap)
            right = heapq.heappop(min_heap)

            merged = HuffmanNode(None, left.freq + right.freq)
            merged.left = left
            merged.right = right
            heapq.heappush(min_heap, merged)

        return min_heap[0]

    def generate_codes(self, root):
        def _generate_codes(node, current_code):
            if node is None:
                return
            if node.char is not None:
                codes[node.char] = current_code
                return
            _generate_codes(node.left, current_code + "0")
            _generate_codes(node.right, current_code + "1")

        codes = {}
        _generate_codes(root, "")
        return codes


if __name__ == "__main__":
    char_freq = {
        'a': 5,
        'b': 9,
        'c': 12,
        'd': 13,
        'e': 16,
        'f': 45
    }

    huffman = HuffmanCoding()
    huffman_tree_root = huffman.build_huffman_tree(char_freq)
    huffman_codes = huffman.generate_codes(huffman_tree_root)

    print("Character with their respective Huffman Codes:")
    for char, code in huffman_codes.items():
        print(f"{char}: {code}")
