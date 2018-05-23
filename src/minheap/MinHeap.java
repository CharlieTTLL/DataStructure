package minheap;

import java.util.NoSuchElementException;

public class MinHeap {
	private static final double CONST = 1.5;
	private int[] array;
	private int size;
	public MinHeap(int[] array) {
		if (array == null || array.length == 0) {
			throw new IllegalArgumentException("array cannot be empty or null");
		}
		this.array = array;
		size = array.length;
		heapify();
	}
	private void heapify() {
		for (int i = 0; i <= size / 2 - 1; i++) {
			percolateDown(i);
		}
	}
	private void copy(int[] newer, int[] older) {
		for (int i = 0; i < older.length; i++) {
			newer[i] = older[i];
		}
	}
	public int offer(int value) {
		if (array.length == size) {
			int[] newArray = new int[(new Double(size * CONST)).intValue()];
			copy(newArray, array);
			//change array reference
			array = newArray;
		}
		array[size] = value;
		size++;
		percolateUp(size - 1);
		return value;
	}
	public int poll() {
		if (size == 0) {
			throw new NoSuchElementException("heap is empty");
		}
		int result = array[0];
		array[0] = array[size - 1];
		size--;
		percolateDown(0);
		return result;
	}
	public int set(int index, int value) {
		if (index < 0 || index > size - 1) {
			throw new ArrayIndexOutOfBoundsException("invalid index range");
		}
		int result = array[index];
		array[index] = value;
		if (result > value) {
			percolateUp(index);
		} else {
			percolateDown(index);
		}
		return result;
	}
	public int peek() {
		if (size == 0) {
			return -1;
		}
		return array[0];
	}
	public boolean isEmpty() {
		return size == 0;
	}
	public boolean isFull() {
		return size == array.length;
	}
	public int size() {
		return size;
	}
	private void percolateUp(int index) {
		while (index > 0) {
			int parentIndex = (index - 1) / 2;
			if (array[index] < array[parentIndex]) {
				swap(array, index, parentIndex);
			} else {
				break;
			}
			index = parentIndex;
		}
	}
	private void percolateDown(int index) {
		while (index <= size / 2 - 1) {
			int leftChildIndex = index * 2 + 1;
			int rightChildIndex = index * 2 + 2;
			int smallestIndex = leftChildIndex;
			if (rightChildIndex <= size - 1 && array[rightChildIndex] <= array[smallestIndex]) {
				smallestIndex = rightChildIndex;
			}
			if (array[index] > array[smallestIndex]) {
				swap(array, index, smallestIndex);
			} else {
				break;
			}
			index = smallestIndex;
		}
	}
	private void swap(int[] array, int left, int right) {
		int tmp = array[left];
		array[left] = array[right];
		array[right] = tmp;
	}
	public static void main(String[] args) {
		int[] array = {1, 4, 2, 5, 9, 8, 6};
		MinHeap test = new MinHeap(array);
		//size, full
		System.out.println(test.size());
		System.out.println(test.isFull());
		//peek(),poll()
		System.out.println(test.peek());
		System.out.println(test.poll());
		System.out.println(test.peek());
		//offer
		System.out.println(test.offer(3));
		System.out.println(test.peek());
		//set
		System.out.println(test.set(0, 7));
		System.out.println(test.peek());
	}
}
