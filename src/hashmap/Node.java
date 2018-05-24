package hashmap;
/*
 * This is single key-value pair node in hashmap
 * */
public class Node<K, V> {
	//Once key is initilized, it cannot be change
	private final K key;
	private V value;
	Node<K,V> next;
	public Node(K key, V value) {
		this.key = key;
		this.value = value;
		next = null;
	}
	public K getKey() {
		return this.key;
	}
	public V getValue() {
		return this.value;
	}
	public void setValue(V value) {
		this.value = value;
	}
}
