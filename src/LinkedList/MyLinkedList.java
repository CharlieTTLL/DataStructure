package LinkedList;

public class MyLinkedList {
	int size;
	ListNode head;
	ListNode tail;
	public MyLinkedList() {
		size = 0;
		head = null;
		tail = null;
	}
	//add a node at head of LinkedList
	public void addHead(int value) {
		ListNode newNode = new ListNode(value);
		newNode.next = head;
		head = newNode;
		size++;
		if (size == 1) {
			tail = head;
		}
	}
	public void addTail(int value) {
		ListNode newNode = new ListNode(value);
		if (size == 0) {
			tail = newNode;
			head = tail;
		} else {
			tail.next = newNode;
			tail = newNode;
		}
		size++;
	}
	//get function return ListNode based on index --> get(index)
	public Integer get(int index) {
		if (index < 0 || index >= size) {
			return null;
		}
		ListNode tmp = head;
		for (int i = 0; i < index; i++) {
			tmp = tmp.next;
		}
		return tmp.value;
	}
	//get remove function based on index
	public void remove(int index) {
		//no such index in this LinkedList
		if (index < 0 || index >= size) {
			return;
		}
		//remove head node, head will change
		if (index == 0) {
			if (size == 1) {
				tail = null;
			}
			head = head.next;
			size--;
			return;
		}
		ListNode tmp = head;
		for (int i = 0; i < index - 1; i++) {
			tmp = tmp.next;
		}
		if (index == size - 1) {
			tail = tmp;
		}
		tmp.next = tmp.next.next;
		size--;
		return;
	}
	//return size of LinkedList
	public int size() {
		return size;
	}
}
