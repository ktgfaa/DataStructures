package com.allendowney.thinkdast;

public class LinkedListExample {
	public static void main(String[] args) {
		
		// ListNode ��ü���� ������ ����
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		
		// ������ ���� ����
		node1.next = node2;
		node2.next = node3;
		node3.next = null;
		
		// Ȥ�� ���� ��ũ�� ���ÿ� ����
		ListNode node0 = new ListNode(0,node1);
		
		// ���� 0,1,2,3�� ��� �ִ� ������ ������������ ����
		System.out.println(node0);
		System.out.println(node1);
		System.out.println(node2);
		System.out.println(node3);
		
		
	}
}
