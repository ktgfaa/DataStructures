package com.allendowney.thinkdast;

public class LinkedListExample {
	public static void main(String[] args) {
		
		// ListNode 객체들의 집합을 생성
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		
		// 다음과 같이 연결
		node1.next = node2;
		node2.next = node3;
		node3.next = null;
		
		// 혹은 노드와 링크를 동시에 생성
		ListNode node0 = new ListNode(0,node1);
		
		// 정수 0,1,2,3이 들어 있는 노드들이 오름차순으로 연결
		System.out.println(node0);
		System.out.println(node1);
		System.out.println(node2);
		System.out.println(node3);
		
		
	}
}
