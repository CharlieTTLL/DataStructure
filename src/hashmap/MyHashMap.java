package hashmap;

import java.util.Arrays;

public class MyHashMap<K, V> {
	//if you wanna test, just modify to public
	private Node<K, V>[] array;
	private int size;
	private static final int INIT_CAP = 10;
	private static final double LOAD_FACTOR = 0.7;
	public MyHashMap() {
		array = (Node<K, V>[]) new Node[INIT_CAP];
		size = 0;
	}
	
	//get current size of hashmap
	public synchronized int size() {
		return size;
	}
	
	//hashmap is empty or not
	public synchronized boolean isEmpty() {
		return size == 0;
	}
	
	//clear whole hashmap
	public synchronized void clear() {
		Arrays.fill(array, null);
		size = 0;
	}
	
	//hashmap contains "key" or not
	public synchronized boolean containsKey(K key) {
		int index = index(key);
//		if (array[index] == null) {
//			return false;
//		}
		Node head = array[index];
		while (head != null) {
			if (equals((K)head.getKey(), key)) {
				return true;
			}
			head = head.next;
		}
		return false;
	}
	
	//put key-value to hashmap, return object is last object
	public synchronized V put(K key, V value) {
		int index = index(key);
		Node head = array[index];
		while (head != null) {
			if (head.getKey().equals(key)) {
				V result = (V) head.getValue();
				head.setValue(value);
				return result;
			}
			head = head.next;
		}
		//head.next = new Node(key, value);	at this moment, head has been moved to end of linkedlist, so it has been null
		Node<K, V> newEntry = new Node(key, value);
		newEntry.next = array[index];
		array[index] = newEntry;
		// be careful
		size++;
		if (needRehashing()) {
			rehash();
		}
		return null;
	}
	
	//get value of specific key
	public synchronized V get(K key) {
		int index = index(key);
//		if (array[index] == null) {
//			return null;
//		}
		Node head = array[index];
		while (head != null) {
			if (equals((K) head.getKey(), key)) {
				return (V) head.getValue();
			}
			head = head.next;
		}
		return null;
	}
	
	//remove key from hashmap
	public synchronized V remove(K key) {
		int index = index(key);
		//no such key
		if (array[index] == null) {
			return null;
		}
		Node head = array[index];
//		//head is key
//		if (head.getKey() == key) {
//			array[index] = head.next;
//		}
		Node prev = null;
		while (head != null) {
			if (equals((K) head.getKey(), key)) {
				if (prev == null) {
					array[index] = head.next;
				} else {
					prev.next = head.next;
				}
				size--;
				return (V)head.getValue();
			}
			prev = head;
			head = head.next;
		}
		return null;
	}
	
	//expand array size, and rehash() is in put(key, value) so
	//we don't need synchronized one more time
	private void rehash() {
		Node<K, V>[] old = array;
		array = (Node<K, V>[]) new Node[old.length * 2];
		for (Node<K, V> e : old) {
			while (e != null) {
				Node<K, V> next = e.next;
				int i = index(e.getKey());
				e.next = array[i];
				array[i] = e;
				e = next;
			}
		}
	}
	
	//hash() function1
	private int hash(K key) {
		if (key == null) {
			return 0;
		}
		// & with maximum of int long
		return key.hashCode() & 0x7fffffff;
	}
	
	//hash() function2 ---> index bullets
	private int index(K key) {
		int hashNumber = hash(key);
		return hashNumber % array.length;
	}
	
	//compare two key
	private boolean equals(K a, K b) {
		return a == b || a != null && a.equals(b);
	}
	
	private boolean needRehashing() {
		return size > array.length * LOAD_FACTOR;
	}
}
