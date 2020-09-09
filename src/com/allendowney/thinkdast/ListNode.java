package com.allendowney.thinkdast;

public class ListNode {
	
	// ���� �ڷᱸ��
	// ���Ḯ��Ʈ���� �� ���� ����Ʈ�� ���� ��忡 ���� ������ ���� �Ѵ�.
	// ���� ������ ���δ� Ʈ���� �׷����� �ִ�.
	
	public Object data; // � Object�� ���� ����
	public ListNode next; // ����Ʈ���� ���� ��忡 ���� ����
	
	// �ʱ�ȭ ������
	public ListNode() {
		this.data = null;
		this.next = null;
	}
	
	// ��� ������
	public ListNode(Object data) {
		this.data = data;
		this.next = null;
	}
	
	// ���,��ũ ������
	public ListNode(Object data, ListNode next) {
		this.data = data;
		this.next = next;
	}
	
	public String toString() {
		return "ListNode(" + data.toString() + ") + Next(" + next +")";
	}
	
}
