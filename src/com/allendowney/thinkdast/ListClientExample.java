package com.allendowney.thinkdast;

import java.util.ArrayList;
import java.util.List;

public class ListClientExample {
	
	// interface ��� ���α׷��� or �������̽� ���α׷���
	// ���⼭ �������̽��� �ڹ� �������̽��� �ƴ� �Ϲ����� ������ �������̽��� ����
	// Java�� �������̽��� ���� Ŭ������ ArrayList �� LinkedList Ŭ������ �����Ѵ�
	@SuppressWarnings("rawtypes")
	private List list;
	
	@SuppressWarnings("rawtypes")
	public ListClientExample() {
		list = new ArrayList();
	}
	
	@SuppressWarnings("rawtypes")
	public List getList() {
		return list;
	}
	
	public static void main(String[] args) {
		ListClientExample lce = new ListClientExample();
		@SuppressWarnings("rawtypes")
		List list = lce.getList();
		System.out.println("ListClientExample ����");
		System.out.println(list);
	}
}
