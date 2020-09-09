package com.allendowney.thinkdast;

public class ListNode {
	
	// 연결 자료구조
	// 연결리스트에서 각 노드는 리스트의 다음 노드에 애한 참조를 포함 한다.
	// 연결 구조의 예로는 트리와 그래프가 있다.
	
	public Object data; // 어떤 Object에 대한 참조
	public ListNode next; // 리스트에서 다음 노드에 대한 참조
	
	// 초기화 생성자
	public ListNode() {
		this.data = null;
		this.next = null;
	}
	
	// 노드 생성자
	public ListNode(Object data) {
		this.data = data;
		this.next = null;
	}
	
	// 노드,링크 생성자
	public ListNode(Object data, ListNode next) {
		this.data = data;
		this.next = next;
	}
	
	public String toString() {
		return "ListNode(" + data.toString() + ") + Next(" + next +")";
	}
	
}
