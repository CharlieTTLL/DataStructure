package LinkedList;

public class Test {
	public static void print(ListNode head) {
		while (head != null) {
			System.out.print(head.value + " ");
			head = head.next;
		}
		System.out.println();
	}
	public static void main(String[] args) {
		//function --> addHead, addTail, get, remove, size
		MyLinkedList list = new MyLinkedList();
		list.addHead(3); // 3
		Test.print(list.head);
		list.addHead(4);
		list.addHead(5);
		Test.print(list.head); // 3, 4, 5
		list.addTail(1); // 3, 4, 5, 1
		Test.print(list.head);
		list.remove(1); // 5, 3, 1
		Test.print(list.head);
		System.out.println(list.get(0)); // 0
		System.out.println(list.size()); //3
	}
}
